package com.dongtong.topic.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.dto
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 18:09
 */
public class TopicDTO implements Serializable {

    private Long topicId;

    private Long operator;

    private Integer userType;

    private Long publisherId;

    /**
     * v1.0 发布人类型：0-商户 1-业务员
     * v1.2 发布人类型：0-商户 1-业务员 2-马甲
     */
    private Integer publisherType;

    /**
     * v1.0 发布类型：0-帖子 1-资讯
     * v1.2 发布类型：0-帖子 1-转载 2-长图文
     */
    private Integer publishType;

    /**
     * 标题
     */
    private String title;

    /**
     * 超链接url
     */
    private String hyperlinkUrl;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片数组
     */
    private List<String> imageList;

    /**
     * 发布时间
     * v1.2 新增
     */
    private Date publishTime;

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Integer getPublisherType() {
        return publisherType;
    }

    public void setPublisherType(Integer publisherType) {
        this.publisherType = publisherType;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHyperlinkUrl() {
        return hyperlinkUrl;
    }

    public void setHyperlinkUrl(String hyperlinkUrl) {
        this.hyperlinkUrl = hyperlinkUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "TopicDTO{" +
                "topicId=" + topicId +
                ", operator=" + operator +
                ", userType=" + userType +
                ", publisherId=" + publisherId +
                ", publisherType=" + publisherType +
                ", publishType=" + publishType +
                ", title='" + title + '\'' +
                ", hyperlinkUrl='" + hyperlinkUrl + '\'' +
                ", content='" + content + '\'' +
                ", imageList=" + imageList +
                ", publishTime=" + publishTime +
                '}';
    }
}
