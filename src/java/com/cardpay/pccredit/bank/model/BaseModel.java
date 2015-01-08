/**
 * 
 */
package com.cardpay.pccredit.bank.model;

import java.util.Date;

/**
 * @author shaoming
 *
 * 2014年11月28日   上午10:56:41
 */
public class BaseModel {
	private Date createdTime;
	private String createdBy;
	private Date modifiedTime;
	private String modifiedBy;
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
