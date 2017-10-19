package com.dongtong.customer.dto.resp;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.dto.resp.StatisticDTO
 * @Description: 统计返回的DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/11 15:37
 * version V1.0.0
 */
public class StatisticDTO implements Serializable {
    private String rate;//比较的百分比
    private Integer total;//返回的总数

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
