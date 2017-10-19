package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: ShopStatusFlow.java
 * @Description: 店铺状态流转记录
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:12
 * version v1.0.0
 */
public class ShopStatusFlow extends BaseBean {
    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 操作人类型(0-业务员 1- 用户 2-系统)
     */
    private Integer operatorType;

    /**
     * 操作人
     */
    private Long operatorId;

    /**
     * 状态 0-待出租 1-出租中 2-已出租  3-已下架
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

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
     * 获取操作人类型(0-业务员 1- 用户 2-系统)
     *
     * @return operator_type
     */
    public Integer getOperatorType() {
        return operatorType;
    }

    /**
     * 设置操作人类型(0-业务员 1- 用户 2-系统)
     *
     * @param operatorType
     */
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * 获取操作人
     *
     * @return operator_id
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 设置操作人
     *
     * @param operatorId
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取状态 0-待出租 1-出租中 2-已出租  3-已下架
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0-待出租 1-出租中 2-已出租  3-已下架
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取描述
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 15:12
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
        sb.append(", operatorType=").append(operatorType);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }
}