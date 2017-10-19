package com.dongtong.basic.query;

import java.io.Serializable;

/**
 * @Package com.dongtong.basic.query.RankingPageQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/15 14:53
 * version V1.0.0
 */
public class RankingPageQuery implements Serializable {
    private static final long serialVersionUID = -8870574568468965863L;

    /**
     * 榜单类型(0:周榜，1：月榜)
     */
    private String startDate;

    /**
     * 用户编号
     */
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
