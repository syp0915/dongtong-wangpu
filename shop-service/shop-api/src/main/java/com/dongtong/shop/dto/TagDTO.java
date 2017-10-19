package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.shop.dto.TagDTO
 * @Description: TagDTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 15:44
 * version V1.0.0
 */
public class TagDTO implements Serializable {
    private static final long serialVersionUID = 5188277076443404580L;

    /**
     * 标签id
     * M
     */
    private Long id;
    /**
     * 标签名称
     * M
     */
    private String name;
    /**
     * 标签颜色
     * M
     */
    private String color;

    /**
     * 标签id
     * M
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 标签id
     * M
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标签名称
     * M
     */
    public String getName() {
        return this.name;
    }

    /**
     * 标签名称
     * M
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 标签颜色
     * M
     */
    public String getColor() {
        return this.color;
    }

    /**
     * 标签颜色
     * M
     */
    public void setColor(String color) {
        this.color = color;
    }
}
