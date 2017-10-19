package com.dongtong.shop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.dongtong.shop.dto.ShopNearDTO
 * @Description: 临铺信息DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/9 9:45
 * version V1.0.0
 */
public class ShopNearDTO implements Serializable {
    /**
     * 临铺ID
     */
    private Long nearId;
    /**
     * 临铺图片
     */
    private List<ShopImgDTO> nearImg = new ArrayList<>();
    /**
     * 店铺名称
     */
    private String name;
    /**
     * 所属业态
     */
    private Long industryId;

    public Long getNearId() {
        return nearId;
    }

    public void setNearId(Long nearId) {
        this.nearId = nearId;
    }

    public List<ShopImgDTO> getNearImg() {
        return nearImg;
    }

    public void setNearImg(List<ShopImgDTO> nearImg) {
        this.nearImg = nearImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }
}
