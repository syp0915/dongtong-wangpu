package com.dongtong.app.wnl.dto;

/**
 * @Package com.shfc.adviser.wnl.dto.Year
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/1/4 10:15
 * version V1.0.0
 */
public class Year {
    private String date;//年份
    private String summary;//综合运势
    private String money;//财运运势
    private String career;//工作运势
    private String love;//爱情运势

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }
}
