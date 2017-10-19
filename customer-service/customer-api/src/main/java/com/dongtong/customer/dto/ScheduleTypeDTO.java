package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * @Author:zhoumin
 * @Description:设置日程
 * @Date:Created in 10:29 2017/8/8.
 */
public class ScheduleTypeDTO implements Serializable {

    private static final long serialVersionUID = -3151174846334467914L;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 业务id
     */
    private Long bizId;

    /**
     * 类型：1：线索待确认 2：线索待实勘 3：约看 4：签约
     */
    private Integer type;

    /**
     * 约见时间
     */
    private String meetTime;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 房东id
     */
    private Long landlordId;

    /**
     * 日程id
     */
    private String scheduleIds;

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Long landlordId) {
        this.landlordId = landlordId;
    }

    public String getScheduleIds() {
        return scheduleIds;
    }

    public void setScheduleIds(String scheduleIds) {
        this.scheduleIds = scheduleIds;
    }
}
