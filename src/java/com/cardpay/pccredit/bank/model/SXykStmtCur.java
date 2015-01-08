package com.cardpay.pccredit.bank.model;

import java.math.BigDecimal;

public class SXykStmtCur {
	private String createdTime;
	
    private Integer xaccount;

    private Short bank;

    private String cardNbr;

    private Short cycleNbr;

    private Short monthNbr;

    private Short postDd;

    private Short mthsOdue;

    private String payFlag;

    private BigDecimal age1;

    private BigDecimal age2;

    private BigDecimal age3;

    private BigDecimal age4;

    private BigDecimal age5;

    private BigDecimal age6;

    private BigDecimal balCmpint;

    private BigDecimal balFree;

    private BigDecimal balInt;

    private String balintflag;

    private BigDecimal balNoint;

    private BigDecimal balOrint;

    private Short branch;

    private String business;

    private BigDecimal cardFees;

    private BigDecimal cashAdfee;

    private BigDecimal cashAdvce;

    private BigDecimal closeBal;

    private String clsbalFlag;

    private String closeCode;

    private String closeDate;

    private BigDecimal credAdj;

    private Long credLimit;

    private BigDecimal credVouch;

    private Integer credlimX;

    private Short cyChgcnt;

    private Integer cyChgday;

    private Integer cyEffday;

    private Short cycleNew;

    private Short dayspay;

    private BigDecimal debitAdj;

    private String duncode1;

    private String duncode2;

    private String duncodeb;

    private String dunletr1;

    private String dunletr2;

    private String dunletrb;

    private BigDecimal dutyCredt;

    private BigDecimal dutyDebit;

    private BigDecimal feesTaxes;

    private BigDecimal instlAmt;

    private BigDecimal intChdcmp;

    private BigDecimal intChgd;

    private BigDecimal intCmpond;

    private BigDecimal intCunot;

    private BigDecimal intCurcmp;

    private BigDecimal intEarned;

    private BigDecimal intRate;

    private BigDecimal intTaxrte;

    private String letrCode1;

    private String letrCode2;

    private BigDecimal losses;

    private BigDecimal minDue;

    private String minDueDt;

    private BigDecimal mindueR;

    private String msgKey;

    private Short nbrCashad;

    private Short nbrFeedty;

    private Short nbrOthers;

    private Short nbrPaymnt;

    private Short nbrPurch;

    private Short odueFlag;

    private BigDecimal odueHeld;

    private BigDecimal openBal;

    private String opbalFlag;

    private String openDate;

    private BigDecimal otherFees;

    private BigDecimal payment;

    private BigDecimal paymtUncl;

    private BigDecimal penChrg;

    private Long pointAdj;

    private String ptadjFlag;

    private Long pointClm;

    private Long pointCum;

    private String ptflag;

    private Long pointEar;

    private Short priorNo;

    private BigDecimal purchases;

    private BigDecimal queryAmt;

    private String queryCode;

    private BigDecimal recvryAmt;

    private BigDecimal revcryAmt;

    private BigDecimal stmAmtOl;

    private String stmtCode;

    private BigDecimal balMp;

    private BigDecimal balNint01;

    private BigDecimal balNint02;

    private BigDecimal balNint03;

    private BigDecimal balNint04;

    private BigDecimal balNint05;

    private BigDecimal balNint06;

    private BigDecimal balNint07;

    private BigDecimal balNint08;

    private BigDecimal balNint09;

    private BigDecimal balNint10;

    private BigDecimal balCmpfee;

    private String orgNo;

    public Integer getXaccount() {
        return xaccount;
    }

    public void setXaccount(Integer xaccount) {
        this.xaccount = xaccount;
    }

    public Short getBank() {
        return bank;
    }

    public void setBank(Short bank) {
        this.bank = bank;
    }

    public String getCardNbr() {
        return cardNbr;
    }

    public void setCardNbr(String cardNbr) {
        this.cardNbr = cardNbr == null ? null : cardNbr.trim();
    }

    public Short getCycleNbr() {
        return cycleNbr;
    }

    public void setCycleNbr(Short cycleNbr) {
        this.cycleNbr = cycleNbr;
    }

