package com.dongtong.shop.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Package com.dongtong.shop.dto.ShopNearViewDTO
 * @Description: 商铺临铺信息展示DTO,用于修改临铺时的返回信息
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/23 10:07
 * version V1.0.0
 */
public class ShopNearViewDTO implements Serializable {
    /**
     * 商铺id
     */
    private Long shopId;
    /**
     * 邻铺信息
     */
    private List<ShopNearDetailDTO> nearInfoList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<ShopNearDetailDTO> getNearInfoList() {
        return nearInfoList;
    }

    public void setNearInfoList(List<ShopNearDetailDTO> nearInfoList) {
        this.nearInfoList = nearInfoList;
    }
}
