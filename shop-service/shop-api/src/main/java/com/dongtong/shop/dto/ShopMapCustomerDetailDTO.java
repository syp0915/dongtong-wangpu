package com.dongtong.shop.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Package com.dongtong.shop.dto.ShopMapDetailDTO
 * @Description: 用户端商铺地图-店铺级别的地图
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehb
 * @date 2017/5/9 9:46
 * version V1.0.0
 */
public class ShopMapCustomerDetailDTO implements Serializable{
    /**
     * 店铺面积
     */
    private Float area;
    /**
     * 租金
     * 单位元
     */
    private BigDecimal rent;
    /**
     * 商铺名称
     */
    private String name;
    /**
     * 商铺id
     */
    private Long shopId;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-已出租  3-已下架
     */
    private Integer shopStatus;

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    /**
     * 获取属性 商铺名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置属性 商铺名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取属性 商铺id
     */
    public Long getShopId() {
        return this.shopId;
    }

    /**
     * 设置属性 商铺id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取属性 经度
     */
    public String getLongitude() {
        return this.longitude;
    }

    /**
     * 设置属性 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取属性 纬度
     */
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * 设置属性 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-已出租  3-已下架
     */
    public Integer getShopStatus() {
        return this.shopStatus;
    }

    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-已出租  3-已下架
     */
    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }
}
