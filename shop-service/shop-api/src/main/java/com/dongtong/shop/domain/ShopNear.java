package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: ShopNear.java
 * @Description: 邻铺
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:10
 * version v1.0.0
 */
public class ShopNear extends BaseBean {
    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 行业id
     */
    private Long industryId;

    /**
     * 邻铺位置(0-左一 1-左二 2-右一 3-右二)
     */
    private Integer nearSeat;

    /**
     * 人均消费单位 元/位
     */
    private Integer feeRate;

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
     * 获取店铺名称
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置店铺名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取行业id
     *
     * @return industry_id
     */
    public Long getIndustryId() {
        return industryId;
    }

    /**
     * 设置行业id
     *
     * @param industryId
     */
    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    /**
     * 获取邻铺位置(0-左一 1-左二 2-右一 3-右二)
     *
     * @return near_seat
     */
    public Integer getNearSeat() {
        return nearSeat;
    }

    /**
     * 设置邻铺位置(0-左一 1-左二 2-右一 3-右二)
     *
     * @param nearSeat
     */
    public void setNearSeat(Integer nearSeat) {
        this.nearSeat = nearSeat;
    }

    /**
     * 获取人均消费单位 元/位
     *
     * @return fee_rate
     */
    public Integer getFeeRate() {
        return feeRate;
    }

    /**
     * 设置人均消费单位 元/位
     *
     * @param feeRate
     */
    public void setFeeRate(Integer feeRate) {
        this.feeRate = feeRate;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 15:10
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
        sb.append(", name=").append(name);
        sb.append(", industryId=").append(industryId);
        sb.append(", nearSeat=").append(nearSeat);
        sb.append(", feeRate=").append(feeRate);
        sb.append("]");
        return sb.toString();
    }
}