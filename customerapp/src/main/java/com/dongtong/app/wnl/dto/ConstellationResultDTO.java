package com.dongtong.app.wnl.dto;

/**
 * @Package com.shfc.adviser.wnl.dto.ConstellationResultDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/1/4 10:14
 * version V1.0.0
 */
public class ConstellationResultDTO {
    private String astroid;//星座ID
    private String astroname;//星座名称
    private Year year;//本年运势
    private Week week;//本周运势
    private Tomorrow tomorrow;//明日运势
    private Today today;//今日运势
    private Month month;//本月运势

    public String getAstroid() {
        return astroid;
    }

    public void setAstroid(String astroid) {
        this.astroid = astroid;
    }

    public String getAstroname() {
        return astroname;
    }

    public void setAstroname(String astroname) {
        this.astroname = astroname;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Tomorrow getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(Tomorrow tomorrow) {
        this.tomorrow = tomorrow;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
