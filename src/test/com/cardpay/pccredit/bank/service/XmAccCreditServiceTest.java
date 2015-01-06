package com.cardpay.pccredit.bank.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chenzhifang
 *
 * 2014-12-3下午4:47:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:database-beans.xml")
public class XmAccCreditServiceTest {

	@Autowired
	private XmAccCreditService xmAccCreditService;
	
	@Autowired
	private SXykAcctCurService sXykAcctCurService;
	
	@Autowired
	private SXykCardCurService sXykCardCurService;
	
	@Test
	public void test() {
		try {
			//new ImportBankDataFileTools().parse("sXykCardCur.xml");
			// 贷记卡台帐表
			String fileName = "C:/Users/Administrator/Desktop/datafile/STA_902_cmis_ACC_CREDIT_ADD_20141127.dat";
			//xmAccCreditService.saveXmAccCreditDataFile(fileName);
			// 账户资料表  
			fileName = "C:/Users/Administrator/Desktop/datafile/STA_902_djk_STA_YW_DJK_ACCT_ADD_20141127.dat";
			//sXykAcctCurService.saveSXykAcctCurDataFile(fileName);
			// 卡片资料表
			fileName = "C:/Users/Administrator/Desktop/datafile/STA_902_djk_STA_YW_DJK_CARD_ADD_20141127.dat";
			sXykCardCurService.saveSXykCardCurDataFile(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
