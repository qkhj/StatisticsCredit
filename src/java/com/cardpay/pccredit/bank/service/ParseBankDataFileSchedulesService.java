package com.cardpay.pccredit.bank.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.model.BankDataFileProcess;
import com.cardpay.pccredit.bank.util.BankFtpConfig;
import com.cardpay.pccredit.bank.util.DateUtils;
import com.cardpay.pccredit.bank.util.ExpandGZ;
import com.cardpay.pccredit.base.InitServer;
import com.cardpay.sftp.Sftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

/**
 * @author chenzhifang
 *
 * 2014-12-8下午3:35:55
 */
@Service
public class ParseBankDataFileSchedulesService {
	public Logger log = Logger.getLogger(ParseBankDataFileSchedulesService.class);
	
	public static final String PARSE = "1";
	
	public static final String FAIL = "2";
	
	public String curRemotePath = "";//保存本次remotePath
	
	@Autowired
	private BankFtpConfig bankFtpConfig;
	
	@Autowired
	private BankDataFileProcessService bankDataFileProcessService;
	
	@Autowired
	private SXykAcctCurService sXykAcctCurService;
	
	@Autowired
	private SXykCardCurService sXykCardCurService;
	
	@Autowired
	private XmAccCreditService xmAccCreditService;
	
	@Autowired
	private SXykStmtCurService sXykStmtCurService;
	
	@Autowired
	private SXykCustrCurService sXykCustrCurService;
	
	@Autowired
	private AgrCrdXykCunegService agrCrdXykCunegService;
	
