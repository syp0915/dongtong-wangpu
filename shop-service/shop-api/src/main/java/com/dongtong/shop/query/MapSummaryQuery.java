package com.dongtong.shop.query;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.query.MapSummaryQuery
 * @Description: 商铺地图概要信息查询
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/9 14:49
 * version V1.0.0
 */
public class MapSummaryQuery implements Serializable {
    private static final long serialVersionUID = 8922171477566511042L;

    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 当前位置经度
     */
    private String longitude;

    /**
     * 当前位置纬度
     */
    private String latitude;


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
     * 获取属性 当前位置经度
     */
    public String getLongitude() {
        return this.longitude;
    }

    /**
     * 设置属性 当前位置经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取属性 当前位置纬度
     */
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * 设置属性 当前位置纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
