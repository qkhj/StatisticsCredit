/**
 * 
 */
package com.cardpay.pccredit.bank.model;

import java.util.Date;

/**
 * @author shaoming
 *
 * 2014年11月28日   上午10:53:16
 */
public class CustomerOverdue extends BaseModel{
	private String id;
	private String customerId;
	private String productId;
	private String numberDaysOverdue;
	private String currentBalance;
	private String minimumPayment;
	private String reminder;
	private String reminderWay;
	private Date reminderDate;
	private String overduePaybackAll;
	private Date lateDate;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getNumberDaysOverdue() {
		return numberDaysOverdue;
	}
	public void setNumberDaysOverdue(String numberDaysOverdue) {
		this.numberDaysOverdue = numberDaysOverdue;
	}
	public String getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getMinimumPayment() {
		return minimumPayment;
	}
	public void setMinimumPayment(String minimumPayment) {
		this.minimumPayment = minimumPayment;
	}
	public String getReminder() {
		return reminder;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	public String getReminderWay() {
		return reminderWay;
	}
	public void setReminderWay(String reminderWay) {
		this.reminderWay = reminderWay;
	}
	public Date getReminderDate() {
		return reminderDate;
	}
	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}
	public String getOverduePaybackAll() {
		return overduePaybackAll;
	}
	public void setOverduePaybackAll(String overduePaybackAll) {
		this.overduePaybackAll = overduePaybackAll;
	}
	public Date getLateDate() {
		return lateDate;
	}
	public void setLateDate(Date lateDate) {
		this.lateDate = lateDate;
	}
	
}
