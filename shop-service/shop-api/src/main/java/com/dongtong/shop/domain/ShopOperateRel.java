package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: ShopOperateRel.java
 * @Description: 商铺经营范围关系
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:11
 * version v1.0.0
 */
public class ShopOperateRel extends BaseBean {
    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 经营范围id
     */
    private Long industryId;

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
     * 获取经营范围id
     *
     * @return industry_id
     */
    public Long getIndustryId() {
        return industryId;
    }

    /**
     * 设置经营范围id
     *
     * @param industryId
     */
    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 15:11
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
        sb.append(", industryId=").append(industryId);
        sb.append("]");
        return sb.toString();
    }
}