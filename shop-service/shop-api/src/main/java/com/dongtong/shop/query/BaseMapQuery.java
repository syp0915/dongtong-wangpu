package com.dongtong.shop.query;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.query.BaseMapQuery
 * @Description: 地图 基础字段
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/9 9:13
 * version V1.0.0
 */
public class BaseMapQuery implements Serializable {

    /**
     * 最小经度
     */
    private String minLon;
    /**
     * 最大经度
     */
    private String maxLon;
    /**
     * 最小纬度
     */
    private String minLat;
    /**
     * 最大纬度
     */
    private String maxLat;

    /**
     * 获取属性 最小经度
     */
    public String getMinLon() {
        return this.minLon;
    }

    /**
     * 设置属性 最小经度
     */
    public void setMinLon(String minLon) {
        this.minLon = minLon;
    }

    /**
     * 获取属性 最大经度
     */
    public String getMaxLon() {
        return this.maxLon;
    }

    /**
     * 设置属性 最大经度
     */
    public void setMaxLon(String maxLon) {
        this.maxLon = maxLon;
    }

    /**
     * 获取属性 最小纬度
     */
    public String getMinLat() {
        return this.minLat;
    }

    /**
     * 设置属性 最小纬度
     */
    public void setMinLat(String minLat) {
        this.minLat = minLat;
    }

    /**
     * 获取属性 最大纬度
     */
    public String getMaxLat() {
        return this.maxLat;
    }

    /**
     * 设置属性 最大纬度
     */
    public void setMaxLat(String maxLat) {
        this.maxLat = maxLat;
    }
}
