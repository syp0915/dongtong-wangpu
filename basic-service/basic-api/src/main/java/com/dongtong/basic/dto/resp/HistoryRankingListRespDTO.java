package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * @version V1.0.0
 * @Package com.dongtong.basic.dto.resp
 * @Description:    榜单详细列表
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/12 13:26
 */
public class HistoryRankingListRespDTO implements Serializable {

    private String headPicture;

    private String ranking;

    private String quantity;

    private String name;

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

    @Override
    public String toString() {
        return "HistoryRankingListRespDTO{" +
                "headPicture='" + headPicture + '\'' +
                ", ranking='" + ranking + '\'' +
                ", quantity='" + quantity + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
