package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.customer.domain.CustomerBrowseShop.java
 * @Description: 用户浏览的商铺
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:41
 * version v1.0.0
 */
public class CustomerBrowseShop extends BaseBean {
    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 商铺ID
     */
    private Long shopId;

    /**
     * 获取用户ID
     *
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户ID
     *
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

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
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:41
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", customerId=").append(customerId);
        sb.append(", shopId=").append(shopId);
        sb.append("]");
        return sb.toString();
    }
}