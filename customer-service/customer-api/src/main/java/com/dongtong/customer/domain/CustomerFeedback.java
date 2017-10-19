package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.customer.domain.CustomerFeedback.java
 * @Description: 建议反馈表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:42
 * version v1.0.0
 */
public class CustomerFeedback extends BaseBean {
    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 反馈信息
     */
    private String feedback;

    /**
     * 审核状态
     */
    private Integer auditStatus;
    /**
     * 审核内容
     */
    private String auditContent;
    /**
     * 获取用户ID
     *
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户ID
     *
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取反馈信息
     *
     * @return feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * 设置反馈信息
     *
     * @param feedback
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }

    /**
     * 获取审核状态
     *
     * @return audit_status
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态
     *
     * @param auditStatus
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:42
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
        sb.append(", feedback=").append(feedback);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append("]");
        return sb.toString();
    }
}