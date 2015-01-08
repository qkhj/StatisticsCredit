package com.cardpay.pccredit.bank.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.SXykCustrCurDao;
import com.cardpay.pccredit.bank.model.DataFileConf;

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
			sXykCustrCurDao.insertSXykCustrCur(map);
		}
	}
}
