package com.cardpay.pccredit.bank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.SXykCustrCurDao;
import com.cardpay.pccredit.bank.dao.SynCustomerInfoDao;
import com.cardpay.pccredit.bank.model.CustomerCareersInformation;
import com.cardpay.pccredit.bank.model.CustomerInfor;
import com.cardpay.pccredit.bank.model.Dict;
import com.cardpay.pccredit.bank.model.SXykCustrCur;
import com.cardpay.pccredit.datasource.DataSourceContextHolder;

/**
 * 
 * 描述 ：同步客户核心信息
 * @author 张石树
 *
 * 2014-12-12 上午11:09:02
 */
@Service
public class SynCustomerInfoService{
	
	/*日志*/
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SynCustomerInfoDao synCustomerInfoDao;
	
	@Autowired
	private SXykCustrCurDao sXykCustrCurDao;
	
	public void synCustomerInfo(){
		try {
			int limit = 100;
			// 查询页码
			int page = 0;
			//连接系统库
			DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
			Map<String, String> nationBanckCodeMap = this.findDictToMapByDictType("CMMSCNTC-国籍");
			Map<String, String> genderBankMap = this.findDictToMapByDictType("GENDER");
			Map<String, String> cardTypeBankMap = this.findDictToMapByDictType("CMMSCCST-证件类型");
			Map<String, String> marStatusBankMap = this.findDictToMapByDictType("CMMSCMRC-婚姻状况");
			Map<String, String> educaBankMap = this.findDictToMapByDictType("DEGREEEDUCATION");
			Map<String, String> homeCodeBankMap = this.findDictToMapByDictType("CMMSCHOM-住宅类型");
			Map<String, String> posnEmplyBankMap = this.findDictToMapByDictType("CMMSCCCC-职业");
			Map<String, String> occCatgryBankMap = this.findDictToMapByDictType("CMMSCCOM-单位性质分值");
			List<Dict> industryTypeDicts = synCustomerInfoDao.findIndustryType();
			Map<String, String> industryTypeBankMap = new HashMap<String, String>();
			for(Dict dict : industryTypeDicts){
				industryTypeBankMap.put(dict.getBankCode(), dict.getTypeCode());
			}
			
			List<CustomerInfor> list = synCustomerInfoDao.findCustomerInforByPage(page, limit);
			while(list != null && list.size() != 0){
				//证件号码list
				List<String> cardIds = new ArrayList<String>();
				//证件号码  客户id map
				Map<String, String> cardIdAndCustrIdMap = new HashMap<String, String>();
				for(CustomerInfor infor : list){
					if(StringUtils.isNotEmpty(infor.getCardId())){
						cardIds.add(infor.getCardId());
						cardIdAndCustrIdMap.put(infor.getCardId(), infor.getId());
					}
				}
				if(cardIds.size() > 0){
					//连接数据同步的库
					DataSourceContextHolder.setDbType(DataSourceContextHolder.BANK);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("cardIds", cardIds);
					List<SXykCustrCur> SXykCustrCurList = sXykCustrCurDao.findBankCustomerByCardIds(map);
//					custr_nbr	客户证件号码  -
//					nation	国籍 -
//					busi_phone	单位电话号码  -
//					comp_name	单位名称-
//					educa	教育程度-
//					gender	性别 -
//					home_code	住房情况-
//					home_phone	住宅电话号码-
//					mar_status	婚姻状况-
//					mo_phone	手机号码-
//					occ_catgry	公司性质-
//					occ_code	行业类别代码 -
//					posn_emply	职务 -
//					race_code	证件类型 -
//					surname	姓名 -
//					yr_in_comp	个人工龄 -
//					id_verify	身份核查结果-
					
					DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
					//修改数据  连接系统库 TODO
					for(SXykCustrCur custrCur : SXykCustrCurList){
						CustomerInfor infor = new CustomerInfor();
						infor.setId(cardIdAndCustrIdMap.get(custrCur.getCustrNbr()));
						infor.setCardId(custrCur.getCustrNbr());
						infor.setChineseName(custrCur.getSurname());
						infor.setTelephone(custrCur.getMoPhone());
						infor.setHomePhone(custrCur.getHomePhone());
						infor.setIdVerify(custrCur.getIdVerify());
						if(StringUtils.isNotEmpty(nationBanckCodeMap.get(custrCur.getNation()))){
							infor.setNationality(nationBanckCodeMap.get(custrCur.getNation()));
						}
						if(StringUtils.isNotEmpty(genderBankMap.get(custrCur.getGender()))){
							infor.setSex(genderBankMap.get(custrCur.getGender()));
						}
						if(StringUtils.isNotEmpty(cardTypeBankMap.get(custrCur.getRaceCode()))){
							infor.setCardType(cardTypeBankMap.get(custrCur.getRaceCode()));
						}
						if(StringUtils.isNotEmpty(homeCodeBankMap.get(custrCur.getHomeCode()))){
							infor.setResidentialPropertie(homeCodeBankMap.get(custrCur.getHomeCode()));
						}
						if(StringUtils.isNotEmpty(educaBankMap.get(custrCur.getEduca()))){
							infor.setDegreeEducation(educaBankMap.get(custrCur.getEduca()));
						}
						if(StringUtils.isNotEmpty(marStatusBankMap.get(custrCur.getMarStatus()))){
							infor.setMaritalStatus(marStatusBankMap.get(custrCur.getMarStatus()));
						}
						
						synCustomerInfoDao.updateCustomerInfos(infor);
						
						CustomerCareersInformation careersInformation = new CustomerCareersInformation();
						careersInformation.setCustomerId(cardIdAndCustrIdMap.get(custrCur.getCustrNbr()));
						careersInformation.setUnitPhone(custrCur.getBusiPhone());
						careersInformation.setNameUnit(custrCur.getCompName());
						if(custrCur.getYrInComp() != null){
							careersInformation.setWorkTime(custrCur.getYrInComp().intValue() + "");
						}
						if(StringUtils.isNotEmpty(occCatgryBankMap.get(custrCur.getOccCatgry()))){
							careersInformation.setUnitNature(occCatgryBankMap.get(custrCur.getOccCatgry()));
						}
						if(StringUtils.isNotEmpty(industryTypeBankMap.get(custrCur.getOccCode()))){
							careersInformation.setIndustryType(industryTypeBankMap.get(custrCur.getOccCode()));
						}
						if(StringUtils.isNotEmpty(posnEmplyBankMap.get(custrCur.getPosnEmply()))){
							careersInformation.setTitle(posnEmplyBankMap.get(custrCur.getPosnEmply()));
						}
						synCustomerInfoDao.updateCustomerCarresInfos(careersInformation);
					}
				}
				DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
				page ++;
				//连接系统库
				list = synCustomerInfoDao.findCustomerInforByPage(page, limit);;
			}
		} catch (Exception e) {
			logger.error("SynCustomerInfoService.synCustomerInfo error.",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据字典类型 查询结果为Map
	 * @param dictType
	 * @return
	 */
	private Map<String, String> findDictToMapByDictType(String dictType) {
		Map<String, String> map = new HashMap<String, String>();
		List<Dict> dicts = synCustomerInfoDao.findDictByDictType(dictType);
		for(Dict dict : dicts){
			map.put(dict.getBankCode(), dict.getTypeCode());
		}
		return map;
	}
}
