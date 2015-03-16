package com.cardpay.pccredit.bank.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.SXykStmtCurDao;
import com.cardpay.pccredit.bank.model.DataFileConf;

/**
 * 帐单记录表
 * @author chenzhifang
 *
 * 2014-12-9下午3:56:06
 */
@Service
public class SXykStmtCurService {
	public Logger log = Logger.getLogger(SXykStmtCurService.class);
	
	@Autowired
	private SXykStmtCurDao sXykStmtCurDao;
	
	/**
	 * 保数据文件到”帐单记录表“表
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public void saveSXykStmtCurDataFile(String fileName) throws Exception {
		ImportBankDataFileTools tools = new ImportBankDataFileTools();
		// 解析数据文件配置
		List<DataFileConf> confList = tools.parseDataFileConf(tools.getFileFullName("/datamapping/sXykStmtCur.xml"));
		
		// 解析”帐单记录表“数据文件
		List<Map<String, Object>> datas = tools.parseDataFile(fileName, confList);
		for(Map<String, Object> map : datas){
			// 保存数据
			sXykStmtCurDao.insertSXykStmtCur(map);
		}
	}
	
	public static void main(String[] args) {
		try {
			ImportBankDataFileTools tools = new ImportBankDataFileTools();
			// 解析数据文件配置
			List<DataFileConf> confList = tools.parseDataFileConf("D:\\Development\\eclipse-jee-with-android_workspace\\StatisticsCredit\\src\\conf\\datamapping\\sXykStmtCur.xml");
			
			// 解析”帐单记录表“数据文件
			long start = System.currentTimeMillis();
			List<Map<String, Object>> datas = tools.parseDataFile("C:\\Users\\Administrator\\Desktop\\shuju\\test.dat", confList);
			for(Map<String, Object> map : datas){
				Iterator<String> keys = map.keySet().iterator(); 

				   while(keys.hasNext()) { 
				   String key = (String) keys.next(); 
				   String value=map.get(key).toString(); 
				   System.out.println("键"+key+"="+"值"+value); 

				} 
			}
			long end = System.currentTimeMillis();
			System.out.println("读取文件内容花费：" + (end - start) + "毫秒");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteOld(){
		sXykStmtCurDao.deleteOld();
	}
}
