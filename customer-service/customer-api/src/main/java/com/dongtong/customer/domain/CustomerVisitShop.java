package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.customer.domain.CustomerVisitShop.java
 * @Description: 用户约看表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:47
 * version v1.0.0
 */
public class CustomerVisitShop extends BaseBean {
    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 商铺ID
     */
    private Long shopId;

    /**
     * 业务员ID(来至于商铺)
     */
    private Long clerkId;

    /**
     * 业务员姓名
     */
    private String clerkName;

    /**
     * 约看时间
     */
    private Date visitTime;

    /**
     * 约看联系人
     */
    private String linkmanName;

    /**
     * 联系人电话
     */
    private String linkmanPhone;

    /**
     * 状态（0待踩盘，1已踩盘，2已取消）
     */
    private Integer status;

    /**
     * 用户撤销时间
     */
    private Date revocationTime;

    /**
     * 完成服务时间
     */
    private Date successTime;

    /**
     * 业务取消时间
     */
    private Date cancelTime;

    /**
     * 取消原因标签id
     */
    private Long tagId;

    /**
     * 业务取消原因
     */
    private String cancelCause;

    /**
     * 是否删除 0-否 1-是
     */
    private Integer isDelete;

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
     * 获取商铺ID
     *
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置商铺ID
     *
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取业务员ID(来至于商铺)
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务员ID(来至于商铺)
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取业务员姓名
     *
     * @return clerk_name
     */
    public String getClerkName() {
        return clerkName;
    }

    /**
     * 设置业务员姓名
     *
     * @param clerkName
     */
    public void setClerkName(String clerkName) {
        this.clerkName = clerkName == null ? null : clerkName.trim();
    }

    /**
     * 获取约看时间
     *
     * @return visit_time
     */
    public Date getVisitTime() {
        return visitTime;
    }

    /**
     * 设置约看时间
     *
     * @param visitTime
     */
    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    /**
     * 获取约看联系人
     *
     * @return linkman_name
     */
    public String getLinkmanName() {
        return linkmanName;
    }

    /**
     * 设置约看联系人
     *
     * @param linkmanName
     */
    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName == null ? null : linkmanName.trim();
    }

    /**
     * 获取联系人电话
     *
     * @return linkman_phone
     */
    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    /**
     * 设置联系人电话
     *
     * @param linkmanPhone
     */
    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone == null ? null : linkmanPhone.trim();
    }

    /**
     * 获取状态（0待踩盘，1已踩盘，2已取消）
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（0待踩盘，1已踩盘，2已取消）
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取用户撤销时间
     *
     * @return revocation_time
     */
    public Date getRevocationTime() {
        return revocationTime;
    }

    /**
     * 设置用户撤销时间
     *
     * @param revocationTime
     */
    public void setRevocationTime(Date revocationTime) {
        this.revocationTime = revocationTime;
    }

    /**
     * 获取完成服务时间
     *
     * @return success_time
     */
    public Date getSuccessTime() {
        return successTime;
    }

    /**
     * 设置完成服务时间
     *
     * @param successTime
     */
    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    /**
     * 获取业务取消时间
     *
     * @return cancel_time
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 设置业务取消时间
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
     * 获取业务取消原因
     *
     * @return cancel_cause
     */
    public String getCancelCause() {
        return cancelCause;
    }

    /**
     * 设置业务取消原因
     *
     * @param cancelCause
     */
    public void setCancelCause(String cancelCause) {
        this.cancelCause = cancelCause == null ? null : cancelCause.trim();
    }

    /**
     * 获取是否删除 0-否 1-是
     *
     * @return is_delete
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除 0-否 1-是
     *
     * @param isDelete
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:47
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
        sb.append(", clerkName=").append(clerkName);
        sb.append(", visitTime=").append(visitTime);
        sb.append(", linkmanName=").append(linkmanName);
        sb.append(", linkmanPhone=").append(linkmanPhone);
        sb.append(", status=").append(status);
        sb.append(", revocationTime=").append(revocationTime);
        sb.append(", successTime=").append(successTime);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", tagId=").append(tagId);
        sb.append(", cancelCause=").append(cancelCause);
        sb.append("]");
        return sb.toString();
    }
}