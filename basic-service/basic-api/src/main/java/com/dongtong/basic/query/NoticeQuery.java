package com.dongtong.basic.query;

import com.dongtong.basic.domain.BaseNotification;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.query
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-15 10:38
 * version V1.0.0
 **/
public class NoticeQuery extends BaseNotification implements Serializable{
    private Integer pageNumber=1;
    private Integer pageSize=20;

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
