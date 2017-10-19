package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.customer.domain.CustomerShopCorrect.java
 * @Description: 商铺纠错表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:44
 * version v1.0.0
 */
public class CustomerShopCorrect extends BaseBean {
    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 纠错标签id
     */
    private Long tagId;

    /**
     * 纠错内容
     */
    private String content;

    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系电话
     */
    private String contactMobile;

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
     * 获取纠错标签id
     *
     * @return tag_id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * 设置纠错标签id
     *
     * @param tagId
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取纠错内容
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置纠错内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        sb.append(", shopId=").append(shopId);
        sb.append(", tagId=").append(tagId);
        sb.append(", content=").append(content);
        sb.append(", contacter=").append(contacter);
        sb.append(", contactMobile=").append(contactMobile);
        sb.append(", auditor=").append(auditor);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", auditContent=").append(auditContent);
        sb.append("]");
        return sb.toString();
    }
}