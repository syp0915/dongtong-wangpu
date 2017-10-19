package com.dongtong.topic.dto;

import java.io.Serializable;

/**
 * @description 删除帖子DTO
 * @package com.dongtong.topic.dto
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/15 11:01
 * @version v1.0.0
 */
public class TopicDeleteDTO implements Serializable{

    private Long topicId;

    private Integer userType;

    private Long operator;

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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "TopicDeleteDTO{" +
                "topicId=" + topicId +
                ", userType=" + userType +
                '}';
    }
}
