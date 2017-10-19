package com.dongtong.customer.query;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.query.CustomerServiceQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/10 11:26
 * version V1.0.0
 */
public class CustomerServiceQuery implements Serializable {
    private Long id;//ID
    private Integer type;//类型 0-旺铺寻租(房东) 1--预约看铺(租客) 2-签约租铺(租客)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
