package com.dongtong.basic.query;

import java.io.Serializable;

/**
 * @version V1.0.0
 * @Package com.dongtong.basic.query
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/12 13:38
 */
public class HistoryRankingQuery implements Serializable {
    private Long operator;
    private int type;
    private int classify;//0-实勘、1-约看、2-签约 3:注册 5-线索
    private int number;
    private int year;
    private int pageNumber;//当前页码
    private int pageSize = 20;//每页条数

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "HistoryRankingQuery{" +
                "operator=" + operator +
                ", type=" + type +
                ", classify=" + classify +
                ", number=" + number +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
