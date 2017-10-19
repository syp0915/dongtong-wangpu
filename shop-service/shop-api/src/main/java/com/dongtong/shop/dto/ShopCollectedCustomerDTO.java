package com.dongtong.shop.dto;

/**
 * @Package com.dongtong.shop.dto.ShopCollectedCustomerDTO
 * @Description: 用户收藏商铺列表DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/11 13:37
 * version V1.0.0
 */
public class ShopCollectedCustomerDTO extends ShopCustomerDTO {
    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-暂不出租 3-已出租
     */
    private Integer rentStatus;
    /**
     * 发布状态 ：0 已上架、1 已下架
     */
    private Integer shelfStatus;

    public Integer getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }
}
