package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.ShopFollowInfoDTO
 * @Description: ShopFollowInfoDTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/10 15:26
 * version V1.0.0
 */
public class ShopFollowInfoDTO implements Serializable {
    private static final long serialVersionUID = -1142194178699802112L;

    /**
     * 商铺id
     */
    private Long shopId;
    /**
     * 跟进内容
     */
    private String content;
    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 获取属性 商铺id
     */
    public Long getShopId() {
        return this.shopId;
    }

    /**
     * 设置属性 商铺id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取属性 跟进内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置属性 跟进内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取属性 业务员id
     */
    public Long getClerkId() {
        return this.clerkId;
    }

    /**
     * 设置属性 业务员id
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }
}
