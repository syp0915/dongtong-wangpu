package com.dongtong.customer.dto.resp;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.dto.resp.CollectStatisticDTO
 * @Description: 收藏统计DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/10 17:03
 * version V1.0.0
 */
public class VisitedStatisticDTO implements Serializable {
    private Long customerId;
    private Integer rowNo;//排序
    private String rate;//收藏比较的百分比
    private Integer totalCollected;//收藏总数
    private Integer totalCustomer;//用户总数

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(Integer totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public Integer getTotalCollected() {
        return totalCollected;
    }

    public void setTotalCollected(Integer totalCollected) {
        this.totalCollected = totalCollected;
    }
}
