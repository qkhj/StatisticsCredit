package com.cardpay.pccredit.bank.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.SXykAcctCurDao;
import com.cardpay.pccredit.bank.model.DataFileConf;
import com.cardpay.pccredit.bank.model.SXykAcctCur;
import com.wicresoft.util.date.DateHelper;

/**
 * 账户资料表
 * @author chenzhifang
 *
 * 2014-12-5下午5:54:17
 */
@Service
public class SXykAcctCurService {
	public Logger log = Logger.getLogger(SXykAcctCurService.class);
	
	@Autowired
	private SXykAcctCurDao sXykAcctCurDao;
	
	/**
	 * 保数据文件到”账户资料“表
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public void saveSXykAcctCurDataFile(String fileName) throws Exception {
		ImportBankDataFileTools tools = new ImportBankDataFileTools();
		// 解析数据文件配置
		List<DataFileConf> confList = tools.parseDataFileConf(tools.getFileFullName("/datamapping/sXykAcctCur.xml"));
		
		// 解析”账户资料“数据文件
		List<Map<String, Object>> datas = tools.parseDataFile(fileName, confList);
		for(Map<String, Object> map : datas){
			// 保存数据
			//先查询卡号对应账户信息是否存在，存在则更新，否则插入
			int count = sXykAcctCurDao.findSXykAcctCurByCardNbr2(map.get("cardNbr").toString());
			if(count == 0){
				sXykAcctCurDao.insertSXykAcctCur(map);
			}
			else{
				sXykAcctCurDao.updateSXykAcctCur(map);
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			ImportBankDataFileTools tools = new ImportBankDataFileTools();
			// 解析数据文件配置
			List<DataFileConf> confList = tools.parseDataFileConf("D:\\Development\\eclipse-jee-with-android_workspace\\StatisticsCredit\\src\\conf\\datamapping\\sXykAcctCur.xml");
			
			// 解析”账户资料“数据文件
			long start = System.currentTimeMillis();
			List<Map<String, Object>> datas = tools.parseDataFile("C:\\Users\\Administrator\\Desktop\\shuju\\STA_902_djk_STA_YW_DJK_ACCT_ADD_20150116_0.dat", confList);
			/*for(Map<String, Object> map : datas){
				Iterator<String> keys = map.keySet().iterator(); 

				   while(keys.hasNext()) { 
				   String key = (String) keys.next(); 
				   String value=map.get(key).toString(); 
				   System.out.println("键"+key+"="+"值"+value); 

				} 
			}*/
			long end = System.currentTimeMillis();
			System.out.println("读取文件内容花费：" + (end - start) + "毫秒");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SXykAcctCur findSXykAcctCurByCardNbr(String cardNbr){
		String createdTime = DateHelper.getDateFormat(new Date(), "yyyyMMdd");
		//return sXykAcctCurDao.findSXykAcctCurByCardNbr(cardNbr,createdTime);//账户信息每日更新 所以不能只查当天created的记录
		return sXykAcctCurDao.findSXykAcctCurByCardNbr3(cardNbr);
	}
	
	public void deleteOld(){
		sXykAcctCurDao.deleteOld();
	}
}
