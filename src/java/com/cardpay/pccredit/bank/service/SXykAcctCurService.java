package com.cardpay.pccredit.bank.service;

import java.util.Date;
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
			sXykAcctCurDao.insertSXykAcctCur(map);
		}
	}
	public SXykAcctCur findSXykAcctCurByCardNbr(String cardNbr){
		String createdTime = DateHelper.getDateFormat(new Date(), "yyyyMMdd");
		return sXykAcctCurDao.findSXykAcctCurByCardNbr(cardNbr,createdTime);
	}
	
	public void deleteOld(){
		sXykAcctCurDao.deleteOld();
	}
}
