package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseShopNumber.java
 * @Description: 商铺编号
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:09
 * version v1.0.0
 */
public class BaseShopNumber extends BaseBean {
    /**
     * 商铺编号
     */
    private String shopNum;

    /**
     * 状态：0-未使用 1-使用
     */
    private Integer status;

    /**
     * 获取商铺编号
     *
     * @return shop_num
     */
    public String getShopNum() {
        return shopNum;
    }

    /**
     * 设置商铺编号
     *
     * @param shopNum
     */
    public void setShopNum(String shopNum) {
        this.shopNum = shopNum == null ? null : shopNum.trim();
    }

    /**
     * 获取状态：0-未使用 1-使用
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0-未使用 1-使用
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:09
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopNum=").append(shopNum);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}