package com.cardpay.pccredit.bank.dao;
import java.util.Map;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cardpay.pccredit.bank.model.XmAccCredit;
import com.wicresoft.util.annotation.Mapper;

@Mapper
public interface XmAccCreditDao {
	/*通过证件号码得到*/
	XmAccCredit findXmAccCreditByCustrNbr(@Param("custrNbr") String custrNbr,@Param("cardNbr") String cardNbr,@Param("createdTime") String createdTime);
	/**
	 * 得到所有的台账信息
	 * @return
	 */
	List<XmAccCredit> findXmAccCredit();
	
	public int insert(XmAccCredit record);
	
	public int insertXmAccCredit(Map<String, Object> map);
	
	public void deleteOld();
}