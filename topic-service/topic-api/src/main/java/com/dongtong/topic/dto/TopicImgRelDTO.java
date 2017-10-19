package com.dongtong.topic.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.topic.dto.TopicImgRelDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/11 18:30
 * version V1.0.0
 */
public class TopicImgRelDTO implements Serializable{
    /**
     * 生意圈id
     */
    private Long topicId;

    /**
     * 图片url
     */
    private String imgUrl;

    /**
     * 图片顺序
     */
    private Integer imgIndex;

    /**
     * 获取生意圈id
     *
     * @return business_id
     */
    public Long getTopicId() {
        return topicId;
    }

    /**
     * 设置生意圈id
     *
     * @param topicId
     */
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    /**
     * 获取图片url
     *
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片url
     *
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取图片顺序
     *
     * @return img_index
     */
    public Integer getImgIndex() {
        return imgIndex;
    }

    /**
     * 设置图片顺序
     *
     * @param imgIndex
     */
    public void setImgIndex(Integer imgIndex) {
        this.imgIndex = imgIndex;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/04 16:16
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", topicId=").append(topicId);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", imgIndex=").append(imgIndex);
        sb.append("]");
        return sb.toString();
    }
}
