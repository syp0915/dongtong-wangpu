package com.dongtong.clerk.dto;

import java.io.Serializable;

/**
 * @Author:zhoumin
 * @Description:
 * @Date:Created in 20:47 2017/8/8.
 */
public class ClerkHintDetailDTO implements Serializable {
    private static final long serialVersionUID = 3163736063039494845L;

    /**
     * 线索id
     */
    private Long id;

    /**
     * 地址
     */
    private String address;

    /**
     * 线索状态(0拓铺员待认领—> 1拓铺员待确认 2：交易员待认领 3：待实堪 4:已转化 5:已废弃 6:已取消)
     */
    private Integer status;

    /**
     * 联系人姓名
     */
    private String customerName;

    /**
     * 联系人电话
     */
    private String customerPhone;

    /**
     * 日程时间
     */
    private String scheduleDate;

    /**
     * 封面
     */
    private String coverImgUrl;

    /**
     * 线索拥有人
     */
    private Long ownerId;

    /**
     * 过期时间
     */
    private String overTime;

    /**
     * 超时提醒时间
     */
    private String overTimeStr;

    /**
     * 过期提醒时间
     */
    private String overTimeRemind;

    /**
     * 超时具体文案
     */
    private String overTimeRemindStr;

    /**
     * 日程id
     */
    private String scheduleIds;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOverTimeStr() {
        return overTimeStr;
    }

    public void setOverTimeStr(String overTimeStr) {
        this.overTimeStr = overTimeStr;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getOverTimeRemind() {
        return overTimeRemind;
    }

    public void setOverTimeRemind(String overTimeRemind) {
        this.overTimeRemind = overTimeRemind;
    }

    public String getOverTimeRemindStr() {
        return overTimeRemindStr;
    }

    public void setOverTimeRemindStr(String overTimeRemindStr) {
        this.overTimeRemindStr = overTimeRemindStr;
    }

    public String getScheduleIds() {
        return scheduleIds;
    }

    public void setScheduleIds(String scheduleIds) {
        this.scheduleIds = scheduleIds;
    }
}
