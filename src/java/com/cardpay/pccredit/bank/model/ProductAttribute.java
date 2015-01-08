/**
 * 
 */
package com.cardpay.pccredit.bank.model;


/**
 * @author shaoming
 *
 * 2014年11月28日   上午10:45:20
 */
public class ProductAttribute extends BaseModel{
	private String id;
	private String productName;
	private String purposeLoan;
	private String creditLine;
	private String rateRange;
	private String productTypeCode;
	private String loanTimeLimit;
	private String loanPrincipal;
	private String letterPaymentWay;
	private String numbererestSettlementWay;
	private String assureMeans;
	private String penaltyNumbererest;
	private String productRiskTolerance;
	private String status;
	private String seqno;
	private String awaitAudit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPurposeLoan() {
		return purposeLoan;
	}
	public void setPurposeLoan(String purposeLoan) {
		this.purposeLoan = purposeLoan;
	}
	public String getCreditLine() {
		return creditLine;
	}
	public void setCreditLine(String creditLine) {
		this.creditLine = creditLine;
	}
	public String getRateRange() {
		return rateRange;
	}
	public void setRateRange(String rateRange) {
		this.rateRange = rateRange;
	}
	public String getProductTypeCode() {
		return productTypeCode;
	}
	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}
	public String getLoanTimeLimit() {
		return loanTimeLimit;
	}
	public void setLoanTimeLimit(String loanTimeLimit) {
		this.loanTimeLimit = loanTimeLimit;
	}
	public String getLoanPrincipal() {
		return loanPrincipal;
	}
	public void setLoanPrincipal(String loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}
	public String getLetterPaymentWay() {
		return letterPaymentWay;
	}
	public void setLetterPaymentWay(String letterPaymentWay) {
		this.letterPaymentWay = letterPaymentWay;
	}
	public String getNumbererestSettlementWay() {
		return numbererestSettlementWay;
	}
	public void setNumbererestSettlementWay(String numbererestSettlementWay) {
		this.numbererestSettlementWay = numbererestSettlementWay;
	}
	public String getAssureMeans() {
		return assureMeans;
	}
	public void setAssureMeans(String assureMeans) {
		this.assureMeans = assureMeans;
	}
	public String getPenaltyNumbererest() {
		return penaltyNumbererest;
	}
	public void setPenaltyNumbererest(String penaltyNumbererest) {
		this.penaltyNumbererest = penaltyNumbererest;
	}
	public String getProductRiskTolerance() {
		return productRiskTolerance;
	}
	public void setProductRiskTolerance(String productRiskTolerance) {
		this.productRiskTolerance = productRiskTolerance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getAwaitAudit() {
		return awaitAudit;
	}
	public void setAwaitAudit(String awaitAudit) {
		this.awaitAudit = awaitAudit;
	}
}
