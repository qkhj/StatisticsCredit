package com.cardpay.pccredit.bank.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.bank.constant.Constants;
import com.cardpay.pccredit.bank.dao.BankDao;
import com.cardpay.pccredit.bank.dao.CustomerApplicationInfoDao;
import com.cardpay.pccredit.bank.dao.CustomerInformationDao;
import com.cardpay.pccredit.bank.id.IDGenerator;
import com.cardpay.pccredit.bank.model.AverageDailyOverdraft;
import com.cardpay.pccredit.bank.model.CardInformation;
import com.cardpay.pccredit.bank.model.CustomerAccountInfor;
import com.cardpay.pccredit.bank.model.CustomerApplicationInfo;
import com.cardpay.pccredit.bank.model.CustomerInfor;
import com.cardpay.pccredit.bank.model.CustomerOverdue;
import com.cardpay.pccredit.bank.model.CustomerOverdueHistory;
import com.cardpay.pccredit.bank.model.ProductAttribute;
import com.cardpay.pccredit.bank.model.SXykAcctCur;
import com.cardpay.pccredit.bank.model.SXykCardCur;
import com.cardpay.pccredit.bank.model.XmAccCredit;
import com.cardpay.pccredit.bank.util.AmountConfig;
import com.cardpay.pccredit.bank.util.Arith;
import com.cardpay.pccredit.bank.util.DateUtils;
import com.cardpay.pccredit.datasource.DataSourceContextHolder;
import com.wicresoft.util.date.DateHelper;
/**
 * 
 * @author shaoming
 *
 * 2014年11月27日   下午4:02:32
 */
@Service
public class BankServive extends AmountConfig{
	
	/*日志*/
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private BankDao bankDao;

	@Autowired
	private SXykAcctCurService sXykAcctCurService;

	@Autowired
	private SXykCardCurService sXykCardCurService;

	@Autowired
	private XmAccCreditService xmAccCreditService;

	@Autowired
	private CustomerApplicationInfoDao customerApplicationInfoDao;
	
