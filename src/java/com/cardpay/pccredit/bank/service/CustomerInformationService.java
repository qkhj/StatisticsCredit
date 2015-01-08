/**
 * 
 */
package com.cardpay.pccredit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.CustomerInformationDao;
import com.cardpay.pccredit.datasource.DataSourceContextHolder;

/**
 * @author shaoming
 *
 * 2014年12月17日   下午5:59:08
 */
@Service
public class CustomerInformationService {

	@Autowired
	private CustomerInformationDao customerInformationDao;
	/**
	 * 得到身份证类型Code 
	 * @param certType
	 * @return
	 */
	public String getCertTypeFromCustomerInformationByBankCode(String certType){
		if(DataSourceContextHolder.getDbType()==null || !DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.PCCREDIT)){
			DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
		}
		String code = customerInformationDao.getCertTypeFromCustomerInformationByBankCode(certType);
		if(DataSourceContextHolder.getDbType()==null || !DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.BANK)){
			DataSourceContextHolder.setDbType(DataSourceContextHolder.BANK);
		}
		return code;
	}
}
