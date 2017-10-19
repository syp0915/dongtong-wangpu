package com.dongtong.customer.dto.resp;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.dto.resp.BrowseStatisticDTO
 * @Description: 浏览统计DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/11 17:34
 * version V1.0.0
 */
public class BrowseStatisticDTO implements Serializable {
    private Long customerId;//用户ID
    private Integer rowNo;//排序
    private String rate;//浏览比较的百分比
    private Integer totalBrowse;//浏览总数
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

    public Integer getTotalBrowse() {
        return totalBrowse;
    }

    public void setTotalBrowse(Integer totalBrowse) {
        this.totalBrowse = totalBrowse;
    }

    public Integer getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(Integer totalCustomer) {
        this.totalCustomer = totalCustomer;
    }
}
