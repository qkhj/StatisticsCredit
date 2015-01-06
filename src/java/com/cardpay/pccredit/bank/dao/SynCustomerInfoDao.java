package com.cardpay.pccredit.bank.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.bank.model.CustomerCareersInformation;
import com.cardpay.pccredit.bank.model.CustomerInfor;
import com.cardpay.pccredit.bank.model.Dict;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface SynCustomerInfoDao {
	
	/**
	 * 分页查询
	 * @param page
	 * @param limit
	 * @return
	 */
	List<CustomerInfor> findCustomerInforByPage(@Param("page") int page, @Param("limit") int limit);
	
	/**
	 * 根据代码类别查询数据字典
	 * @param dictType
	 * @return
	 */
	List<Dict> findDictByDictType(@Param("dictType") String dictType);

	/**
	 * 获取所有行业类别
	 * @return
	 */
	List<Dict> findIndustryType();
	
	/**
	 * 更新客户信息
	 * @param infor
	 */
	void updateCustomerInfos(CustomerInfor infor);
	
	/**
	 * 更新职业信息
	 * @param careersInformation
	 */
	void updateCustomerCarresInfos(CustomerCareersInformation careersInformation);
	
}
