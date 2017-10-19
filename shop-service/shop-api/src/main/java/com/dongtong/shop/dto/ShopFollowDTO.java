package com.dongtong.shop.dto;

import com.dongtong.shop.utils.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package com.dongtong.shop.dto.ShopFollowDTO
 * @Description: 跟进动态
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 15:29
 * version V1.0.0
 */
public class ShopFollowDTO implements Serializable {
    private static final long serialVersionUID = 677034002004480963L;

    /**
     * 跟进id
     */
    private Long id;
    /**
     * 业务员头像
     */
    private String clerkImg;

    /**
     * 跟进内容
     */
    private String content;

    /**
     * 跟进时间
     */
    private String followTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 获取属性 跟进id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置属性 跟进id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取属性 业务员头像
     */
    public String getClerkImg() {
        return this.clerkImg;
    }

    /**
     * 设置属性 业务员头像
     */
    public void setClerkImg(String clerkImg) {
        this.clerkImg = clerkImg;
    }

    /**
     * 获取属性 跟进内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置属性 跟进内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取属性 跟进时间
     */
    public String getFollowTime() {
        return this.followTime;
    }

    /**
     * 设置属性 跟进时间
     */
    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }

    /**
     * 获取属性 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置属性 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if(createTime != null){
            setFollowTime(DateFormatUtils.parseDateShow(createTime));
        }
    }

}
