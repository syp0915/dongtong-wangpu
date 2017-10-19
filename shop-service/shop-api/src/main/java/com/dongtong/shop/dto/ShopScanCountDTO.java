package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.ShopScanCountDTO
 * @Description: 旺铺被看总数和排名统计
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/5 15:47
 * version V1.0.0
 */
public class ShopScanCountDTO implements Serializable{
    private static final long serialVersionUID = -5540105502556300710L;

    /**
     * 本周被浏览总数
     * M
     */
    private Integer weekCount = 0;

    /**
     * 本周被浏览百分比
     * M
     * eg:70%
     */
    private String  rate = "";

    public ShopScanCountDTO() {
    }

    public ShopScanCountDTO(Integer weekCount, String rate) {
        this.weekCount = weekCount;
        this.rate = rate;
    }

    /**
     * 本周被浏览总数
     * M
     */
    public Integer getWeekCount() {
        return this.weekCount;
    }

    /**
     * 本周被浏览总数
     * M
     */
    public void setWeekCount(Integer weekCount) {
        this.weekCount = weekCount;
    }

    /**
     * 本周被浏览百分比
     * M
     * eg:70%
     */
    public String getRate() {
        return this.rate;
    }

    /**
     * 本周被浏览百分比
     * M
     * eg:70%
     */
    public void setRate(String rate) {
        this.rate = rate;
    }
}
