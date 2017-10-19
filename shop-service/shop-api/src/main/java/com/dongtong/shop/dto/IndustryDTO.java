package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.IndustryDTO
 * @Description: 业态DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/5 11:25
 * version V1.0.0
 */
public class IndustryDTO implements Serializable {
    private Long industryId;
    /**
     * 业态名称
     */
    private String name;

    /**
     * 父id
     */
    private Long parentId;

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
