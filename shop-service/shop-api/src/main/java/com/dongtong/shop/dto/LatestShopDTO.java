package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.LatestShopDTO
 * @Description: 显示最近正在转让的一个旺铺
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/5 13:57
 * version V1.0.0
 */
public class LatestShopDTO implements Serializable{
    private static final long serialVersionUID = 1986985725950861333L;

    /**
     * 商铺id
     * M
     */
    private Long id;
    /**
     * 封面图片
     * M
     */
    private String coverImg;
    /**
     * 查看条数
     * N
     */
    private Integer visitCount;
    /**
     * 商铺地址
     * M
     */
    private String address;

    /**
     * 商铺id
     * M
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 商铺id
     * M
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 封面图片
     * M
     */
    public String getCoverImg() {
        return this.coverImg;
    }

    /**
     * 封面图片
     * M
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 查看条数
     * N
     */
    public Integer getVisitCount() {
        return this.visitCount;
    }

    /**
     * 查看条数
     * N
     */
    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    /**
     * 商铺地址
     * M
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 商铺地址
     * M
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
