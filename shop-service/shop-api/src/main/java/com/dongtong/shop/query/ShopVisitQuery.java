package com.dongtong.shop.query;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * ShopVisitededQuery
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-18 10:48
 **/
public class ShopVisitQuery extends BaseQuery {
    private Long customerId;

    private String longitude;

    private String latitude;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
