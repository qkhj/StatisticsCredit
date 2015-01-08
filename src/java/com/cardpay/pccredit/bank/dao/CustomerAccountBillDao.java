package com.cardpay.pccredit.bank.dao;

import com.cardpay.pccredit.bank.model.CustomerAccountBill;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface CustomerAccountBillDao {
	int insert(CustomerAccountBill record);

    int insertSelective(CustomerAccountBill record);
}