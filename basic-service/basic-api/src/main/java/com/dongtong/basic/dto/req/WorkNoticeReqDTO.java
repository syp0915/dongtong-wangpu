package com.dongtong.basic.dto.req;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto
 * @Description :工作通知类型所需参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 11:22
 * version V1.0.0
 **/
public class WorkNoticeReqDTO extends BaseNoticeReqDTO implements Serializable {

    /**
     * 通知状态
     */
    private Integer workNoticeType;

    /**
     * 服务类型
     */
    private Integer serviceType;

    /**
     * 商铺地址
     */
    private String shopAddress;

    /**
     * 计划时间
     */
    private String planTime;

    /**
     * 收铺榜擂主
     */
    private String closeStoreName;

    /**
     * 商场榜擂主
     */
    private String marketName;

    /**
     * 签约榜擂主
     */
    private String signName;

    /**
     *约看榜擂主
     */
    private String lookName;

    /**
     * 注册榜擂主
     */
    private String registerName;


    /**
     * 线索榜擂主
     */
    private String hintName;

    public String getHintName() {
        return hintName;
    }

    public void setHintName(String hintName) {
        this.hintName = hintName;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getWorkNoticeType() {
        return workNoticeType;
    }

    public void setWorkNoticeType(Integer workNoticeType) {
        this.workNoticeType = workNoticeType;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getCloseStoreName() {
        return closeStoreName;
    }

    public void setCloseStoreName(String closeStoreName) {
        this.closeStoreName = closeStoreName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getLookName() {
        return lookName;
    }

    public void setLookName(String lookName) {
        this.lookName = lookName;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

}