	public void init() {
		File file = new File(bankFtpConfig.getDownloadPath());
		// 检测下载临时保存目录是否存在
		if(!file.exists()){
			file.mkdirs();
		}
		// 检测备份目录是否存在
		file = new File(bankFtpConfig.getBackupPath());
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	/**
	 * 下载并解析银行数据文件定时任务
	 * @return
	 * @throws Exception 
	 */
	@Scheduled(cron = "50 * * * * ?")
	public void downloadAndParseDataFileSchedules(){
		Sftp sftp = null;
		try {
			sftp = new Sftp(bankFtpConfig.getHost(),bankFtpConfig.getUsername(), bankFtpConfig.getPassword(),
					 			bankFtpConfig.getPrivateKey(), bankFtpConfig.getPassphrase(),bankFtpConfig.getPort());
			sftp.connect();
			//获取前一天
			String fmt = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			Date date = new Date();
			String dateStr = sdf.format(date);
			dateStr = "2014-12-29";
			curRemotePath = bankFtpConfig.getRemothPath()+DateUtils.getSpecifiedDayBefore(dateStr)+"/902";
			//获取文件列表
			ArrayList<String> files = sftp.getList(curRemotePath);
			// 处理ftp文件
			processFtpFile(sftp, files);
		} catch (JSchException e) {
			log.error("", e);
		} catch (SftpException e) {
			log.error("", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(sftp != null){
				sftp.close();
				System.out.println("success,,/////");
			}
		}
	}
	
	/**
	 * 处理ftp文件
	 * @return
	 * @throws Exception 
	 */
	public void processFtpFile(Sftp sftp, ArrayList<String> files) throws Exception{
		Iterator<String> pathIterator = files.iterator();
		// 下载路径
		String downloadPath =  bankFtpConfig.getDownloadPath();
		downloadPath = URLDecoder.decode(downloadPath, "utf-8");
		// 备份路径
		String backupPath = bankFtpConfig.getBackupPath();
		backupPath = URLDecoder.decode(backupPath, "utf-8");
		
		while(pathIterator.hasNext()){
			String file = pathIterator.next();
			try{
				if(!bankDataFileProcessService.isExist(file)){
					//下载文件
					if(sftp.download(curRemotePath, file, downloadPath)){
						// 备份文件
						boolean flag = backupFile(downloadPath + File.separator + file, backupPath);
						if(flag){
							// 解析数据文件
							saveParseDataFile(backupPath, file);
						}
						
						//if(!sftp.remove(bankFtpConfig.getRemothPath(), file, bankFtpConfig.getRemothBackupPath())){
						//	log.error("移动文件" + file + "失败");
						//}
					}else{
						log.error("下载文件" + file + "失败");
					}
				}else{
					//删除文件
					//sftp.delete(bankFtpConfig.getRemothPath(), file);
				}
			}catch(Exception e){
				log.error("处理文件" + file + "出错", e);
			}
		}
	}
	
	/**
	 * 解析银行数据文件
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public void saveParseDataFile(String path, String fileName) throws Exception{
		BankDataFileProcess bankDataFileProcess = new BankDataFileProcess();
		bankDataFileProcess.setFileName(fileName);
		bankDataFileProcess.setProcessTime(new Date());
		bankDataFileProcess.setStatus(PARSE);
		
		if(fileName.endsWith(".gz"))
		{
			ExpandGZ.doExpand(path + File.separator + fileName);
			int to = fileName.lastIndexOf('.');
			fileName = fileName.substring(0, to);
		}
		
		try{
			if(fileName.startsWith("STA_902_cmis_ACC_CREDIT_ADD_")) {
				/**
				 * “贷记卡台帐”数据文件
				 */
				bankDataFileProcess.setType("ACC_CREDIT");
				bankDataFileProcessService.insert(bankDataFileProcess);
				xmAccCreditService.saveXmAccCreditDataFile(path + File.separator + fileName);
			} else if(fileName.startsWith("STA_902_djk_STA_YW_DJK_ACCT_ADD_")) {
				/**
				 * “卡片资料”数据文件 
				 */
				bankDataFileProcess.setType("ACC");
				bankDataFileProcessService.insert(bankDataFileProcess);
				sXykAcctCurService.saveSXykAcctCurDataFile(path + File.separator + fileName);
			} else if(fileName.startsWith("STA_902_djk_STA_YW_DJK_CARD_ADD_")) {
				/**
				 * ”账户资料表“数据文件
				 */
				bankDataFileProcess.setType("CARD");
				bankDataFileProcessService.insert(bankDataFileProcess);
				sXykCardCurService.saveSXykCardCurDataFile(path + File.separator + fileName);
			} else if(fileName.startsWith("STA_902_djk_STA_YW_DJK_STMT_ADD_")) {
				/**
				 * ”帐单记录表“数据文件
				 */
				bankDataFileProcess.setType("STMT");
				bankDataFileProcessService.insert(bankDataFileProcess);
				sXykStmtCurService.saveSXykStmtCurDataFile(path + File.separator + fileName);
			} else if(fileName.startsWith("STA_902_djk_STA_YW_DJK_CUSTR_ADD_")) {
				/**
				 * ”客户资料表“数据文件
				 */
				bankDataFileProcess.setType("CUSTR");
				bankDataFileProcessService.insert(bankDataFileProcess);
				sXykCustrCurService.saveSXykCustrCurDataFile(path + File.separator + fileName);
			} else if(fileName.startsWith("STA_902_djk_STA_YW_DJK_CUNEG_ADD_")) {
				/**
				 * ”黑名单数据资料“数据文件
				 */
				bankDataFileProcess.setType("CUNEG");
				bankDataFileProcessService.insert(bankDataFileProcess);
				agrCrdXykCunegService.saveAgrCrdXykCunegDataFile(path + File.separator + fileName);
			}
			bankDataFileProcessService.updateByPrimaryKey(bankDataFileProcess);
		}catch(Exception e){
			bankDataFileProcess.setStatus(FAIL);
			bankDataFileProcessService.updateByPrimaryKey(bankDataFileProcess);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 拷贝数据文件
	 * @param srcFilePath
	 * @param dstFilePath
	 * @return
	 * @throws Exception 
	 */
	public boolean copyFile(String srcFilePath, String dstFilePath) {
		try {
			int byteread = 0;
			File srcFile = new File(srcFilePath);
			if (srcFile.exists()) {
				InputStream inputStream = new FileInputStream(srcFilePath);
				FileOutputStream outputStream = new FileOutputStream(dstFilePath);
				byte[] buffer = new byte[1024];
				while ((byteread = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, byteread);
				}
				inputStream.close();
				outputStream.close();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 备份并删除原始数据文件
	 * @return
	 * @throws Exception 
	 */
	public boolean backupFile(String srcFilePath, String toPathString) {
		File srcF = new File(srcFilePath);
		File toFilePath = new File(toPathString);
		if (!toFilePath.exists()){
			toFilePath.mkdirs();
		}
		String dstFilePath = toPathString + File.separator + srcF.getName();
		if(copyFile(srcFilePath, dstFilePath)){
			try{
				srcF.delete();
				return true;
			}catch(Exception e){
				e.getStackTrace();
				return false;
			}
		}
		return false;
	}
}
