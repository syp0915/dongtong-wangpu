package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.customer.domain.CustomerSchedule.java
 * @Description: 日程表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author Jianguo Li
 * @date 2017/05/09 14:50
 * version v1.0.0
 */
public class CustomerSchedule extends BaseBean {
    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 业务id
     */
    private Long bizId;

    /**
     * 类型：1-旺铺寻租(线索)2-旺铺寻租(实勘)3-预约看铺(租客) 4-租客看铺(房东) 5-签约租铺(租客) 6-租客签约(房东)
     */
    private Integer type;

    /**
     * 申请时间
     */
    private Date applicationTime;

    /**
     * 约见时间
     */
    private Date meetTime;

    /**
     * 旧约见时间
     */
    private Date oldMeetTime;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态：0-服务受理中 1-已完成 2-已撤销
     */
    private Integer status;

    /**
     * 完成时间
     */
    private Date completeTime;

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
     * 获取业务id
     *
     * @return biz_id
     */
    public Long getBizId() {
        return bizId;
    }

    /**
     * 设置业务id
     *
     * @param bizId
     */
    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    /**
     * 获取类型：0-旺铺寻租 1--带我踩盘 2-签约租铺
     *
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型：0-旺铺寻租 1--带我踩盘 2-签约租铺
     *
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取申请时间
     *
     * @return application_time
     */
    public Date getApplicationTime() {
        return applicationTime;
    }

    /**
     * 设置申请时间
     *
     * @param applicationTime
     */
    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    /**
     * 获取约见时间
     *
     * @return meet_time
     */
    public Date getMeetTime() {
        return meetTime;
    }

    /**
     * 设置约见时间
     *
     * @param meetTime
     */
    public void setMeetTime(Date meetTime) {
        this.meetTime = meetTime;
    }

    /**
     * 获取旧约见时间
     *
     * @return old_meet_time
     */
    public Date getOldMeetTime() {
        return oldMeetTime;
    }

    /**
     * 设置旧约见时间
     *
     * @param oldMeetTime
     */
    public void setOldMeetTime(Date oldMeetTime) {
        this.oldMeetTime = oldMeetTime;
    }

    /**
     * 获取省
     *
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取市
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取区
     *
     * @return district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区
     *
     * @param district
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 获取地址
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取状态：0-服务受理中 1-已完成 2-已撤销
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0-服务受理中 1-已完成 2-已撤销
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取完成时间
     *
     * @return complete_time
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * 设置完成时间
     *
     * @param completeTime
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * @Title toString
     * @Author Jianguo Li
     * @Date 2017/05/09 14:50
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clerkId=").append(clerkId);
        sb.append(", customerId=").append(customerId);
        sb.append(", bizId=").append(bizId);
        sb.append(", type=").append(type);
        sb.append(", applicationTime=").append(applicationTime);
        sb.append(", meetTime=").append(meetTime);
        sb.append(", oldMeetTime=").append(oldMeetTime);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", district=").append(district);
        sb.append(", address=").append(address);
        sb.append(", status=").append(status);
        sb.append(", completeTime=").append(completeTime);
        sb.append("]");
        return sb.toString();
    }
}