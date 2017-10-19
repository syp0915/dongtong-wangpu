package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.dto.ServiceListDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/11 14:46
 * version V1.0.0
 */
public class ServiceListDTO implements Serializable {
    private Long id;//ID
    private String address;//地址
    private String createTime;//创建时间
    private Integer type;//类型 0-旺铺寻租(房东) 1--预约看铺(租客) 2-签约租铺(租客)
    private Integer shopStatus;//旺铺状态 0:服务受理中 1:旺铺已发布 2:服务已完成 3:服务已撤销 4:店铺被废弃

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }
}
