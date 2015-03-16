package com.cardpay.pccredit.bank.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.bank.model.SXykCardCur;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface SXykCardCurDao {
	/**/
	SXykCardCur findSXykCardCurByCardNbr(@Param("cardNar") String cardNar,@Param("createdTime") String createdTime);
	
	List<SXykCardCur> findSXykCardCur(@Param("createdTime") String createdTime);
	List<SXykCardCur> findSXykCardCurByPage(@Param("createdTime") String createdTime,@Param("start") int start,@Param("end") int end);
	
	public int insertSXykCardCur(Map<String, Object> map);
	
	public int getTableCount();
	public void deleteOld();
}