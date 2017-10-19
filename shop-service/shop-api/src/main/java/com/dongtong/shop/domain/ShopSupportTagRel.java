package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: ShopSupportTagRel.java
 * @Description: 商铺配套标签
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:13
 * version v1.0.0
 */
public class ShopSupportTagRel extends BaseBean {
    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 标签id
     */
    private Long tagId;

    /**
     * 获取商铺id
     *
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置商铺id
     *
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取标签id
     *
     * @return tag_id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * 设置标签id
     *
     * @param tagId
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 15:13
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
        sb.append(", tagId=").append(tagId);
        sb.append("]");
        return sb.toString();
    }
}