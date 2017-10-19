package com.dongtong.customer.query;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/11 上午10:11.
 */
public class ShopNameIsGoodReqQuery implements Serializable {

    private String shopName;//店名

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
