package com.dongtong.customer.query;

/**
 * @Package com.dongtong.customer.query.ServiceListListQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/11 10:48
 * version V1.0.0
 */
public class ServiceListListQuery extends BaseQuery {
    private Long userId;
    private Long type;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
