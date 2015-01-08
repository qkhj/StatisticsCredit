package com.cardpay.pccredit.bank.model;

import java.util.Date;

public class CustomerCareersInformation{

	private String id;
	
	private String customerId;
	
	private String customerCode;

    private String nameUnit;

    private String departmentName;

    private String unitAddress;

    private String zipCode;

    private String unitPhone;

    private String unitNature;

    private String industryType;

    private String positio;

    private String title;

    private String annualIncome;

    private String yearWorkUnit;

    private String beforeNameUnit;

    private String beforeYearWorkUnit;

    private String workTime;
    
    private String createdBy;

    private Date createdTime;

    private String modifiedBy;

    private Date modifiedTime;

    public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getNameUnit() {
        return nameUnit;
    }

    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit == null ? null : nameUnit.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress == null ? null : unitAddress.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone == null ? null : unitPhone.trim();
    }

    public String getUnitNature() {
        return unitNature;
    }

    public void setUnitNature(String unitNature) {
        this.unitNature = unitNature == null ? null : unitNature.trim();
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType == null ? null : industryType.trim();
    }

    public String getPositio() {
        return positio;
    }

    public void setPositio(String positio) {
        this.positio = positio == null ? null : positio.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome == null ? null : annualIncome.trim();
    }

    public String getYearWorkUnit() {
		return yearWorkUnit;
	}

	public void setYearWorkUnit(String yearWorkUnit) {
		this.yearWorkUnit = yearWorkUnit;
	}

	public String getBeforeNameUnit() {
        return beforeNameUnit;
    }

    public void setBeforeNameUnit(String beforeNameUnit) {
        this.beforeNameUnit = beforeNameUnit == null ? null : beforeNameUnit.trim();
    }

    public String getBeforeYearWorkUnit() {
        return beforeYearWorkUnit;
    }

    public void setBeforeYearWorkUnit(String beforeYearWorkUnit) {
        this.beforeYearWorkUnit = beforeYearWorkUnit == null ? null : beforeYearWorkUnit.trim();
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime == null ? null : workTime.trim();
    }

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
    
	
}