package com.dongtong.basic.dto.req;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description :商铺通知类型所需参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 11:10
 * version V1.0.0
 **/
public class ShopNoticeReqDTO extends BaseNoticeReqDTO implements Serializable{


    /**
     * 通知状态
     */
    private Integer shopNoticeType;

    /**
     * 商铺地址
     */
    private String shopAddress;


    public Integer getShopNoticeType() {
        return shopNoticeType;
    }

    public void setShopNoticeType(Integer shopNoticeType) {
        this.shopNoticeType = shopNoticeType;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}
