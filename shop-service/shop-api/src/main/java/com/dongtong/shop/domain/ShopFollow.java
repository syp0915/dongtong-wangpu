package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: ShopFollow.java
 * @Description: 商铺跟进信息
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:09
 * version v1.0.0
 */
public class ShopFollow extends BaseBean {
    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 跟进内容
     */
    private String content;
    /**
     * 删除标识
     */
    private Integer deleteFlag;

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
     * 获取业务员id
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务员id
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取跟进内容
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置跟进内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 15:09
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
        sb.append(", clerkId=").append(clerkId);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}