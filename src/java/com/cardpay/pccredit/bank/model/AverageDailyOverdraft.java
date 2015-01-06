/**
 * 
 */
package com.cardpay.pccredit.bank.model;

import java.util.Date;


/**
 * @author shaoming
 *
 * 2014年11月27日   上午11:36:02
 */
public class AverageDailyOverdraft {
	private String id;
	private String accountNumber;
	private String averageDailyOverdraft;
	private String principalOverdraft;
	private String totalAmountOverdraft;
	private String allDueStatus;
	private String lowDueStatus;
	private String periods;
	private String month;
	private String year;
	private Date createdTime;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAverageDailyOverdraft() {
		return averageDailyOverdraft;
	}
	public void setAverageDailyOverdraft(String averageDailyOverdraft) {
		this.averageDailyOverdraft = averageDailyOverdraft;
	}
	public String getPrincipalOverdraft() {
		return principalOverdraft;
	}
	public void setPrincipalOverdraft(String principalOverdraft) {
		this.principalOverdraft = principalOverdraft;
	}
	public String getTotalAmountOverdraft() {
		return totalAmountOverdraft;
	}
	public void setTotalAmountOverdraft(String totalAmountOverdraft) {
		this.totalAmountOverdraft = totalAmountOverdraft;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAllDueStatus() {
		return allDueStatus;
	}
	public void setAllDueStatus(String allDueStatus) {
		this.allDueStatus = allDueStatus;
	}
	public String getLowDueStatus() {
		return lowDueStatus;
	}
	public void setLowDueStatus(String lowDueStatus) {
		this.lowDueStatus = lowDueStatus;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
}
