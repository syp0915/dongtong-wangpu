package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.customer.domain.CustomerLoan.java
 * @Description: 申请贷款表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:44
 * version v1.0.0
 */
public class CustomerLoan extends BaseBean {
    /**
     * 用户ID
     */
    private String customerId;

    /**
     * 贷款额度
     */
    private String loanLimit;

    /**
     * 贷款期限
     */
    private String loanMaturity;

    /**
     * 期限单位
     */
    private String maturityUnit;

    /**
     * 联系人
     */
    private String linkmanName;

    /**
     * 联系电话
     */
    private String linkmanPhone;

    /**
     * 预约时间
     */
    private Date subscribeTime;

    /**
     * 受理状态(0待受理1已受理)
     */
    private Integer status;

    /**
     * 获取用户ID
     *
     * @return customer_id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户ID
     *
     * @param customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 获取贷款额度
     *
     * @return loan_limit
     */
    public String getLoanLimit() {
        return loanLimit;
    }

    /**
     * 设置贷款额度
     *
     * @param loanLimit
     */
    public void setLoanLimit(String loanLimit) {
        this.loanLimit = loanLimit == null ? null : loanLimit.trim();
    }

    /**
     * 获取贷款期限
     *
     * @return loan_maturity
     */
    public String getLoanMaturity() {
        return loanMaturity;
    }

    /**
     * 设置贷款期限
     *
     * @param loanMaturity
     */
    public void setLoanMaturity(String loanMaturity) {
        this.loanMaturity = loanMaturity == null ? null : loanMaturity.trim();
    }

    /**
     * 获取期限单位
     *
     * @return maturity_unit
     */
    public String getMaturityUnit() {
        return maturityUnit;
    }

    /**
     * 设置期限单位
     *
     * @param maturityUnit
     */
    public void setMaturityUnit(String maturityUnit) {
        this.maturityUnit = maturityUnit == null ? null : maturityUnit.trim();
    }

    /**
     * 获取联系人
     *
     * @return linkman_name
     */
    public String getLinkmanName() {
        return linkmanName;
    }

    /**
     * 设置联系人
     *
     * @param linkmanName
     */
    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName == null ? null : linkmanName.trim();
    }

    /**
     * 获取联系电话
     *
     * @return linkman_phone
     */
    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    /**
     * 设置联系电话
     *
     * @param linkmanPhone
     */
    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone == null ? null : linkmanPhone.trim();
    }

    /**
     * 获取预约时间
     *
     * @return subscribe_time
     */
    public Date getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * 设置预约时间
     *
     * @param subscribeTime
     */
    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    /**
     * 获取受理状态(0待受理1已受理)
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置受理状态(0待受理1已受理)
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:44
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", customerId=").append(customerId);
        sb.append(", loanLimit=").append(loanLimit);
        sb.append(", loanMaturity=").append(loanMaturity);
        sb.append(", maturityUnit=").append(maturityUnit);
        sb.append(", linkmanName=").append(linkmanName);
        sb.append(", linkmanPhone=").append(linkmanPhone);
        sb.append(", subscribeTime=").append(subscribeTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}