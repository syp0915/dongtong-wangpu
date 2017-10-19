package com.dongtong.topic.query;

import java.io.Serializable;

/**
 * @Package com.dongtong.topic.query.MyTopicListQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/10 16:32
 * version V1.0.0
 */
public class MyTopicListQuery implements Serializable {
    private Long userId;//用户编号
    private Integer pageNumber;//当前页码
    private Integer pageSize = 20;//每页条数
    private Integer userType;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
