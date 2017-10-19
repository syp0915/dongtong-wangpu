package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto
 * @Description: 客户端查询旺铺显示总数统计
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/4 下午4:59
 * version V1.0.0
 */
public class ShopCountDTO implements Serializable {

    private static final long serialVersionUID = -7238092284100636594L;

    /**
     * 本周新铺总数
     */
    private Integer countNewShop;

    /**
     * 无转让费总数
     */
    private Integer countNoTransferFee;

    /**
     *百平小铺总数
     */
    private Integer countHundredArea;

    /**
     *临近地铁总数
     */
    private Integer countNearStation;

    /**
     * 全部店铺数
     */
    private Integer allShopCount;

    /**
     * 我的店铺
     */
    private Integer myShopCount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCountNewShop() {
        return countNewShop;
    }

    public void setCountNewShop(Integer countNewShop) {
        this.countNewShop = countNewShop;
    }

    public Integer getCountNoTransferFee() {
        return countNoTransferFee;
    }

    public void setCountNoTransferFee(Integer countNoTransferFee) {
        this.countNoTransferFee = countNoTransferFee;
    }

    public Integer getCountHundredArea() {
        return countHundredArea;
    }

    public void setCountHundredArea(Integer countHundredArea) {
        this.countHundredArea = countHundredArea;
    }

    public Integer getCountNearStation() {
        return countNearStation;
    }

    public void setCountNearStation(Integer countNearStation) {
        this.countNearStation = countNearStation;
    }

    public Integer getAllShopCount() {
        return allShopCount;
    }

    public void setAllShopCount(Integer allShopCount) {
        this.allShopCount = allShopCount;
    }

    public Integer getMyShopCount() {
        return myShopCount;
    }

    public void setMyShopCount(Integer myShopCount) {
        this.myShopCount = myShopCount;
    }
}
