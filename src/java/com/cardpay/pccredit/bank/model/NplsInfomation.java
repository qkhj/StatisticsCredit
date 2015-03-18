/**
 * 
 */
package com.cardpay.pccredit.bank.model;

import java.util.Date;

/**
 * 描述 ：不良资产model
 * @author 张石树
 *
 * 2014-11-4 上午9:33:30
 */
public class NplsInfomation extends BaseModel{
	private String id;
	private String customerId;
	
	private String accountId;
	
	private String reviewResults;
	//系统自动匹配、卡中心手动添加（创建方式）
	private String createMethod;
	
	private Date verificationTime;
	
	private String verificationStatus;
	
	private String verificationType;
	
	private String verificationAccount;
	
	private String overdueBalanceIn;
	private String overdueIntIn;
	private String levelFiveReason;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getReviewResults() {
		return reviewResults;
	}

	public void setReviewResults(String reviewResults) {
		this.reviewResults = reviewResults;
	}

	public String getCreateMethod() {
		return createMethod;
	}

	public void setCreateMethod(String createMethod) {
		this.createMethod = createMethod;
	}

	public Date getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(Date verificationTime) {
		this.verificationTime = verificationTime;
	}

	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getVerificationType() {
		return verificationType;
	}

	public void setVerificationType(String verificationType) {
		this.verificationType = verificationType;
	}

	public String getVerificationAccount() {
		return verificationAccount;
	}

	public void setVerificationAccount(String verificationAccount) {
		this.verificationAccount = verificationAccount;
	}

	public String getOverdueBalanceIn() {
		return overdueBalanceIn;
	}

	public void setOverdueBalanceIn(String overdueBalanceIn) {
		this.overdueBalanceIn = overdueBalanceIn;
	}

	public String getOverdueIntIn() {
		return overdueIntIn;
	}

	public void setOverdueIntIn(String overdueIntIn) {
		this.overdueIntIn = overdueIntIn;
	}

	public String getLevelFiveReason() {
		return levelFiveReason;
	}

	public void setLevelFiveReason(String levelFiveReason) {
		this.levelFiveReason = levelFiveReason;
	}
	
}
