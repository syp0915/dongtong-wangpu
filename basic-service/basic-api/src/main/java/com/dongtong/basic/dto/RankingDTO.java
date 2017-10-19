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
public class RankingDTO implements Serializable {

    private static final long serialVersionUID = -3360937377471529830L;

    /**
     * 头像图片
     */
    private String headPicture;
    /**
     * 名次
     */
    private String ranking;
    /**
     * 单数
     */
    private String quantity;
    /**
     * 名称
     */
    private String name;

    /**
     * 业务员ID
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
