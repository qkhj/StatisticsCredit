import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cardpay.pccredit.bank.service.BankService;
import com.cardpay.pccredit.bank.service.BankServive;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:database-beans.xml")
public class DmoTest {

	@Autowired
	private BankServive bankServive;
	
	@Autowired
	private BankService bankService;

	@Test
	public void testSync() {
//		String s1 = DataSourceContextHolder.getDbType();
//		System.out.println("s1 "+s1);
//		DataSourceContextHolder.setDbType(DataSourceContextHolder.BANK);
//		String s2 = DataSourceContextHolder.getDbType();
//		System.out.println("s2 "+s2);
//		DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
//		String s3 = DataSourceContextHolder.getDbType();
//		System.out.println(s3);
//		Calendar cal=Calendar.getInstance();
//		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
//		System.out.println(dayOfMonth);
//		DataSourceContextHolder.setDbType(DataSourceContextHolder.BANK);
		bankServive.addCustomerMessageSync();
	}
	@Test
	public void testSync2() {

		System.out.println(Object.class.getResource("/"));
//		bankService.addSXykStmtCurSync();
//		DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
//		CustomerCardInformation card = new CustomerCardInformation();
//		
//		bankService.insertCustomerCardInformation(card);
	}
	@Test
	public void testJob() throws InterruptedException, SchedulerException {
		//基于spring配置job、trigger、scheduler之间的关联关系
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("database-beans.xml");
		Scheduler scheduler = (Scheduler) context.getBean("scheduler");
		Thread.sleep(20000);// 等待20秒
		scheduler.shutdown();// 关闭调度程序

		SchedulerMetaData metaData = scheduler.getMetaData();
		System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
		context.close();
	}
}
