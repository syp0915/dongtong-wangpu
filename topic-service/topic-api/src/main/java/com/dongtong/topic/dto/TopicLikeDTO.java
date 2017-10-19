package com.dongtong.topic.dto;

import java.io.Serializable;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.dto
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 18:29
 */
public class TopicLikeDTO implements Serializable {

    private Long likeId;

    private Long topicId;

    private Integer likeType;

    private Integer userType;

    private Long operator;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
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

    public Integer getLikeType() {
        return likeType;
    }

    public void setLikeType(Integer likeType) {
        this.likeType = likeType;
    }

    @Override
    public String toString() {
        return "TopicLikeDTO{" +
                "likeId=" + likeId +
                ", topicId=" + topicId +
                ", likeType=" + likeType +
                ", userType=" + userType +
                ", operator=" + operator +
                '}';
    }
}
