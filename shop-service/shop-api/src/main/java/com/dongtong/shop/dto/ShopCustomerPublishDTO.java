package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.ShopCustomerPublishDTO
 * @Description: 我发布的金铺列表接口
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/5 17:26
 * version V1.0.0
 */
public class ShopCustomerPublishDTO implements Serializable {
    private static final long serialVersionUID = -2954567551579121513L;

    /**
     * 商铺id
     * M
     */
    private Long id;
    /**
     * 封面图片
     * M
     */
    private String coverImg;
    /**
     * 商铺地址
     * M
     */
    private String address;
    /**
     * 店铺面积
     * M
     */
    private Float area;
    /**
     * 一周内浏览商铺次数
     * M
     */
    private int scanCount;
    /**
     * 历史打开申请约看次数
     * M
     */
    private int visitCount;
    /**
     * 发布时间
     * 格式：yyyy.MM.dd  HH:mm:ss
     * M
     */
    private String publishTime;
    /**
     * 撤下时间
     * 格式：yyyy.MM.dd  HH:mm:ss
     * N
     */
    private String undoTime;

    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-暂不出租 3-已出租
     * M
     */
    private Integer rentStatus;
    /**
     * 0 已上架、1 已下架
     */
    private Integer shelfStatus;
    /**
     * 业务员电话
     * M
     */
    private String clerkPhone;
    //业务员头像
    private String headPortrait;
    //真实姓名
    private String clerkName;

    /**
     * 商铺id
     * M
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 商铺id
     * M
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 封面图片
     * M
     */
    public String getCoverImg() {
        return this.coverImg;
    }

    /**
     * 封面图片
     * M
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 商铺地址
     * M
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 商铺地址
     * M
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 店铺面积
     * M
     */
    public Float getArea() {
        return this.area;
    }

    /**
     * 店铺面积
     * M
     */
    public void setArea(Float area) {
        this.area = area;
    }

    /**
     * 一周内浏览商铺次数
     * M
     */
    public int getScanCount() {
        return this.scanCount;
    }

    /**
     * 一周内浏览商铺次数
     * M
     */
    public void setScanCount(int scanCount) {
        this.scanCount = scanCount;
    }

    /**
     * 历史打开申请约看次数
     * M
     */
    public int getVisitCount() {
        return this.visitCount;
    }

    /**
     * 历史打开申请约看次数
     * M
     */
    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    /**
     * 发布时间
     * 格式：yyyy.MM.dd  HH:mm:ss
     * M
     */
    public String getPublishTime() {
        return this.publishTime;
    }

    /**
     * 发布时间
     * 格式：yyyy.MM.dd  HH:mm:ss
     * M
     */
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 撤下时间
     * 格式：yyyy.MM.dd  HH:mm:ss
     * N
     */
    public String getUndoTime() {
        return this.undoTime;
    }

    /**
     * 撤下时间
     * 格式：yyyy.MM.dd  HH:mm:ss
     * N
     */
    public void setUndoTime(String undoTime) {
        this.undoTime = undoTime;
    }

    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-已出租  3-已下架（撤下）
     * M
     */
    public Integer getRentStatus() {
        return this.rentStatus;
    }

    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-已出租  3-已下架（撤下）
     * M
     */
    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    /**
     * 业务员电话
     * M
     */
    public String getClerkPhone() {
        return this.clerkPhone;
    }

    /**
     * 业务员电话
     * M
     */
    public void setClerkPhone(String clerkPhone) {
        this.clerkPhone = clerkPhone;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }
}
