package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.ShopImgDTO
 * @Description: 商铺照片
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/5 11:21
 * version V1.0.0
 */
public class ShopImgDTO implements Serializable {

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 图片顺序
     */
    private Integer imgIndex;

    /**
     * 是否为封面(0-否 1-是)
     */
    private Integer isCover;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImgIndex() {
        return imgIndex;
    }

    public void setImgIndex(Integer imgIndex) {
        this.imgIndex = imgIndex;
    }

    public Integer getIsCover() {
        return isCover;
    }

    public void setIsCover(Integer isCover) {
        this.isCover = isCover;
    }

    @Override
    public String toString() {
        return "ShopImgDTO{" +
                "imgUrl='" + imgUrl + '\'' +
                ", imgIndex=" + imgIndex +
                ", isCover=" + isCover +
                '}';
    }
}
