package com.cardpay.pccredit.bank.dao;

import java.util.List;
import java.util.Map;

import com.cardpay.pccredit.bank.model.SXykStmtCur;
import com.wicresoft.util.annotation.Mapper;

/**
 * @author chenzhifang
 *
 * 2014-12-9下午3:44:15
 */
@Mapper
public interface SXykStmtCurDao {
	
	List<SXykStmtCur> findSXykStmtCur();
	public int insertSXykStmtCur(Map<String, Object> map);
}
