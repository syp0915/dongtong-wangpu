package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.ShopUndoDTO
 * @Description: 商铺下架
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/5 19:08
 * version V1.0.0
 */
public class ShopUndoDTO implements Serializable{
    private static final long serialVersionUID = -4251287655800105366L;

    /**
     * 商铺id
     * M
     */
    private Long shopId;
    /**
     * 标签id
     * M
     */
    private Long tagId;
    /**
     * 下架输入其它信息内容
     * N
     */
    private String undoContent;

    /**
     * 撤下人类型(0-业务员 1- 用户 2-系统)
     */
    private Integer undoType;

    /**
     * 撤下人Id
     */
    private Long undoId;

    /**
     * 出租状态：0-待出租 1-出租中 2-暂不出租 3-已出租
     */
    private Integer rentStatus;
    /**
     * 撤下原因类型 1-出租中 2-暂不出租 3-已出租
     */
    private Integer type;

    /**
     * 商铺id
     * M
     */
    public Long getShopId() {
        return this.shopId;
    }

    /**
     * 商铺id
     * M
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 标签id
     * M
     */
    public Long getTagId() {
        return this.tagId;
    }

    /**
     * 标签id
     * M
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * 下架输入其它信息内容
     * N
     */
    public String getUndoContent() {
        return this.undoContent;
    }

    /**
     * 下架输入其它信息内容
     * N
     */
    public void setUndoContent(String undoContent) {
        this.undoContent = undoContent;
    }

    /**
     * 获取属性 撤下人类型(0-业务员 1- 用户 2-系统)
     */
    public Integer getUndoType() {
        return this.undoType;
    }

    /**
     * 设置属性 撤下人类型(0-业务员 1- 用户 2-系统)
     */
    public void setUndoType(Integer undoType) {
        this.undoType = undoType;
    }

    /**
     * 获取属性 撤下人Id
     */
    public Long getUndoId() {
        return this.undoId;
    }

    /**
     * 设置属性 撤下人Id
     */
    public void setUndoId(Long undoId) {
        this.undoId = undoId;
    }

    public Integer getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
