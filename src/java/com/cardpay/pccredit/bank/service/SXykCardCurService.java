package com.cardpay.pccredit.bank.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.SXykCardCurDao;
import com.cardpay.pccredit.bank.model.DataFileConf;
import com.cardpay.pccredit.bank.model.SXykCardCur;
import com.wicresoft.util.date.DateHelper;

/**
 * 卡片资料表
 * @author chenzhifang
 *
 * 2014-12-8上午10:38:29
 */
@Service
public class SXykCardCurService {
	
	@Autowired
	private SXykCardCurDao sXykCardCurDao;
	
	/**
	 * 保数据文件到”卡片资料“表
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public void saveSXykCardCurDataFile(String fileName) throws Exception {
		ImportBankDataFileTools tools = new ImportBankDataFileTools();
		// 解析数据文件配置 
		List<DataFileConf> confList = tools.parseDataFileConf(tools.getFileFullName("/datamapping/sXykCardCur.xml"));
		
		// 解析”卡片资料“数据文件
		List<Map<String, Object>> datas = tools.parseDataFile(fileName, confList);
		for(Map<String, Object> map : datas){
			// 保存数据
			sXykCardCurDao.insertSXykCardCur(map);
		}
	}
	public SXykCardCur findSXykCardCurByCardNbr(String cardNbr){
		String createdTime = DateHelper.getDateFormat(new Date(), "yyyyMMdd");
		return sXykCardCurDao.findSXykCardCurByCardNbr(cardNbr,createdTime);
	}
	public List<SXykCardCur> findSXykCardCur(){
		String createdTime = DateHelper.getDateFormat(new Date(), "yyyyMMdd");
		return sXykCardCurDao.findSXykCardCur(createdTime);
	}
}
