package com.cardpay.pccredit.bank.dao;

import java.util.List;

import com.cardpay.pccredit.bank.model.BankDataFileProcess;
import com.wicresoft.util.annotation.Mapper;

/**
 * @author chenzhifang
 *
 * 2014-12-12下午3:42:47
 */
@Mapper
public interface BankDataFileProcessDao {
	
	public List<BankDataFileProcess> findBankDataFileProcess(BankDataFileProcess record);
	
	int insertBankDataFileProcess(BankDataFileProcess record);
	
	int updateByPrimaryKey(BankDataFileProcess record);
}
