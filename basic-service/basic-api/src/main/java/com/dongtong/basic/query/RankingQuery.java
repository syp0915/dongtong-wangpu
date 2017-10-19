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
public class RankingQuery implements Serializable {
    private static final long serialVersionUID = -8870574568468965863L;

    /**
     * 榜单类型(0:周榜，1：月榜)
     */
    private Integer type;
    /**
     * 业务类型(0-收铺、1-踩盘、2-签约、3-注册)
     */
    private Integer classify;
    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageNumber;

    /**
     * 是否是推送 true:代表是推送
     */
    private Boolean whetherPush = false;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Boolean getWhetherPush() {
        return whetherPush;
    }

    public void setWhetherPush(Boolean whetherPush) {
        this.whetherPush = whetherPush;
    }
}
