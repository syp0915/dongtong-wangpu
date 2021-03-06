package com.dongtong.shop.dto;

/**
 * @Package com.dongtong.shop.dto.ShopMapSummaryDTO
 * @Description: 商铺地图-点击商铺查看概要信息
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/9 14:52
 * version V1.0.0
 */
public class ShopMapSummaryDTO extends BaseShopDTO {
    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-暂不出租 3-已出租
     */
    private Integer rentStatus;
    /**
     * 发布状态  0已上架、1已下架
     */
    private Integer shelfStatus;

    /**
     * 商铺最新跟进动态
     */
    private String followInfo;

    /**
     * 获取属性 业务员id
     */
    public Long getClerkId() {
        return this.clerkId;
    }

    /**
     * 设置属性 业务员id
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 商铺状态
     * 出租状态 -9-待认领 0-待出租 1-出租中 2-已出租  3-已下架
     */
    public Integer getRentStatus() {
        return this.rentStatus;
    }

    /**
     * 商铺状态
     * 出租状态 -9-待认领 0-待出租 1-出租中 2-已出租  3-已下架
     */
    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    /**
     * 获取属性 商铺最新跟进动态
     */
    public String getFollowInfo() {
        return this.followInfo;
    }

    /**
     * 设置属性 商铺最新跟进动态
     */
    public void setFollowInfo(String followInfo) {
        this.followInfo = followInfo;
    }
}
