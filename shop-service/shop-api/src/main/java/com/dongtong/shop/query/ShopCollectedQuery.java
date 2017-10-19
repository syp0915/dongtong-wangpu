package com.dongtong.shop.query;

/**
 * @Package com.dongtong.shop.query.ShopCollectedQuery
 * @Description: 查询收藏商铺列表
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/11 14:07
 * version V1.0.0
 */
public class ShopCollectedQuery extends BaseQuery {
    private Long customerId;//客户ID

    private String longitude;//经度

    private String latitude;//纬度

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        if(longitude==""){
            longitude= null;
        }
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        if(latitude==""){
            latitude=null;
        }
        this.latitude = latitude;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
