package com.cardpay.pccredit.bank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.SXykCustrCurDao;
import com.cardpay.pccredit.bank.dao.SynCustomerInfoDao;
import com.cardpay.pccredit.bank.id.IDGenerator;
import com.cardpay.pccredit.bank.model.CustomerCareersInformation;
import com.cardpay.pccredit.bank.model.CustomerInfor;
import com.cardpay.pccredit.bank.model.Dict;
import com.cardpay.pccredit.bank.model.SXykCustrCur;
import com.cardpay.pccredit.bank.model.XmAccCredit;
import com.cardpay.pccredit.bank.util.Cn2Spell;
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
	
	/**
	 * 客户信息同步
	 * @return
	 */
	public void synCustomerInfo(XmAccCredit xm){
		try {
			//连接系统库
			/*if(DataSourceContextHolder.getDbType()!=null &&!DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.PCCREDIT)){
				DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
			}
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
			}*/
			
			/////////////////////////
			/*if(DataSourceContextHolder.getDbType()!=null &&!DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.BANK)){
				DataSourceContextHolder.setDbType(DataSourceContextHolder.BANK);
			}*/
			
			//客户信息改为从xm_acc_credit中查询
			//SXykCustrCur custrCur = sXykCustrCurDao.findBankCustomerByCertCode(xm.getCertCode());
			
			if(DataSourceContextHolder.getDbType()!=null &&!DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.PCCREDIT)){
				DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
			}
			CustomerInfor infor = synCustomerInfoDao.findCustomerInforByCustrNbr(xm.getCertCode());
			//查询客户经理ID
			String mgrId = synCustomerInfoDao.findMgrIdByNo(xm.getMgrNo());
			
			if(infor == null){
				infor = new CustomerInfor();
			}
			infor.setUserId(mgrId);
			infor.setCardId(xm.getCertCode());
			infor.setChineseName(xm.getCusName());
			infor.setPinyinenglishName(Cn2Spell.converterToSpell(xm.getCusName()));
			infor.setBirthday(xm.getCertCode().substring(6,10)+"-"+xm.getCertCode().substring(10,12)+"-"+xm.getCertCode().substring(12,14));
			//infor.setTelephone(custrCur.getMoPhone());
			//infor.setHomePhone(custrCur.getHomePhone());
			//infor.setIdVerify(custrCur.getIdVerify());
			/*if(StringUtils.isNotEmpty(nationBanckCodeMap.get(custrCur.getNation()))){
				infor.setNationality(nationBanckCodeMap.get(custrCur.getNation()));
			}*/
			infor.setNationality("NTC00000000156");
			/*if(StringUtils.isNotEmpty(genderBankMap.get(custrCur.getGender()))){
				//infor.setSex(genderBankMap.get(custrCur.getGender()));
			}*/
			infor.setSex(Integer.parseInt(xm.getCertCode().substring(16,17))/2==0?"Male":"Female");
			/*if(StringUtils.isNotEmpty(cardTypeBankMap.get(custrCur.getRaceCode()))){
				infor.setCardType(cardTypeBankMap.get(custrCur.getRaceCode()));
			}*/
			infor.setCardType("01");
			/*if(StringUtils.isNotEmpty(homeCodeBankMap.get(custrCur.getHomeCode()))){
				infor.setResidentialPropertie(homeCodeBankMap.get(custrCur.getHomeCode()));
			}
			if(StringUtils.isNotEmpty(educaBankMap.get(custrCur.getEduca()))){
				infor.setDegreeEducation(educaBankMap.get(custrCur.getEduca()));
			}
			if(StringUtils.isNotEmpty(marStatusBankMap.get(custrCur.getMarStatus()))){
				infor.setMaritalStatus(marStatusBankMap.get(custrCur.getMarStatus()));
			}*/
			//infor.setUserId(xm.getMgrNo());
			if(StringUtils.isNotEmpty(infor.getId())){
				synCustomerInfoDao.updateCustomerInfos(infor);
			}else{
				infor.setId(IDGenerator.generateID());
				synCustomerInfoDao.insertCustomerInfos(infor);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("SynCustomerInfoService.synCustomerInfo error.(同步客户信息报错，直接跳过该条记录)",e);
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
