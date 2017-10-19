package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description :服务通知列表响应参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-11 19:42
 * version V1.0.0
 **/
public class ServiceNoticeRespDTO extends BaseNoticeRespDTO implements Serializable{

    /**
     * 0-时间变动 1-服务撤销 2-服务完成 3-日程提醒
     */
    private Integer serviceStatus;

    /**
     * 服务类型 0：旺铺寻租1：签约租铺2：预约看铺
     */
    private Integer serviceType;

    /**
     * 商铺地址
     */
    private String shopAddress;

    /**
     * 旧日程
     */
    private String oldSchedule;

    /**
     * 当前日程
     */
    private String currentSchedule;

    /**
     * 小二姓名
     */
    private String serviceName;

    /**
     * 小二电话
     */
    private String serviceTel;

    /**
     * 撤销原因
     */
    private String reason;

    /**
     * 日程数
     */
    private Integer scheduleCount;

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getOldSchedule() {
        return oldSchedule;
    }

    public void setOldSchedule(String oldSchedule) {
        this.oldSchedule = oldSchedule;
    }

    public String getCurrentSchedule() {
        return currentSchedule;
    }

    public void setCurrentSchedule(String currentSchedule) {
        this.currentSchedule = currentSchedule;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getScheduleCount() {
        return scheduleCount;
    }

    public void setScheduleCount(Integer scheduleCount) {
        this.scheduleCount = scheduleCount;
    }
}
