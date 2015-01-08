/**
 * 
 */
package com.cardpay.pccredit.bank.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.CustomerCardInformationDao;
import com.cardpay.pccredit.bank.model.CustomerCardInformation;

/**
 * @author shaoming
 *
 * 2014年12月18日   下午3:49:27
 */
@Service
public class CustomerCardInformationService {

	@Autowired
	private CustomerCardInformationDao customerCardInformationDao;
	
	public CustomerCardInformation findCustomerCardInformationByCardId(String cardId){
		Calendar cal=Calendar.getInstance();
		/*字段 Month*/
		int month = cal.get(Calendar.MONTH)+1;;
		/*字段 Year*/
		int year = cal.get(Calendar.YEAR);
		return customerCardInformationDao.findCustomerCardInformationByCardId(cardId, String.valueOf(year), String.valueOf(month));
	}
}
