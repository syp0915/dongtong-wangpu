package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.ShopMapDTO
 * @Description: 商铺地图dto
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/9 9:30
 * version V1.0.0
 */
public class ShopMapDTO implements Serializable{
    private static final long serialVersionUID = 3750099391293400018L;

    /**
     * 名称
     */
    private String name;
    /**
     * 业务id
     */
    private Long bizId;
    /**
     * 数量
     */
    private int shopCount;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;

    /**
     * 获取属性 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置属性 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取属性 业务id
     */
    public Long getBizId() {
        return this.bizId;
    }

    /**
     * 设置属性 业务id
     */
    public void setBizId(Long bizId) {
        this.bizId = bizId;
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
     * 获取属性 数量
     */
    public int getShopCount() {
        return this.shopCount;
    }

    /**
     * 设置属性 数量
     */
    public void setShopCount(int shopCount) {
        this.shopCount = shopCount;
    }
}
