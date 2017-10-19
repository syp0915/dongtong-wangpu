package com.dongtong.shop.dto;

/**
 * @Package com.dongtong.shop.dto
 * @Description: 邻铺详细信息DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/10 上午9:41
 * version V1.0.0
 */
public class ShopNearDetailDTO extends ShopNearDTO {
    /**
     * 邻铺所属业态名称
     */
    private String industryName;

    /**
     * 邻铺所属业态父id
     */
    private Long parentId;

    /**
     * 邻铺位置(0-左一 1-左二 2-右一 3-右二)
     */
    private Integer nearSeat;

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getNearSeat() {
        return nearSeat;
    }

    public void setNearSeat(Integer nearSeat) {
        this.nearSeat = nearSeat;
    }
}