	@Autowired
	private CustomerInformationDao customerInformationDao;
	/**
	 * 定时同步客户账户信息,卡信息等
	 */
	@Scheduled(cron = "${b2b.link.scheduled.day}")
	public void addCustomerMessageSync(){
		/*得到今日的所有的卡片资料*/
		List<SXykCardCur> SXykCardCurList =  sXykCardCurService.findSXykCardCur();
		for(SXykCardCur sxcc :SXykCardCurList){
			//卡号 
			String cardNbr = sxcc.getCardNbr();
			//卡片状态代码
			String canclCode = sxcc.getCanclCode();
			try{
				if(StringUtils.isNotEmpty(cardNbr)){
					/*卡号关联得到对应的账户资料信息*/
					if(DataSourceContextHolder.getDbType()==null || !DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.BANK)){
						DataSourceContextHolder.setDbType(DataSourceContextHolder.BANK);
					}
					SXykAcctCur sxac = sXykAcctCurService.findSXykAcctCurByCardNbr(cardNbr);
					//卡片激活日期
					String activeday = sxcc.getActiveday();
					if(sxac!=null){
						XmAccCredit xm = null;
						//证件号码
						String custrNbr = sxcc.getCustrNbr();
						/*若证件号码存在*/
						if(StringUtils.isNotEmpty(custrNbr)){
							/*得到产品编号*/
							String product = sxcc.getProduct();
							if(StringUtils.isNotEmpty(product)){
								/*得到台账信息,同步台账与申请件信息*/
								xm = SyncXmAccCreditAndCustomerApplicationInfo(custrNbr,product,cardNbr);
								if(xm==null){
									if(DataSourceContextHolder.getDbType()==null || !DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.BANK)){
										DataSourceContextHolder.setDbType(DataSourceContextHolder.BANK);
									}
									xm= xmAccCreditService.findXmAccCreditByCustrNbr(custrNbr);
								}
							}else{
								logger.error("卡号:"+cardNbr+" 的卡片资料字段产品编号缺失");
							}
						}
						/*同步客户经理日均额度*/
						if(xm!=null){
							//账单日
							String billDays = xm.getBillDays();
							/*当存在账单日时,进行同步客户经理日均额度*/
							if(StringUtils.isNotEmpty(billDays)){
								HandleAverageDailyOverdraft(xm,billDays);								
							}
						}
						String customerId = "";
						/*客户账户处理*/
						if(xm!=null){
							customerId = HandleCustomerAccountInfor(xm,cardNbr);
						}
						/*逾期客户处理 */
						if(xm!=null){
							/*是否逾期*/
							String flagOverdue = xm.getFlagOverdue();
							String productId = "";
							/*卡片信息*/
							String productCode =  sxcc.getProduct();
							if(DataSourceContextHolder.getDbType()==null || !DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.PCCREDIT)){
								DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
							}
							ProductAttribute productAttribute =	bankDao.findProductAttributeByCode(productCode);
							if(productAttribute!=null){
								productId = productAttribute.getId();
							}else{
								logger.error("/*卡片信息*/ 不存在产品编号为"+productCode+"的产品");
							}

							/*卡号生成 客户id和产品id存在*/
							if(productId!=null && !productId.equals("")){
								/*得到卡片信息*/
								CardInformation cardInfor = findCardInformation(cardNbr);
								
								/*卡片信息存在*/
								if(cardInfor!=null){
									/*更新操作*/
									cardInfor.setCardActivateDate(activeday);
									cardInfor.setCardStatusCode(canclCode);
									/*执行操作*/
									updateCardInformation(cardInfor);
								}else{
									/*卡片信息不存在*/
									/*添加操作*/
									cardInfor = new CardInformation();
									cardInfor.setId(IDGenerator.generateID());
									cardInfor.setCardNumber(cardNbr);
									cardInfor.setCustomerId(customerId);
									cardInfor.setProductId(productId);
									cardInfor.setCardActivateDate(activeday);
									cardInfor.setCardStatusCode(canclCode);
									cardInfor.setIdNumber(xm.getCertCode()==null?null:xm.getCertCode());
									/*执行操作*/
									insertCardInformation(cardInfor);
								}
							}
							if(StringUtils.isNotEmpty(flagOverdue)){
								HandleCustomerOverdue(sxac,xm,customerId,productId);
							}
						}
					}else{
						logger.error("卡号:"+cardNbr+" 所对应的账户资料缺失");
					}
				}else{
					logger.error("数据缺失!字段卡号不存在");
				}
			}catch(Exception e){
				logger.error("出现异常!"+e.getStackTrace());
				continue;
			}
		}
	}
	/**
	 * 同步台账与申请件信息
	 */
	private XmAccCredit SyncXmAccCreditAndCustomerApplicationInfo(String custrNbr,String product,String cardNbr){
		if(DataSourceContextHolder.getDbType()==null || !DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.BANK)){
			DataSourceContextHolder.setDbType(DataSourceContextHolder.BANK);
		}
		/*得到台账信息*/
		XmAccCredit xmAccCredit = xmAccCreditService.findXmAccCreditByCustrNbr(custrNbr);
		/*通过产品编码得到产品id*/
		if(DataSourceContextHolder.getDbType()==null || !DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.PCCREDIT)){
			DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
		}
		if(xmAccCredit!=null){
			String bankCode = xmAccCredit.getCertType()==null?null:xmAccCredit.getCertType();
			String certType = "";
			if(StringUtils.isNotEmpty(bankCode)){
				certType = customerInformationDao.getCertTypeFromCustomerInformationByBankCode(bankCode);
			}else{
				logger.error("证件号码:"+custrNbr+"贷记卡台帐表,字段证件类型数据不存在");
			}
			ProductAttribute productAttribute =	bankDao.findProductAttributeByCode(product);
			if(productAttribute!=null){
				/*得到产品id*/
				String productId = productAttribute.getId();
				if(StringUtils.isNotEmpty(productId)){
					/*通过证件号码得到该客户*/
					CustomerInfor customerInfor = bankDao.findCustomerInforByCard(certType,custrNbr);
					/*该客户存在*/
					if(customerInfor!=null){
						/*得到客户id*/
						String customerId = customerInfor.getId();
						if(StringUtils.isNotEmpty(customerId)){
							/*通过客户id和产品id得到特定状态的申请件信息*/
							CustomerApplicationInfo customerApplicationInfo = customerApplicationInfoDao.findCustomerApplicationInfoByCustomerIdAndProductId(customerId, productId);
							/*存在申请件信息*/
							if(customerApplicationInfo!=null){
								/*设置当前申请件信息的实际额度,申请状态,修改时间*/
								/*实际额度*/
								String actualQuote = xmAccCredit.getCreditLimit()==null?null:String.valueOf(xmAccCredit.getCreditLimit());
								if(StringUtils.isNotEmpty(actualQuote)){
									actualQuote = String.valueOf(Arith.mul(actualQuote, multiple));
								}else{
									actualQuote = initialAmount;
								}
								customerApplicationInfo.setActualQuote(actualQuote.substring(0,actualQuote.indexOf(".")));
								if(customerApplicationInfo.getStatus().equalsIgnoreCase(Constants.APPROVED_INTOPICES)){
									/*申请状态*/
									customerApplicationInfo.setStatus(Constants.SUCCESS_INTOPICES);
								}
								/*卡号*/
								customerApplicationInfo.setCardNumber(cardNbr);
								/*修改时间*/
								customerApplicationInfo.setModifiedTime(DateHelper.normalizeDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
								/*
								 * 执行更新操作
								 */
								customerApplicationInfoDao.updateByPrimaryKeySelective(customerApplicationInfo);
							}
						}else{
							logger.error("证件号码为"+custrNbr+" 的客户id不存在");
						}
					}else{
						logger.error("证件号码为 "+custrNbr+" 的客户不存在");
					}
				}else{
					logger.error("产品编码为"+" 的产品缺少产品id字段");
				}
			}else{
				logger.error("不存在产品编号为"+product+" 的产品");
			}
		}else{
			logger.error("不存在证件号码为"+custrNbr+" 的台账信息");
		}
		return xmAccCredit;
	}

	/*逾期客户*/
	private void HandleCustomerOverdue(SXykAcctCur sa,XmAccCredit xm,String customerId,String productId){
		String flagOverdue = xm.getFlagOverdue();
		String overdueDate =  xm.getOverdueDate();

		/*通过客户id和产品id得到逾期客户信息*/
		CustomerOverdue customerOverdue = findCustomerOverdue(customerId, productId);
		/*是逾期*/
		if(flagOverdue.equals(Constants.ISOVERDUE)){
			if(customerOverdue!=null){
				/*存在逾期客户信息*/
				String stmDay = sa.getStmDay()==null?null:sa.getStmDay();
				/*逾期天数*/
				if(StringUtils.isNotEmpty(stmDay)){
					customerOverdue.setNumberDaysOverdue(stmDay);
				}else{
					logger.error("卡号为"+sa.getCardNbr()+"的账户资料信息,字段stm_day不存在");
				}
				/*逾期金额*/
				String stmOverdue = sa.getStmOverdu()==null?null:String.valueOf(sa.getStmOverdu());
				if(StringUtils.isNotEmpty(stmOverdue)){
					/*金额单位转换*/
					stmOverdue = String.valueOf(Arith.mul(stmOverdue, multiple));
					customerOverdue.setCurrentBalance(stmOverdue.substring(0,stmOverdue.indexOf(".")));
				}else{
					logger.error("卡号为"+sa.getCardNbr()+"的账户资料信息,字段stm_overdu不存在");
				}
				if(StringUtils.isNotEmpty(overdueDate)){
					customerOverdue.setLateDate(DateHelper.getDateFormat(overdueDate,"yyyyMMdd"));					
				}else{
					logger.error("卡号为"+sa.getCardNbr()+"的账户资料信息,字段overdue_date不存在");
				}
				customerOverdue.setModifiedTime(DateHelper.normalizeDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				/*执行更新操作*/
				updateCustomerOverdue(customerOverdue);
			}else{
				/*不存在逾期客户信息*/
				customerOverdue = new CustomerOverdue();
				customerOverdue.setId(IDGenerator.generateID());
				customerOverdue.setCustomerId(customerId);
				customerOverdue.setProductId(productId);
				customerOverdue.setNumberDaysOverdue(sa.getStmDay());
				/*逾期金额*/
				String stmOverdue = sa.getStmOverdu()==null?null:String.valueOf(sa.getStmOverdu());
				if(StringUtils.isNotEmpty(stmOverdue)){
					/*金额单位转换*/
					stmOverdue = String.valueOf(Arith.mul(stmOverdue, multiple));
					customerOverdue.setCurrentBalance(stmOverdue.substring(0,stmOverdue.indexOf(".")));
				}else{
					logger.error("逾期客户插入操作,卡号为"+sa.getCardNbr()+"的账户资料信息,字段stm_overdu不存在");
				}
				if(StringUtils.isNotEmpty(overdueDate)){
					customerOverdue.setLateDate(DateHelper.getDateFormat(overdueDate,"yyyyMMdd"));
				}else{
					logger.error("逾期客户插入操作,卡号为"+sa.getCardNbr()+"的账户资料信息,字段overdue_date不存在");
				}
				customerOverdue.setCreatedTime(DateHelper.normalizeDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				/*执行插入操作*/
				insertCustomerOverdue(customerOverdue);
			}
			/*不是逾期*/
		}else if(flagOverdue.equals(Constants.NOOVERDUE)){
			/*之前是逾期现在不是*/
			if(customerOverdue!=null){
				//逾期客户信息存在,数据转移到历史表中
				transferhistoricalToHistory(customerOverdue);
			}
		}
	}
	/*逾期客户现表移动到逾期客户历史表*/
	public int transferhistoricalToHistory(CustomerOverdue customerOverdue){
		CustomerOverdueHistory history = new CustomerOverdueHistory();
		history.setCreatedTime(DateHelper.normalizeDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		history.setId(IDGenerator.generateID());
		history.setCustomerId(customerOverdue.getCustomerId()==null?"":customerOverdue.getCustomerId());
		history.setProductId(customerOverdue.getProductId()==null?"":customerOverdue.getProductId());
		history.setNumberDaysOverdue(customerOverdue.getNumberDaysOverdue()==null?"":customerOverdue.getNumberDaysOverdue());
		history.setOverdue_amount(customerOverdue.getCurrentBalance()==null?"":customerOverdue.getCurrentBalance());
		history.setLateDate(customerOverdue.getLateDate()==null?null:customerOverdue.getLateDate());
		/*先移动数据到历史表中*/
		bankDao.transferhistoricalToHistory(history);
		String id = customerOverdue.getId();
		int i = 0;
		/*删除现表中数据*/
		if(StringUtils.isNotEmpty(id)){
			i = bankDao.deleteCustomerOverdue(id);
		}
		return i;
	}
	/*插入逾期客户*/
	public int insertCustomerOverdue(CustomerOverdue customerOverdue){
		return bankDao.insertCustomerOverdue(customerOverdue);
	}
	/*更新逾期客户*/
	public int updateCustomerOverdue(CustomerOverdue customerOverdue){
		return bankDao.updateCustomerOverdue(customerOverdue);
	}
	public CustomerOverdue findCustomerOverdue(String customerId,String productId){
		return bankDao.findCustomerOverdue(customerId, productId);
	}
	/*插入卡信息*/
	public int insertCardInformation(CardInformation cardInfor){
		return bankDao.insertCardInformation(cardInfor);
	}
	/*更新卡信息*/
	public int updateCardInformation(CardInformation cardInfor){
		return bankDao.updateCardInformation(cardInfor);
	}
	public CardInformation findCardInformation(String cardNbr){
		return bankDao.findCardInformation(cardNbr);
	}


	/*客户账户信息*/
	private String HandleCustomerAccountInfor(XmAccCredit xm,String cardNbr){
		if(DataSourceContextHolder.getDbType()!=null &&!DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.PCCREDIT)){
			DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
		}
		//账号
		String loanAccount = xm.getLoanAccount();
		/*客户id*/
		String customerId = "";
		/*客户账户信息*/
		CustomerAccountInfor accountInfor = null;
		if(StringUtils.isNotEmpty(loanAccount)){
			//信用额度
			String creditLimit = xm.getCreditLimit()==null?null:String.valueOf(xm.getCreditLimit());
			//账户状态
			String accountState = xm.getAccountState();
			//账单日
			String billDays = xm.getBillDays();

			accountInfor = findCustomerAccountInfor(cardNbr);
			if(accountInfor!=null){
				customerId = accountInfor.getCustomerId();
				/*更新操作*/
				if(StringUtils.isNotEmpty(creditLimit)){
					/*金额单位转换*/
					creditLimit = String.valueOf(Arith.mul(creditLimit, multiple));
					accountInfor.setCreditAmount(creditLimit.substring(0,creditLimit.indexOf(".")));
				}else{
					accountInfor.setCreditAmount(initialAmount.substring(0,initialAmount.indexOf(".")));
				}
				accountInfor.setAccountStatus(accountState);
				accountInfor.setStatementDate(billDays);
				/*执行更新*/
				updateCustomerAccountInfor(accountInfor);
			}else{
				/*插入操作*/
				/*证件类型*/
				String bankCode = xm.getCertType();
				/*证件号码*/
				String certCode = xm.getCertCode();
				String certType = "";
				if(StringUtils.isNotEmpty(bankCode)){
					certType = customerInformationDao.getCertTypeFromCustomerInformationByBankCode(bankCode);
				}
				/*得到客户信息*/
				CustomerInfor customerInfor = bankDao.findCustomerInforByCard(certType,certCode);
				if(customerInfor!=null){
					customerId = customerInfor.getId();
					if(StringUtils.isNotEmpty(customerId)){
						accountInfor = new CustomerAccountInfor();
						accountInfor.setId(IDGenerator.generateID());
						accountInfor.setCustomerId(customerId);
						if(StringUtils.isNotEmpty(creditLimit)){
							/*金额单位转换*/
							creditLimit = String.valueOf(Arith.mul(creditLimit, multiple));
							accountInfor.setCreditAmount(creditLimit.substring(0,creditLimit.indexOf(".")));
						}else{
							accountInfor.setCreditAmount(initialAmount.substring(0,initialAmount.indexOf(".")));
						}
						accountInfor.setCardNumber(cardNbr);
						accountInfor.setAccountStatus(accountState);
						accountInfor.setStatementDate(billDays);
					}
					/*执行添加*/
					insertCustomerAccountInfor(accountInfor);
				}
			}
		}
		return customerId;
	}
	public CustomerAccountInfor findCustomerAccountInfor(String cardNumber){
		return bankDao.findCustomerAccountInfor(cardNumber);
	}
	/*插入客户账户信息*/
	public int insertCustomerAccountInfor(CustomerAccountInfor accountInfor){
		return bankDao.insertCustomerAccountInfor(accountInfor);
	}
	/*更新客户账户信息*/
	public int updateCustomerAccountInfor(CustomerAccountInfor accountInfor){
		return bankDao.updateCustomerAccountInfor(accountInfor);
	}
	/*客户经理统计每一月日均透支额度*/
	private void HandleAverageDailyOverdraft(XmAccCredit xm,String billDays){
		AverageDailyOverdraft averageDailyOverdraft = null;
		/*账号*/
		String loanAccount = xm.getLoanAccount();
		if(StringUtils.isNotEmpty(loanAccount)){
			//总余额
			String totalBalance = xm.getTotalBalance()==null?null:String.valueOf(xm.getTotalBalance());
			if(StringUtils.isEmpty(totalBalance)){
				totalBalance = initialAmount;
			}
			//透支本金
			String overdrawAmount = xm.getOverdrawAmount()==null?null:String.valueOf(xm.getOverdrawAmount());
			if(StringUtils.isEmpty(overdrawAmount)){
				overdrawAmount = initialAmount;
			}
			//已全额还款标志
			String flagRepayFull = xm.getFlagRepayFull();
			if(DataSourceContextHolder.getDbType()==null || !DataSourceContextHolder.getDbType().equalsIgnoreCase(DataSourceContextHolder.PCCREDIT)){
				DataSourceContextHolder.setDbType(DataSourceContextHolder.PCCREDIT);
			}
			averageDailyOverdraft = findAverageDailyOverdraftByAccountNumber(loanAccount,billDays);
			if(averageDailyOverdraft!=null){
				/*更新操作*/
				/*透支本金金额*/
				String principalOverdraft = averageDailyOverdraft.getPrincipalOverdraft()==null?null:averageDailyOverdraft.getPrincipalOverdraft();
				if(StringUtils.isEmpty(principalOverdraft)){
					principalOverdraft = initialAmount;
				}
				String i = String.valueOf(Arith.add(principalOverdraft, overdrawAmount));
				/*金额单位转换*/
				i = String.valueOf(Arith.mul(i, multiple));
				averageDailyOverdraft.setPrincipalOverdraft(i.substring(0,i.indexOf(".")));
				/*透支总金额*/
				String totalAmountOverdraft = averageDailyOverdraft.getTotalAmountOverdraft()==null?null:averageDailyOverdraft.getTotalAmountOverdraft();
				if(StringUtils.isEmpty(totalAmountOverdraft)){
					totalAmountOverdraft = initialAmount;
				}
				String t = String.valueOf(Arith.add(totalAmountOverdraft,totalBalance));
				/*金额单位转换*/
				t = String.valueOf(Arith.mul(t, multiple));
				averageDailyOverdraft.setTotalAmountOverdraft(t.substring(0,t.indexOf(".")));
				/*日均透支金额*/
				//得到现在时间
				Date today = DateHelper.normalizeDate(new Date(), "yyyy-MM-dd");
				//得到创建时间
				Date createdTime = averageDailyOverdraft.getCreatedTime();
				int day = 0;
				if(createdTime!=null){
					day = DateUtils.getTimeInterval(createdTime, today)+1;
				}
				String overdraft = String.valueOf(Arith.div(String.valueOf(i), String.valueOf(day)));
				/*金额单位转换*/
				overdraft = String.valueOf(Arith.mul(overdraft, multiple));
				averageDailyOverdraft.setAverageDailyOverdraft(overdraft.substring(0,overdraft.indexOf(".")));
				averageDailyOverdraft.setAllDueStatus(flagRepayFull);
				/*执行*/
				updateAverageDailyOverdraft(averageDailyOverdraft);
			}else{
				/*插入操作*/
				/*创建AverageDailyOverdraft对象*/
				averageDailyOverdraft = new AverageDailyOverdraft();
				/*id*/
				averageDailyOverdraft.setId(IDGenerator.generateID());
				/*账号*/
				averageDailyOverdraft.setAccountNumber(loanAccount);
				if(StringUtils.isNotEmpty(overdrawAmount)){
					/*金额单位转换*/
					overdrawAmount = String.valueOf(Arith.mul(overdrawAmount, multiple));
					/*日均透支金额*/
					averageDailyOverdraft.setAverageDailyOverdraft(overdrawAmount.substring(0,overdrawAmount.indexOf(".")));
					/*透支本金金额*/
					averageDailyOverdraft.setPrincipalOverdraft(overdrawAmount.substring(0,overdrawAmount.indexOf(".")));
				}
				if(StringUtils.isNotEmpty(totalBalance)){
					/*金额单位转换*/
					totalBalance = String.valueOf(Arith.mul(totalBalance, multiple));
					/*透支总金额*/
					averageDailyOverdraft.setTotalAmountOverdraft(totalBalance.substring(0,totalBalance.indexOf(".")));
				}
				averageDailyOverdraft.setAllDueStatus(flagRepayFull);
				/*创建时间*/
				averageDailyOverdraft.setCreatedTime(DateHelper.normalizeDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				Calendar cal=Calendar.getInstance();
				int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
				/*字段 Month*/
				int month = cal.get(Calendar.MONTH)+1;;
				/*字段 Year*/
				int year = cal.get(Calendar.YEAR);
				/*当日大于账单日,月份为下月*/
				if(dayOfMonth>Integer.valueOf(billDays)){
					month += 1;
					/*例:12月 下月为次年1月*/
					if(month>12){
						year += 1;
						month -= 12;
					}
				}
				averageDailyOverdraft.setYear(String.valueOf(year));
				averageDailyOverdraft.setMonth(String.valueOf(month));
				/*执行*/
				insertAverageDailyOverdraft(averageDailyOverdraft);
			}
		}
	}
	/*插入客户经理统计每一月日均透支额度 */
	public int insertAverageDailyOverdraft(AverageDailyOverdraft averageDailyOverdraft){

		return bankDao.insertAverageDailyOverdraft(averageDailyOverdraft);
	}
	/*得到账号为accountNumber的AverageDailyOverdraft信息*/
	public AverageDailyOverdraft findAverageDailyOverdraftByAccountNumber(String accountNumber,String billDays){
		Calendar cal=Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		/*字段 Month*/
		int month = cal.get(Calendar.MONTH)+1;;
		/*字段 Year*/
		int year = cal.get(Calendar.YEAR);
		/*当日大于账单日,月份为下月*/
		if(dayOfMonth>Integer.valueOf(billDays)){
			month += 1;
			/*例:12月 下月为次年1月*/
			if(month>12){
				year += 1;
				month -= 12;
			}
		}
		return bankDao.findAverageDailyOverdraftByAccountNumber(accountNumber,year,month);
	}
	public int updateAverageDailyOverdraft(AverageDailyOverdraft averageDailyOverdraft){
		return bankDao.updateAverageDailyOverdraft(averageDailyOverdraft);
	}
	
}