    public Short getMonthNbr() {
        return monthNbr;
    }

    public void setMonthNbr(Short monthNbr) {
        this.monthNbr = monthNbr;
    }

    public Short getPostDd() {
        return postDd;
    }

    public void setPostDd(Short postDd) {
        this.postDd = postDd;
    }

    public Short getMthsOdue() {
        return mthsOdue;
    }

    public void setMthsOdue(Short mthsOdue) {
        this.mthsOdue = mthsOdue;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag == null ? null : payFlag.trim();
    }

    public BigDecimal getAge1() {
        return age1;
    }

    public void setAge1(BigDecimal age1) {
        this.age1 = age1;
    }

    public BigDecimal getAge2() {
        return age2;
    }

    public void setAge2(BigDecimal age2) {
        this.age2 = age2;
    }

    public BigDecimal getAge3() {
        return age3;
    }

    public void setAge3(BigDecimal age3) {
        this.age3 = age3;
    }

    public BigDecimal getAge4() {
        return age4;
    }

    public void setAge4(BigDecimal age4) {
        this.age4 = age4;
    }

    public BigDecimal getAge5() {
        return age5;
    }

    public void setAge5(BigDecimal age5) {
        this.age5 = age5;
    }

    public BigDecimal getAge6() {
        return age6;
    }

    public void setAge6(BigDecimal age6) {
        this.age6 = age6;
    }

    public BigDecimal getBalCmpint() {
        return balCmpint;
    }

    public void setBalCmpint(BigDecimal balCmpint) {
        this.balCmpint = balCmpint;
    }

    public BigDecimal getBalFree() {
        return balFree;
    }

    public void setBalFree(BigDecimal balFree) {
        this.balFree = balFree;
    }

    public BigDecimal getBalInt() {
        return balInt;
    }

    public void setBalInt(BigDecimal balInt) {
        this.balInt = balInt;
    }

    public String getBalintflag() {
        return balintflag;
    }

    public void setBalintflag(String balintflag) {
        this.balintflag = balintflag == null ? null : balintflag.trim();
    }

    public BigDecimal getBalNoint() {
        return balNoint;
    }

    public void setBalNoint(BigDecimal balNoint) {
        this.balNoint = balNoint;
    }

    public BigDecimal getBalOrint() {
        return balOrint;
    }

    public void setBalOrint(BigDecimal balOrint) {
        this.balOrint = balOrint;
    }

    public Short getBranch() {
        return branch;
    }

