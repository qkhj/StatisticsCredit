/**
 * 
 */
package com.cardpay.pccredit.bank.model;

import java.util.Date;

/**
 * @author shaoming
 *
 * 2014年12月1日   上午11:04:35
 */
public class CustomerOverdueHistory extends BaseModel{
	private String id;
	private String customerId;
	private String productId;
	private String numberDaysOverdue;
	private String overdue_amount;
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
	public String getOverdue_amount() {
		return overdue_amount;
	}
	public void setOverdue_amount(String overdue_amount) {
		this.overdue_amount = overdue_amount;
	}
	public Date getLateDate() {
		return lateDate;
	}
	public void setLateDate(Date lateDate) {
		this.lateDate = lateDate;
	}
	
}
