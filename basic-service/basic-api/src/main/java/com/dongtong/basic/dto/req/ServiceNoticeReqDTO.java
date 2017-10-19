package com.dongtong.basic.dto.req;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto
 * @Description :服务类型通知所需参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 17:17
 * version V1.0.0
 **/
public class ServiceNoticeReqDTO extends BaseNoticeReqDTO implements Serializable {

    /**
     *服务通知类型
     */
    private Integer serviceType;


    /**
     * 服务状态
     */
    private Integer serviceStatus;

    /**
     * 业务员姓名
     */
    private String serviceName;

    /**
     * 联系电话
     */
    private String serviceTel;

    /**
     * 商铺地址
     */
    private String shopAddress;

    /**
     * 旧日程
     */
    private String oldTime;

    /**
     * 新日程
     */
    private String currentTime;

    /**
     * 撤销原因
     */
    private String reason;

    /**
     * 当天日程数
     */
    private Integer scheduleCount;


    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getOldTime() {
        return oldTime;
    }

    public void setOldTime(String oldTime) {
        this.oldTime = oldTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
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

    @Override
    public String toString() {
        return "ServiceNoticeReqDTO{" +
                "serviceType=" + serviceType +
                ", serviceStatus=" + serviceStatus +
                ", serviceName='" + serviceName + '\'' +
                ", serviceTel='" + serviceTel + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", oldTime='" + oldTime + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", reason='" + reason + '\'' +
                ", scheduleCount=" + scheduleCount +
                '}';
    }
}
