package com.dongtong.app.wnl.dto;

/**
 * @Package com.shfc.adviser.wnl.dto.PerpetualCalendarResultDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/1/4 9:52
 * version V1.0.0
 */
public class PerpetualCalendarResultDTO {

    private String year;//年份
    private String month;//月
    private String day;//日
    private String week;//星期
    private String lunaryear;//农历年
    private String lunarmonth;//农历月
    private String lunarday;//农历日
    private String ganzhi;//干支
    private String shengxiao;//生肖
    private String star;//星座
    private HuangLi huangli;//黄历

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getLunaryear() {
        return lunaryear;
    }

    public void setLunaryear(String lunaryear) {
        this.lunaryear = lunaryear;
    }

    public String getLunarmonth() {
        return lunarmonth;
    }

    public void setLunarmonth(String lunarmonth) {
        this.lunarmonth = lunarmonth;
    }

    public String getLunarday() {
        return lunarday;
    }

    public void setLunarday(String lunarday) {
        this.lunarday = lunarday;
    }

    public String getGanzhi() {
        return ganzhi;
    }

    public void setGanzhi(String ganzhi) {
        this.ganzhi = ganzhi;
    }

    public String getShengxiao() {
        return shengxiao;
    }

    public void setShengxiao(String shengxiao) {
        this.shengxiao = shengxiao;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public HuangLi getHuangli() {
        return huangli;
    }

    public void setHuangli(HuangLi huangli) {
        this.huangli = huangli;
    }
}
