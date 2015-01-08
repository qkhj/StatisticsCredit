package com.cardpay.pccredit.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.dao.AgrCrdXykCunegDao;
import com.cardpay.pccredit.bank.model.AgrCrdXykCuneg;
import com.cardpay.pccredit.datasource.DataSourceContextHolder;
import com.wicresoft.util.date.DateHelper;

/**
 * "黑名单数据资料"同步
 * @author chenzhifang
 *
 * 2014-12-22下午3:56:22
 */
@Service
public class SyncArgCrdXykCunegSchedules {
	
	@Autowired
	private AgrCrdXykCunegDao agrCrdXykCunegDao;
	
	/**
	 * "黑名单数据资料"同步
	 * @return
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void  syncData(){
		String createdTime = DateHelper.getDateFormat(new Date(), "yyyyMMdd");
		
		List<AgrCrdXykCuneg> list = agrCrdXykCunegDao.findAgrCrdXykCunegs(createdTime);
		DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
		for(int i = 0; i < list.size(); i++){
			agrCrdXykCunegDao.insert(list.get(i));
		}
	}
}
