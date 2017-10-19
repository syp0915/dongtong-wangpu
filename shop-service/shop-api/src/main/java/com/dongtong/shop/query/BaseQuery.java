package com.dongtong.shop.query;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.query.BaseQuery
 * @Description: 基础查询
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 15:04
 * version V1.0.0
 */
public class BaseQuery implements Serializable{
    private static final long serialVersionUID = 1636748196087653784L;

    /**
     * 页码
     */
    private Integer pageNumber=1;

    /**
     * 条数
     */
    private Integer pageSize=10;

    public BaseQuery(){}

    public BaseQuery(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
