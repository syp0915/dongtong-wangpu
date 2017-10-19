package com.dongtong.clerk.query;

/**
 * @Package com.dongtong.clerk.query.SignOrViewQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/8 10:34
 * version V1.0.0
 */
public class SignOrViewQuery  extends BaseQuery{
    private Integer type;//类型 1:约看 2:签约
    private Long id;//约看详情ID

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
