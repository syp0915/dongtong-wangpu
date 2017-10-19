package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: ShopStationRel.java
 * @Description: 商铺地铁站关系表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:12
 * version v1.0.0
 */
public class ShopStationRel extends BaseBean {
    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 距离
     */
    private Float distance;

    /**
     * 地铁站id
     */
    private Long stationId;

    /**
     * 地铁线路id
     */
    private Long metroId;

    /**
     * 获取商铺id
     *
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置商铺id
     *
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取距离
     *
     * @return distance
     */
    public Float getDistance() {
        return distance;
    }

    /**
     * 设置距离
     *
     * @param distance
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }

    /**
     * 获取地铁站id
     *
     * @return station_id
     */
    public Long getStationId() {
        return stationId;
    }

    /**
     * 设置地铁站id
     *
     * @param stationId
     */
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    /**
     * 获取地铁线路id
     *
     * @return metro_id
     */
    public Long getMetroId() {
        return metroId;
    }

    /**
     * 设置地铁线路id
     *
     * @param metroId
     */
    public void setMetroId(Long metroId) {
        this.metroId = metroId;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 15:12
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopId=").append(shopId);
        sb.append(", distance=").append(distance);
        sb.append(", stationId=").append(stationId);
        sb.append(", metroId=").append(metroId);
        sb.append("]");
        return sb.toString();
    }
}