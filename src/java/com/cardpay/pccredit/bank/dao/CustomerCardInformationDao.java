package com.cardpay.pccredit.bank.dao;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.bank.model.CustomerCardInformation;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface CustomerCardInformationDao {
	 int insert(CustomerCardInformation record);

	 int insertSelective(CustomerCardInformation record);
	 
	 CustomerCardInformation findCustomerCardInformationByCardId(@Param("cardId") String cardId,@Param("year") String year,@Param("month") String month);
	 
	 int updateByPrimaryKeySelective(CustomerCardInformation record);
}