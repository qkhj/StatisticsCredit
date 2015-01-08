package com.cardpay.pccredit.bank.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.bank.model.SXykAcctCur;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface SXykAcctCurDao {
	
	SXykAcctCur findSXykAcctCurByCardNbr(@Param("cardNbr") String cardNbr,@Param("createdTime") String createdTime);

	public int insertSXykAcctCur(Map<String, Object> map);
}