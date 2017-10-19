package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * 贷款申请
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-09 18:28
 **/
public class LoadDTO extends VerifyDTO implements Serializable {
    private Long customerId;

    private String loanLimit;

    private String loanMaturity;

    private String contactName;

    private String contactMobile;

    private String subscribeTime;
    private String status;
    private String applyTime;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(String loanLimit) {
        this.loanLimit = loanLimit;
    }

    public String getLoanMaturity() {
        return loanMaturity;
    }

    public void setLoanMaturity(String loanMaturity) {
        this.loanMaturity = loanMaturity;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
}