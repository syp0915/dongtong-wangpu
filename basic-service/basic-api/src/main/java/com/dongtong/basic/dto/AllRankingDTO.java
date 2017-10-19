package com.dongtong.basic.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.basic.dto.AllRankingDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/12 17:39
 * version V1.0.0
 */
public class AllRankingDTO implements Serializable {

    private static final long serialVersionUID = -3360937377471529830L;

    /**
     * 业务类型(0-收铺、1-踩盘、2-签约、3-注册)
     */
    private int classify;
    /**
     * 名次
     */
    private String ranking;
    /**
     * 单数
     */
    private String quantity;
    /**
     * 最好名称
     */
    private String optimalName;
    /**
     * 最好名次
     */
    private String optimalNumber;


    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public String getOptimalName() {
        return optimalName;
    }

    public void setOptimalName(String optimalName) {
        this.optimalName = optimalName;
    }

    public String getOptimalNumber() {
        return optimalNumber;
    }

    public void setOptimalNumber(String optimalNumber) {
        this.optimalNumber = optimalNumber;
    }
}
