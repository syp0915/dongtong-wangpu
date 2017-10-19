package com.dongtong.shop.query;

import java.util.List;

/**
 * @Package com.dongtong.shop.query.ShopClerkQuery
 * @Description: 业务端商铺查询
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 15:11
 * version V1.0.0
 */
public class ShopClerkQuery extends BaseShopQuery{
    private static final long serialVersionUID = 6452744133886568768L;

    /**
     * 查询街铺类型
     * N
     * 0-我的店铺 1-全部店铺
     */
    private Integer shopType;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 出租状态
     * Y
     * 1-出租中 2-暂不出租 3-已出租
     */
    private List<Integer> rentStatus;
    /**
     * 发布状态
     * Y
     * 0已上架、1已下架
     */
    private List<Integer> shelfStatus;

    /**
     * 排序方式
     * N
     * 1-按距离（升序） 2-按热度（倒序） 3-更新时间（倒序）
     */
    private Integer sortType;


    /**
     * 查询街铺类型
     * N
     * 0-我的店铺 1-全部店铺
     */
    public Integer getShopType() {
        return this.shopType;
    }

    /**
     * 查询街铺类型
     * N
     * 0-我的店铺 1-全部店铺
     */
    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

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
     * 出租状态
     * N
     * 1：招租中 2：已下架  3：待认领 4：待出租
     */
    public List<Integer> getRentStatus() {
        return this.rentStatus;
    }

    /**
     * 出租状态
     * N
     * 1：招租中 2：已下架  3：待认领 4：待出租
     */
    public void setRentStatus(List<Integer> rentStatus) {
        this.rentStatus = rentStatus;
    }

    public List<Integer> getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(List<Integer> shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    /**
     * 排序方式
     * N
     * 1-按距离（升序） 2-按热度（倒序） 3-更新时间（倒序）
     */
    public Integer getSortType() {
        return this.sortType;
    }

    /**
     * 排序方式
     * N
     * 1-按距离（升序） 2-按热度（倒序） 3-更新时间（倒序）
     */
    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }
}
