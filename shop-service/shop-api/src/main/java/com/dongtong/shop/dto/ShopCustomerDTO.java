package com.dongtong.shop.dto;

import java.util.List;

/**
 * @Package com.dongtong.shop.dto.ShopCustomerDTO
 * @Description: ShopCustomer
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 15:43
 * version V1.0.0
 */
public class ShopCustomerDTO extends BaseShopDTO{
    private static final long serialVersionUID = -42702211459773438L;

    /**
     * 查看条数
     * N
     */
    private int visitCount;
    /**
     * 联络条数
     * N
     */
    private int contactCount;

    /**
     * 特色标签集合
     * N
     */
    private List<TagDTO> featureList;

    /**
     * 查看条数
     * N
     */
    public int getVisitCount() {
        return this.visitCount;
    }

    /**
     * 查看条数
     * N
     */
    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    /**
     * 联络条数
     * N
     */
    public int getContactCount() {
        return this.contactCount;
    }

    /**
     * 联络条数
     * N
     */
    public void setContactCount(int contactCount) {
        this.contactCount = contactCount;
    }

    /**
     * 特色标签集合
     * N
     */
    public List<TagDTO> getFeatureList() {
        return this.featureList;
    }

    /**
     * 特色标签集合
     * N
     */
    public void setFeatureList(List<TagDTO> featureList) {
        this.featureList = featureList;
    }
}
