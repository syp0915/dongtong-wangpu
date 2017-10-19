package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.shop.domain.ShopShelf.java
 * @Description: 商铺上下架记录表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author xiehb
 * @date 2017/08/03 18:32
 * version v1.2.0
 */
public class ShopShelf extends BaseBean {
    /**
     * 商铺ID
     */
    private Long shopId;

    /**
     * 0已上架、1已下架
     */
    private Integer status;

    /**
     * 下架原因
     */
    private String cause;

    /**
     * 跑批标记
     */
    private Integer runSign;

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
     * 获取0已上架、1已下架
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0已上架、1已下架
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取下架原因
     *
     * @return cause
     */
    public String getCause() {
        return cause;
    }

    /**
     * 设置下架原因
     *
     * @param cause
     */
    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    /**
     * 获取跑批标记
     *
     * @return run_sign
     */
    public Integer getRunSign() {
        return runSign;
    }

    /**
     * 设置跑批标记
     *
     * @param runSign
     */
    public void setRunSign(Integer runSign) {
        this.runSign = runSign;
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
        sb.append(", status=").append(status);
        sb.append(", cause=").append(cause);
        sb.append(", runSign=").append(runSign);
        sb.append("]");
        return sb.toString();
    }
}