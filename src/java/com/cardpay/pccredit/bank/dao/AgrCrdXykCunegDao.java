package com.cardpay.pccredit.bank.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.bank.model.AgrCrdXykCuneg;
import com.wicresoft.util.annotation.Mapper;

/**
 * 黑名单数据资料
 * @author chenzhifang
 *
 * 2014-12-22下午3:26:46
 */
@Mapper
public interface AgrCrdXykCunegDao {
	
	int insertAgrCrdXykCuneg(Map<String, Object> map);
	
	int insert(AgrCrdXykCuneg agrCrdXykCuneg);
	
	List<AgrCrdXykCuneg> findAgrCrdXykCunegs(@Param("createdTime") String createdTime);
	
}
