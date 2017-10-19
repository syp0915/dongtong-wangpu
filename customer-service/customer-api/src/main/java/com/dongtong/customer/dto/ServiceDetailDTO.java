package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.dto.ServiceListDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/11 14:46
 * version V1.0.0
 */
public class ServiceDetailDTO implements Serializable {
    private Long id;//ID
    private String address;//地址
    private String createTime;//创建时间
    private Integer type;//类型 0-旺铺寻租(房东) 1--预约看铺(租客) 2-签约租铺(租客)
    private Integer shopStatus;//旺铺状态 0:服务受理中 1:旺铺已发布 2:服务已完成 3:服务已撤销 4:店铺被废弃
    private String clerkName;//业务员名称
    private String clerkHead;//业务员头像
    private String clerkPhone;//业务员电话
    private String contactPerson;//联系人
    private String contactPhone;//联系人电话
    private Long shopId;//商铺ID
    private String discardReason;//废弃原因

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getClerkHead() {
        return clerkHead;
    }

    public void setClerkHead(String clerkHead) {
        this.clerkHead = clerkHead;
    }

    public String getClerkPhone() {
        return clerkPhone;
    }

    public void setClerkPhone(String clerkPhone) {
        this.clerkPhone = clerkPhone;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDiscardReason() {
        return discardReason;
    }

    public void setDiscardReason(String discardReason) {
        this.discardReason = discardReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }
}
