package com.cardpay.pccredit.bank.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.SXykCustrCurDao;
import com.cardpay.pccredit.bank.model.DataFileConf;
import com.cardpay.pccredit.bank.model.SXykCustrCur;

/**
 * 客户资料表
 * @author chenzhifang
 *
 * 2014-12-12上午11:18:16
 */
@Service
public class SXykCustrCurService {
	public Logger log = Logger.getLogger(SXykCustrCurService.class);
	
	@Autowired
	private SXykCustrCurDao sXykCustrCurDao;
	
	/**
	 * 保数据文件到”客户资料表“表
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public void saveSXykCustrCurDataFile(String fileName) throws Exception {
		ImportBankDataFileTools tools = new ImportBankDataFileTools();
		// 解析数据文件配置
		List<DataFileConf> confList = tools.parseDataFileConf(tools.getFileFullName("/datamapping/sXyCustrCur.xml"));
		
		// 解析”帐单记录表“数据文件
		List<Map<String, Object>> datas = tools.parseDataFile(fileName, confList);
		for(Map<String, Object> map : datas){
			// 保存数据
			//先查询客户资料，存在则更新否则插入
			SXykCustrCur sc = sXykCustrCurDao.findBankCustomerByCertCode(map.get("custrNbr").toString());
			if(sc == null){
				sXykCustrCurDao.insertSXykCustrCur(map);
			}
			else{
				sXykCustrCurDao.updateSXykCustrCur(map);
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			ImportBankDataFileTools tools = new ImportBankDataFileTools();
			// 解析数据文件配置
			List<DataFileConf> confList = tools.parseDataFileConf("D:\\Development\\eclipse-jee-with-android_workspace\\StatisticsCredit\\src\\conf\\datamapping\\sXyCustrCur.xml");
			
			// 解析”账户资料“数据文件
			long start = System.currentTimeMillis();
			List<Map<String, Object>> datas = tools.parseDataFile("C:\\Users\\Administrator\\Desktop\\shuju\\STA_902_djk_STA_YW_DJK_CUSTR_ADD_20150116.dat", confList);
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
	public void deleteOld(){
		sXykCustrCurDao.deleteOld();
	}
}
