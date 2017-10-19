package com.dongtong.shop.query;

import java.io.Serializable;

/**
 * @description 旺铺导购推荐旺铺
 * @package com.dongtong.shop.query
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/11 0011 16:31
 * @version v1.0.0
 */
public class ShopRecomendQuery implements Serializable {

    private Long shopId;

    private String longitude;//经度

    private String latitude;//纬度

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
