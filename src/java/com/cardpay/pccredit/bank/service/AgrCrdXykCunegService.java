package com.cardpay.pccredit.bank.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.AgrCrdXykCunegDao;
import com.cardpay.pccredit.bank.model.DataFileConf;

/**
 * 黑名单数据资料
 * @author chenzhifang
 *
 * 2014-12-22下午3:38:08
 */
@Service
public class AgrCrdXykCunegService {
	@Autowired
	private AgrCrdXykCunegDao agrCrdXykCunegDao;
	
	public Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 保数据文件到”黑名单数据资料“表
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public void saveAgrCrdXykCunegDataFile(String fileName) throws Exception {
		ImportBankDataFileTools tools = new ImportBankDataFileTools();
		// 解析数据文件配置
		List<DataFileConf> confList = tools.parseDataFileConf(tools.getFileFullName("/datamapping/agrCrdXykCuneg.xml"));
		
		// 解析”黑名单数据资料“数据文件
		List<Map<String, Object>> datas = tools.parseDataFile(fileName, confList);
		for(Map<String, Object> map : datas){
			// 保存数据
			agrCrdXykCunegDao.insertAgrCrdXykCuneg(map);
		}
	}
	
	public void deleteOld(){
		agrCrdXykCunegDao.deleteOld();
	}
}
