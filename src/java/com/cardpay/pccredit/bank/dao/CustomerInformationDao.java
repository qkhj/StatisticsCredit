/**
 * 
 */
package com.cardpay.pccredit.bank.dao;

import org.apache.ibatis.annotations.Param;

import com.wicresoft.util.annotation.Mapper;

/**
 * @author shaoming
 *
 * 2014年12月17日   下午5:59:40
 */
@Mapper
public interface CustomerInformationDao {

	String getCertTypeFromCustomerInformationByBankCode(@Param("code") String code);
}
