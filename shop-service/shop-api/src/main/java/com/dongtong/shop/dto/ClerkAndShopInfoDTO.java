package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.ClerkAndShopInfoDTO
 * @Description: 商铺和业务员相关信息DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/22 17:11
 * version V1.0.0
 */
public class ClerkAndShopInfoDTO implements Serializable {
    private String phone;//业务员手机号
    private String realName;//业务员真实姓名
    private String address;//商铺地址

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
