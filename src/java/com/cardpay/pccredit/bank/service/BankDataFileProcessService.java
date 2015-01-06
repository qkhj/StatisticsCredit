package com.cardpay.pccredit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.BankDataFileProcessDao;
import com.cardpay.pccredit.bank.id.IDGenerator;
import com.cardpay.pccredit.bank.model.BankDataFileProcess;

/**
 * @author chenzhifang
 *
 * 2014-12-12下午3:42:08
 */
@Service
public class BankDataFileProcessService {
	
	@Autowired
	private BankDataFileProcessDao bankDataFileProcessDao;
	
	public boolean isExist(String fileName){
		BankDataFileProcess dankDataFileProcess = new BankDataFileProcess();
		dankDataFileProcess.setFileName(fileName);
		return bankDataFileProcessDao.findBankDataFileProcess(dankDataFileProcess).size() > 0;
	}
	
	public boolean insert(BankDataFileProcess bankDataFileProcess){
		bankDataFileProcess.setId(IDGenerator.generateID());
		return bankDataFileProcessDao.insertBankDataFileProcess(bankDataFileProcess) != 0;
	}
	
	public boolean updateByPrimaryKey(BankDataFileProcess bankDataFileProcess){
		return bankDataFileProcessDao.updateByPrimaryKey(bankDataFileProcess) != 0;
	}
}
