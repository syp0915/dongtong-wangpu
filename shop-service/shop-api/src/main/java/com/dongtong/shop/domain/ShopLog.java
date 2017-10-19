package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.shop.domain.ShopLog.java
 * @Description: 商铺操作日志表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author xiehb
 * @date 2017/08/03 18:32
 * version v1.2.0
 */
public class ShopLog extends BaseBean {
    /**
     * 商铺ID
     */
    private Long shopId;

    /**
     * 业务员ID
     */
    private Long clerkId;

    /**
     * 日志类型(1-上架;2-下架;3-跟进超时)
     */
    private Integer type;

    /**
     * 日志内容
     */
    private String remark;

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
     * 获取业务员ID
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务员ID
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取日志类型(1-上架;2-下架;3-跟进超时)
     *
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置日志类型(1-上架;2-下架;3-跟进超时)
     *
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取日志内容
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置日志内容
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", clerkId=").append(clerkId);
        sb.append(", type=").append(type);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}