    public void setBranch(Short branch) {
        this.branch = branch;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public BigDecimal getCardFees() {
        return cardFees;
    }

    public void setCardFees(BigDecimal cardFees) {
        this.cardFees = cardFees;
    }

    public BigDecimal getCashAdfee() {
        return cashAdfee;
    }

    public void setCashAdfee(BigDecimal cashAdfee) {
        this.cashAdfee = cashAdfee;
    }

    public BigDecimal getCashAdvce() {
        return cashAdvce;
    }

    public void setCashAdvce(BigDecimal cashAdvce) {
        this.cashAdvce = cashAdvce;
    }

    public BigDecimal getCloseBal() {
        return closeBal;
    }

    public void setCloseBal(BigDecimal closeBal) {
        this.closeBal = closeBal;
    }

    public String getClsbalFlag() {
        return clsbalFlag;
    }

    public void setClsbalFlag(String clsbalFlag) {
        this.clsbalFlag = clsbalFlag == null ? null : clsbalFlag.trim();
    }

    public String getCloseCode() {
        return closeCode;
    }

    public void setCloseCode(String closeCode) {
        this.closeCode = closeCode == null ? null : closeCode.trim();
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate == null ? null : closeDate.trim();
    }

    public BigDecimal getCredAdj() {
        return credAdj;
    }

    public void setCredAdj(BigDecimal credAdj) {
        this.credAdj = credAdj;
    }

    public Long getCredLimit() {
        return credLimit;
    }

    public void setCredLimit(Long credLimit) {
        this.credLimit = credLimit;
    }

    public BigDecimal getCredVouch() {
        return credVouch;
    }

    public void setCredVouch(BigDecimal credVouch) {
        this.credVouch = credVouch;
    }

    public Integer getCredlimX() {
        return credlimX;
    }

    public void setCredlimX(Integer credlimX) {
        this.credlimX = credlimX;
    }

    public Short getCyChgcnt() {
        return cyChgcnt;
    }

    public void setCyChgcnt(Short cyChgcnt) {
        this.cyChgcnt = cyChgcnt;
    }

    public Integer getCyChgday() {
        return cyChgday;
    }

    public void setCyChgday(Integer cyChgday) {
        this.cyChgday = cyChgday;
    }

    public Integer getCyEffday() {
        return cyEffday;
    }

    public void setCyEffday(Integer cyEffday) {
        this.cyEffday = cyEffday;
    }

    public Short getCycleNew() {
        return cycleNew;
    }

    public void setCycleNew(Short cycleNew) {
        this.cycleNew = cycleNew;
    }

    public Short getDayspay() {
        return dayspay;
    }

    public void setDayspay(Short dayspay) {
        this.dayspay = dayspay;
    }

    public BigDecimal getDebitAdj() {
        return debitAdj;
    }

    public void setDebitAdj(BigDecimal debitAdj) {
        this.debitAdj = debitAdj;
    }

    public String getDuncode1() {
        return duncode1;
    }

    public void setDuncode1(String duncode1) {
        this.duncode1 = duncode1 == null ? null : duncode1.trim();
    }

    public String getDuncode2() {
        return duncode2;
    }

    public void setDuncode2(String duncode2) {
        this.duncode2 = duncode2 == null ? null : duncode2.trim();
    }

    public String getDuncodeb() {
        return duncodeb;
    }

    public void setDuncodeb(String duncodeb) {
        this.duncodeb = duncodeb == null ? null : duncodeb.trim();
    }

    public String getDunletr1() {
        return dunletr1;
    }

    public void setDunletr1(String dunletr1) {
        this.dunletr1 = dunletr1 == null ? null : dunletr1.trim();
    }

    public String getDunletr2() {
        return dunletr2;
    }

    public void setDunletr2(String dunletr2) {
        this.dunletr2 = dunletr2 == null ? null : dunletr2.trim();
    }

    public String getDunletrb() {
        return dunletrb;
    }

    public void setDunletrb(String dunletrb) {
        this.dunletrb = dunletrb == null ? null : dunletrb.trim();
    }

    public BigDecimal getDutyCredt() {
        return dutyCredt;
    }

    public void setDutyCredt(BigDecimal dutyCredt) {
        this.dutyCredt = dutyCredt;
    }

    public BigDecimal getDutyDebit() {
        return dutyDebit;
    }

    public void setDutyDebit(BigDecimal dutyDebit) {
        this.dutyDebit = dutyDebit;
    }

    public BigDecimal getFeesTaxes() {
        return feesTaxes;
    }

    public void setFeesTaxes(BigDecimal feesTaxes) {
        this.feesTaxes = feesTaxes;
    }

    public BigDecimal getInstlAmt() {
        return instlAmt;
    }

    public void setInstlAmt(BigDecimal instlAmt) {
        this.instlAmt = instlAmt;
    }

    public BigDecimal getIntChdcmp() {
        return intChdcmp;
    }

    public void setIntChdcmp(BigDecimal intChdcmp) {
        this.intChdcmp = intChdcmp;
    }

    public BigDecimal getIntChgd() {
        return intChgd;
    }

    public void setIntChgd(BigDecimal intChgd) {
        this.intChgd = intChgd;
    }

    public BigDecimal getIntCmpond() {
        return intCmpond;
    }

    public void setIntCmpond(BigDecimal intCmpond) {
        this.intCmpond = intCmpond;
    }

    public BigDecimal getIntCunot() {
        return intCunot;
    }

    public void setIntCunot(BigDecimal intCunot) {
        this.intCunot = intCunot;
    }

    public BigDecimal getIntCurcmp() {
        return intCurcmp;
    }

    public void setIntCurcmp(BigDecimal intCurcmp) {
        this.intCurcmp = intCurcmp;
    }

    public BigDecimal getIntEarned() {
        return intEarned;
    }

    public void setIntEarned(BigDecimal intEarned) {
        this.intEarned = intEarned;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    public BigDecimal getIntTaxrte() {
        return intTaxrte;
    }

    public void setIntTaxrte(BigDecimal intTaxrte) {
        this.intTaxrte = intTaxrte;
    }

    public String getLetrCode1() {
        return letrCode1;
    }

    public void setLetrCode1(String letrCode1) {
        this.letrCode1 = letrCode1 == null ? null : letrCode1.trim();
    }

    public String getLetrCode2() {
        return letrCode2;
    }

    public void setLetrCode2(String letrCode2) {
        this.letrCode2 = letrCode2 == null ? null : letrCode2.trim();
    }

    public BigDecimal getLosses() {
        return losses;
    }

    public void setLosses(BigDecimal losses) {
        this.losses = losses;
    }

    public BigDecimal getMinDue() {
        return minDue;
    }

    public void setMinDue(BigDecimal minDue) {
        this.minDue = minDue;
    }

    public String getMinDueDt() {
        return minDueDt;
    }

    public void setMinDueDt(String minDueDt) {
        this.minDueDt = minDueDt == null ? null : minDueDt.trim();
    }

    public BigDecimal getMindueR() {
        return mindueR;
    }

    public void setMindueR(BigDecimal mindueR) {
        this.mindueR = mindueR;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey == null ? null : msgKey.trim();
    }

    public Short getNbrCashad() {
        return nbrCashad;
    }

    public void setNbrCashad(Short nbrCashad) {
        this.nbrCashad = nbrCashad;
    }

    public Short getNbrFeedty() {
        return nbrFeedty;
    }

    public void setNbrFeedty(Short nbrFeedty) {
        this.nbrFeedty = nbrFeedty;
    }

    public Short getNbrOthers() {
        return nbrOthers;
    }

    public void setNbrOthers(Short nbrOthers) {
        this.nbrOthers = nbrOthers;
    }

    public Short getNbrPaymnt() {
        return nbrPaymnt;
    }

    public void setNbrPaymnt(Short nbrPaymnt) {
        this.nbrPaymnt = nbrPaymnt;
    }

    public Short getNbrPurch() {
        return nbrPurch;
    }

    public void setNbrPurch(Short nbrPurch) {
        this.nbrPurch = nbrPurch;
    }

    public Short getOdueFlag() {
        return odueFlag;
    }

    public void setOdueFlag(Short odueFlag) {
        this.odueFlag = odueFlag;
    }

    public BigDecimal getOdueHeld() {
        return odueHeld;
    }

    public void setOdueHeld(BigDecimal odueHeld) {
        this.odueHeld = odueHeld;
    }

    public BigDecimal getOpenBal() {
        return openBal;
    }

    public void setOpenBal(BigDecimal openBal) {
        this.openBal = openBal;
    }

    public String getOpbalFlag() {
        return opbalFlag;
    }

    public void setOpbalFlag(String opbalFlag) {
        this.opbalFlag = opbalFlag == null ? null : opbalFlag.trim();
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate == null ? null : openDate.trim();
    }

    public BigDecimal getOtherFees() {
        return otherFees;
    }

    public void setOtherFees(BigDecimal otherFees) {
        this.otherFees = otherFees;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getPaymtUncl() {
        return paymtUncl;
    }

    public void setPaymtUncl(BigDecimal paymtUncl) {
        this.paymtUncl = paymtUncl;
    }

    public BigDecimal getPenChrg() {
        return penChrg;
    }

    public void setPenChrg(BigDecimal penChrg) {
        this.penChrg = penChrg;
    }

    public Long getPointAdj() {
        return pointAdj;
    }

    public void setPointAdj(Long pointAdj) {
        this.pointAdj = pointAdj;
    }

    public String getPtadjFlag() {
        return ptadjFlag;
    }

    public void setPtadjFlag(String ptadjFlag) {
        this.ptadjFlag = ptadjFlag == null ? null : ptadjFlag.trim();
    }

    public Long getPointClm() {
        return pointClm;
    }

    public void setPointClm(Long pointClm) {
        this.pointClm = pointClm;
    }

    public Long getPointCum() {
        return pointCum;
    }

    public void setPointCum(Long pointCum) {
        this.pointCum = pointCum;
    }

    public String getPtflag() {
        return ptflag;
    }

    public void setPtflag(String ptflag) {
        this.ptflag = ptflag == null ? null : ptflag.trim();
    }

    public Long getPointEar() {
        return pointEar;
    }

    public void setPointEar(Long pointEar) {
        this.pointEar = pointEar;
    }

    public Short getPriorNo() {
        return priorNo;
    }

    public void setPriorNo(Short priorNo) {
        this.priorNo = priorNo;
    }

    public BigDecimal getPurchases() {
        return purchases;
    }

    public void setPurchases(BigDecimal purchases) {
        this.purchases = purchases;
    }

    public BigDecimal getQueryAmt() {
        return queryAmt;
    }

    public void setQueryAmt(BigDecimal queryAmt) {
        this.queryAmt = queryAmt;
    }

    public String getQueryCode() {
        return queryCode;
    }

    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode == null ? null : queryCode.trim();
    }

    public BigDecimal getRecvryAmt() {
        return recvryAmt;
    }

    public void setRecvryAmt(BigDecimal recvryAmt) {
        this.recvryAmt = recvryAmt;
    }

    public BigDecimal getRevcryAmt() {
        return revcryAmt;
    }

    public void setRevcryAmt(BigDecimal revcryAmt) {
        this.revcryAmt = revcryAmt;
    }

    public BigDecimal getStmAmtOl() {
        return stmAmtOl;
    }

    public void setStmAmtOl(BigDecimal stmAmtOl) {
        this.stmAmtOl = stmAmtOl;
    }

    public String getStmtCode() {
        return stmtCode;
    }

    public void setStmtCode(String stmtCode) {
        this.stmtCode = stmtCode == null ? null : stmtCode.trim();
    }

    public BigDecimal getBalMp() {
        return balMp;
    }

    public void setBalMp(BigDecimal balMp) {
        this.balMp = balMp;
    }

    public BigDecimal getBalNint01() {
        return balNint01;
    }

    public void setBalNint01(BigDecimal balNint01) {
        this.balNint01 = balNint01;
    }

    public BigDecimal getBalNint02() {
        return balNint02;
    }

    public void setBalNint02(BigDecimal balNint02) {
        this.balNint02 = balNint02;
    }

    public BigDecimal getBalNint03() {
        return balNint03;
    }

    public void setBalNint03(BigDecimal balNint03) {
        this.balNint03 = balNint03;
    }

    public BigDecimal getBalNint04() {
        return balNint04;
    }

    public void setBalNint04(BigDecimal balNint04) {
        this.balNint04 = balNint04;
    }

    public BigDecimal getBalNint05() {
        return balNint05;
    }

    public void setBalNint05(BigDecimal balNint05) {
        this.balNint05 = balNint05;
    }

    public BigDecimal getBalNint06() {
        return balNint06;
    }

    public void setBalNint06(BigDecimal balNint06) {
        this.balNint06 = balNint06;
    }

    public BigDecimal getBalNint07() {
        return balNint07;
    }

    public void setBalNint07(BigDecimal balNint07) {
        this.balNint07 = balNint07;
    }

    public BigDecimal getBalNint08() {
        return balNint08;
    }

    public void setBalNint08(BigDecimal balNint08) {
        this.balNint08 = balNint08;
    }

    public BigDecimal getBalNint09() {
        return balNint09;
    }

    public void setBalNint09(BigDecimal balNint09) {
        this.balNint09 = balNint09;
    }

    public BigDecimal getBalNint10() {
        return balNint10;
    }

    public void setBalNint10(BigDecimal balNint10) {
        this.balNint10 = balNint10;
    }

    public BigDecimal getBalCmpfee() {
        return balCmpfee;
    }

    public void setBalCmpfee(BigDecimal balCmpfee) {
        this.balCmpfee = balCmpfee;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
}