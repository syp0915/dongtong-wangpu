package com.dongtong.shop.query;

/**
 * @Package com.dongtong.shop.query.MapCustomerQuery
 * @Description: 用户端 区域板块层级店铺数量
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/12 11:05
 * version V1.0.0
 */
public class MapCustomerQuery extends BaseShopQuery {
    /**
     * 查询类型
     * 0-（默认值）查询区域商铺数量
     * 1-查询板块商铺数量
     */
    private Integer type = 0;

    /**
     * 从区域点击查询区域下的板块
     */
    //private Long districtId;

    /**
     * 基础查询条件
     */
    private BaseMapQuery baseMapQuery = new BaseMapQuery();

    /**
     * 0-（默认值）查询区域商铺数量
     * 1-查询板块商铺数量
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 0-（默认值）查询区域商铺数量
     * 1-查询板块商铺数量
     */
    public void setType(Integer type) {
        this.type = type;
    }

/*    *//**
     * 从区域点击查询区域下的板块
     *//*
    public Long getDistrictId() {
        return this.districtId;
    }

    *//**
     * 从区域点击查询区域下的板块
     *//*
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }*/

    public String getMinLon() {
        return baseMapQuery.getMinLon();
    }

    public void setMinLat(String minLat) {
        baseMapQuery.setMinLat(minLat);
    }

    public String getMaxLat() {
        return baseMapQuery.getMaxLat();
    }

    public void setMaxLon(String maxLon) {
        baseMapQuery.setMaxLon(maxLon);
    }

    public String getMinLat() {
        return baseMapQuery.getMinLat();
    }

    public void setMaxLat(String maxLat) {
        baseMapQuery.setMaxLat(maxLat);
    }

    public void setMinLon(String minLon) {
        baseMapQuery.setMinLon(minLon);
    }

    public String getMaxLon() {
        return baseMapQuery.getMaxLon();
    }
}
