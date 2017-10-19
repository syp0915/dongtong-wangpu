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
public class TopicListQuery implements Serializable {
    private Integer type;//类型 0:最新发布，1：近期最火
    private Integer pageNumber;//当前页码
    private Integer pageSize = 20;//每页条数
    private Long userId;
    private Integer userType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
