package com.cardpay.pccredit.bank.dao;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.bank.model.CustomerApplicationInfo;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface CustomerApplicationInfoDao {
	/**
	 * 通过客户id和产品id得到申请件信息
	 * @param customerId
	 * @param productId
	 * @return
	 */
	CustomerApplicationInfo findCustomerApplicationInfoByCustomerIdAndProductId(@Param("customerId") String customerId,@Param("productId") String productId);
	/**
	 * 修改申请件信息
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(CustomerApplicationInfo record);
}