package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * @version V1.0.0
 * @Package com.dongtong.basic.dto.resp
 * @Description:    个人榜单排名
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/12 15:29
 */
public class HistoryPersonalRankingRespDTO implements Serializable {

    private String headPicture;
    private String ranking;
    private String quantity;
    private String name;
    private String dateTime;
    private String number;
    private String isParticipate;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIsParticipate() {
        return isParticipate;
    }

    public void setIsParticipate(String isParticipate) {
        this.isParticipate = isParticipate;
    }

    @Override
    public String toString() {
        return "HistoryPersonalRankingRespDTO{" +
                "headPicture='" + headPicture + '\'' +
                ", ranking='" + ranking + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", quantity='" + quantity + '\'' +
                ", isParticipate='" + isParticipate + '\'' +
                '}';
    }
}
