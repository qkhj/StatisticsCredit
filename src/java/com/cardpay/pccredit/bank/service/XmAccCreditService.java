package com.cardpay.pccredit.bank.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.XmAccCreditDao;
import com.cardpay.pccredit.bank.model.DataFileConf;
import com.cardpay.pccredit.bank.model.XmAccCredit;
import com.wicresoft.util.date.DateHelper;

/**
 * 贷记卡台帐表
 * @author chenzhifang
 *
 * 2014-12-3下午2:46:41
 */
@Service
public class XmAccCreditService {
	@Autowired
	private XmAccCreditDao xmAccCreditDao;
	
	public Logger log = Logger.getLogger(XmAccCreditService.class);
	
	/**
	 * 保数据文件到”存贷记卡台帐“表
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public void saveXmAccCreditDataFile(String fileName) throws Exception {
		ImportBankDataFileTools tools = new ImportBankDataFileTools();
		// 解析数据文件配置
		List<DataFileConf> confList = tools.parseDataFileConf(tools.getFileFullName("/datamapping/xmAccCredit.xml"));
		
		// 解析”存贷记卡台帐“数据文件
		List<Map<String, Object>> datas = tools.parseDataFile(fileName, confList);
		for(Map<String, Object> map : datas){
			String value = (String) map.get("billDays");
			value = value.replaceAll("[\\.0]+$", "");
			if(StringUtils.isNotBlank(value) && value.indexOf(".") == 0){
				value = "0"+value;
			}
			map.put("billDays", value);
			// 保存数据
			xmAccCreditDao.insertXmAccCredit(map);
		}
	}
	public XmAccCredit findXmAccCreditByCustrNbr(String custrNbr){
		String createdTime = DateHelper.getDateFormat(new Date(), "yyyyMMdd");
		return xmAccCreditDao.findXmAccCreditByCustrNbr(custrNbr,createdTime);
	}
}	
	
	
