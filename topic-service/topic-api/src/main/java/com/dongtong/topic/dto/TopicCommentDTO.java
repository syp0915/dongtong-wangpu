package com.dongtong.topic.dto;

import java.io.Serializable;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.dto
 * @Description:  评论DTO
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 17:27
 */
public class TopicCommentDTO implements Serializable {

    private Long topicId;   //帖子ID
    private Long commentId;     //评论记录ID
    private String content;     //评论内容
    private Long operator;    //操作人
    private Integer userType;

    //v1.2新增字段
    private Integer isReply;
    private Long parentId;

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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "TopicCommentDTO{" +
                "topicId=" + topicId +
                ", commentId=" + commentId +
                ", content='" + content + '\'' +
                ", operator=" + operator +
                ", userType=" + userType +
                ", isReplay=" + isReply +
                ", parentId=" + parentId +
                '}';
    }
}
