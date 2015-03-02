package com.cardpay.pccredit.bank.dao;

import java.util.List;
import java.util.Map;

import com.cardpay.pccredit.bank.model.SXykCustrCur;
import com.wicresoft.util.annotation.Mapper;

/**
 * @author chenzhifang
 *
 * 2014-12-12上午11:08:59
 */
@Mapper
public interface SXykCustrCurDao {
	
	public int insertSXykCustrCur(Map<String, Object> map);

	public List<SXykCustrCur> findBankCustomerByCardIds(Map<String, Object> map);
	
	public void deleteOld();
}
