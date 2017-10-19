package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * @version V1.0.0
 * @Package com.dongtong.basic.dto.resp
 * @Description:    历史榜单（周榜或月榜）
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/12 15:56
 */
public class HistoryRankingRespDTO implements Serializable {

    private String ranking;
    private String quantity;
    private String dateTime;
    private int number;

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "HistoryRankingRespDTO{" +
                "ranking='" + ranking + '\'' +
                ", quantity='" + quantity + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
