package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * @Author:zhoumin
 * @Description:
 * @Date:Created in 15:12 2017/8/9.
 */
public class VisitDTO implements Serializable {
    private static final long serialVersionUID = -5982488150338361291L;

    /**
     * 约看客户
     */
    private Long customerId;

    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 商铺封面
     */
    private String coverImg;

    /**
     * 地址
     */
    private String address;

    /**
     * 面积
     */
    private Float area;

    /**
     * 出租类型
     */
    private Integer rentType;

    /**
     * 约看次数
     */
    private Integer visitCount;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Integer getRentType() {
        return rentType;
    }

    public void setRentType(Integer rentType) {
        this.rentType = rentType;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }
}
