package com.cardpay.pccredit.bank.dao;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.bank.model.AverageDailyOverdraft;
import com.cardpay.pccredit.bank.model.CardInformation;
import com.cardpay.pccredit.bank.model.CustomerAccountInfor;
import com.cardpay.pccredit.bank.model.CustomerInfor;
import com.cardpay.pccredit.bank.model.CustomerOverdue;
import com.cardpay.pccredit.bank.model.CustomerOverdueHistory;
import com.cardpay.pccredit.bank.model.NplsInfomation;
import com.cardpay.pccredit.bank.model.ProductAttribute;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface BankDao {
	public CustomerInfor findCustomerInforByCard(@Param("certType") String certType,@Param("certCode") String certCode);
	public ProductAttribute findProductAttributeByCode(@Param("code") String code);
	/*逾期客户*/
	CustomerOverdue findCustomerOverdue(@Param("customerId") String customerId,@Param("productId") String productId);
	
	int insertCustomerOverdue(CustomerOverdue customerOverdue);
	
	int updateCustomerOverdue(CustomerOverdue customerOverdue);
	
	int transferhistoricalToHistory(CustomerOverdueHistory customerOverdue);
	
	int deleteCustomerOverdue(@Param("id") String id);
	/*卡片信息*/
	CardInformation findCardInformation(@Param("cardNar") String cardNar);
	
	int insertCardInformation(CardInformation cardInfor);
	
	int updateCardInformation(CardInformation cardInfor);
	/*客户账户信息*/
	CustomerAccountInfor findCustomerAccountInfor(@Param("cardNumber") String cardNumber);
	
	int updateCustomerAccountInfor(CustomerAccountInfor customerAccountInfor);
	
	int insertCustomerAccountInfor(CustomerAccountInfor customerAccountInfor);
	/*客户经理统计每月日均透支额度*/
	int insertAverageDailyOverdraft(AverageDailyOverdraft averageDailyOverdraft);
	
	AverageDailyOverdraft findAverageDailyOverdraftByAccountNumber(@Param("accountNumber")String accountNumber,@Param("year") int year,@Param("month") int month);
	
	int updateAverageDailyOverdraft(AverageDailyOverdraft averageDailyOverdraft);
	public ProductAttribute getDefaultProduct();
	int insertNplsInfomation(NplsInfomation nplsInfomation);
	void updateNplsInfomation(NplsInfomation nplsInfomation);
	void deleteNplsInfomation(@Param("accountNumber")String accountNumber);
	public NplsInfomation findNplsInfomation(@Param("accountNumber")String accountNumber);
	
}
