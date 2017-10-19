package com.dongtong.basic.query;

import java.io.Serializable;

/**
 * @Package com.dongtong.basic.query.AllRankingQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/12 17:37
 * version V1.0.0
 */
public class AllRankingQuery implements Serializable {
    private static final long serialVersionUID = -8870574568468965863L;

    /**
     * 榜单类型(0:周榜，1：月榜)
     */
    private Integer type;

    /**
     * 用户编号
     */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
