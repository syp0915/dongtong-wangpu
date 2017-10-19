package com.dongtong.basic.query;

import java.io.Serializable;

/**
 * @description
 * @package com.dongtong.basic.dto
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/15 12:22
 * @version v1.0.0
 */
public class HistoryPersonalRankingQuery implements Serializable {

    private int type;

    private int classify;

    private Long operator;

    private int number;

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

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "HistoryPersonalRankingQuery{" +
                "type=" + type +
                ", classify=" + classify +
                ", operator=" + operator +
                ", number=" + number +
                '}';
    }
}
