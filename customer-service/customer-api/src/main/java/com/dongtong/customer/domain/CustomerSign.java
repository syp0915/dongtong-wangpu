package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.customer.domain.CustomerSign.java
 * @Description: 商铺签约表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:45
 * version v1.0.0
 */
public class CustomerSign extends BaseBean {
    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 约看id 踩盘
     */
    private Long visitId;

    /**
     * 商铺地址
     */
    private String shopAddress;

    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系电话
     */
    private String contactMobile;

    /**
     * 业主
     */
    private String owner;

    /**
     * 业主电话
     */
    private String ownerMobile;

    /**
     * 约见时间
     */
    private Date appointTime;

    /**
     * 签约状态(0-待签约 1-已签约未上传合同（业主确认日程） 2-已签约上传合同 9-业务员取消)
     */
    private Integer status;

    /**
     * 业主确认时间
     */
    private Date ownerAffirmTime;

    /**
     * 业务员上传合同时间
     */
    private Date uploadTime;

    /**
     * 合同id
     */
    private Long agreementId;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 取消原因标签id
     */
    private Long tagId;

    /**
     * 取消原因
     */
    private String reason;

    /**
     * 审核人
     */
    private Long auditor;

    /**
     * 审核状态（0-待审核 1-审核通过 2-审核不通过）
     */
    private Integer auditStatus;

    /**
     * 审核内容
     */
    private String auditContent;

    /**
     * 获取用户id
     *
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户id
     *
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取商铺id
     *
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置商铺id
     *
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取业务员id
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务员id
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取约看id 踩盘
     *
     * @return visit_id
     */
    public Long getVisitId() {
        return visitId;
    }

    /**
     * 设置约看id 踩盘
     *
     * @param visitId
     */
    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    /**
     * 获取商铺地址
     *
     * @return shop_address
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * 设置商铺地址
     *
     * @param shopAddress
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    /**
     * 获取联系人
     *
     * @return contacter
     */
    public String getContacter() {
        return contacter;
    }

    /**
     * 设置联系人
     *
     * @param contacter
     */
    public void setContacter(String contacter) {
        this.contacter = contacter == null ? null : contacter.trim();
    }

    /**
     * 获取联系电话
     *
     * @return contact_mobile
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * 设置联系电话
     *
     * @param contactMobile
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    /**
     * 获取业主
     *
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置业主
     *
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * 获取业主电话
     *
     * @return owner_mobile
     */
    public String getOwnerMobile() {
        return ownerMobile;
    }

    /**
     * 设置业主电话
     *
     * @param ownerMobile
     */
    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile == null ? null : ownerMobile.trim();
    }

    /**
     * 获取约见时间
     *
     * @return appoint_time
     */
    public Date getAppointTime() {
        return appointTime;
    }

    /**
     * 设置约见时间
     *
     * @param appointTime
     */
    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    /**
     * 获取签约状态(0-待签约 1-已签约未上传合同（业主确认日程） 2-已签约上传合同 9-业务员取消)
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置签约状态(0-待签约 1-已签约未上传合同（业主确认日程） 2-已签约上传合同 9-业务员取消)
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取业主确认时间
     *
     * @return owner_affirm_time
     */
    public Date getOwnerAffirmTime() {
        return ownerAffirmTime;
    }

    /**
     * 设置业主确认时间
     *
     * @param ownerAffirmTime
     */
    public void setOwnerAffirmTime(Date ownerAffirmTime) {
        this.ownerAffirmTime = ownerAffirmTime;
    }

    /**
     * 获取业务员上传合同时间
     *
     * @return upload_time
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 设置业务员上传合同时间
     *
     * @param uploadTime
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 获取合同id
     *
     * @return agreement_id
     */
    public Long getAgreementId() {
        return agreementId;
    }

    /**
     * 设置合同id
     *
     * @param agreementId
     */
    public void setAgreementId(Long agreementId) {
        this.agreementId = agreementId;
    }

    /**
     * 获取取消时间
     *
     * @return cancel_time
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 设置取消时间
     *
     * @param cancelTime
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * 获取取消原因标签id
     *
     * @return tag_id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * 设置取消原因标签id
     *
     * @param tagId
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取取消原因
     *
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置取消原因
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 获取审核人
     *
     * @return auditor
     */
    public Long getAuditor() {
        return auditor;
    }

    /**
     * 设置审核人
     *
     * @param auditor
     */
    public void setAuditor(Long auditor) {
        this.auditor = auditor;
    }

    /**
     * 获取审核状态（0-待审核 1-审核通过 2-审核不通过）
     *
     * @return audit_status
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态（0-待审核 1-审核通过 2-审核不通过）
     *
     * @param auditStatus
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取审核内容
     *
     * @return audit_content
     */
    public String getAuditContent() {
        return auditContent;
    }

    /**
     * 设置审核内容
     *
     * @param auditContent
     */
    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent == null ? null : auditContent.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:45
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
        sb.append(", shopId=").append(shopId);
        sb.append(", clerkId=").append(clerkId);
        sb.append(", visitId=").append(visitId);
        sb.append(", shopAddress=").append(shopAddress);
        sb.append(", contacter=").append(contacter);
        sb.append(", contactMobile=").append(contactMobile);
        sb.append(", owner=").append(owner);
        sb.append(", ownerMobile=").append(ownerMobile);
        sb.append(", appointTime=").append(appointTime);
        sb.append(", status=").append(status);
        sb.append(", ownerAffirmTime=").append(ownerAffirmTime);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", agreementId=").append(agreementId);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", tagId=").append(tagId);
        sb.append(", reason=").append(reason);
        sb.append(", auditor=").append(auditor);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", auditContent=").append(auditContent);
        sb.append("]");
        return sb.toString();
    }
}