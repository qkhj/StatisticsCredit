package com.cardpay.pccredit.bank.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:database-beans.xml")
public class SynCustomerInforServiceTest {

	@Autowired
	private SynCustomerInfoService synCustomerInforService;
	
	@Test
	public void synCustomerInfoTest(){
		synCustomerInforService.synCustomerInfo();
	}
}
