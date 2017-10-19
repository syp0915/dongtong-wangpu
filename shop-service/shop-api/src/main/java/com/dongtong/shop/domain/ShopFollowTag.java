package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.shop.domain.ShopFollowTag.java
 * @Description: 商铺跟进标签表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author xiehb
 * @date 2017/08/03 18:32
 * version v1.2.0
 */
public class ShopFollowTag extends BaseBean {
    /**
     * 商铺ID
     */
    private Long shopId;

    /**
     * 商铺跟进表ID
     */
    private Long shopFollowId;

    /**
     * 标签ID
     */
    private Long baseTagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 获取商铺ID
     *
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置商铺ID
     *
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取商铺跟进表ID
     *
     * @return shop_follow_id
     */
    public Long getShopFollowId() {
        return shopFollowId;
    }

    /**
     * 设置商铺跟进表ID
     *
     * @param shopFollowId
     */
    public void setShopFollowId(Long shopFollowId) {
        this.shopFollowId = shopFollowId;
    }

    /**
     * 获取标签ID
     *
     * @return base_tag_id
     */
    public Long getBaseTagId() {
        return baseTagId;
    }

    /**
     * 设置标签ID
     *
     * @param baseTagId
     */
    public void setBaseTagId(Long baseTagId) {
        this.baseTagId = baseTagId;
    }

    /**
     * 获取标签名称
     *
     * @return tag_name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签名称
     *
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * @Title toString
     * @Author xiehb
     * @Date 2017/08/03 18:32
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopId=").append(shopId);
        sb.append(", shopFollowId=").append(shopFollowId);
        sb.append(", baseTagId=").append(baseTagId);
        sb.append(", tagName=").append(tagName);
        sb.append("]");
        return sb.toString();
    }
}