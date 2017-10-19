package com.dongtong.topic.query;

import java.io.Serializable;

public class TopicCommentDetailQuery implements Serializable {
    private Long topicId;
    private Integer pageNumber = 1;//当前页码
    private Integer pageStart;
    private Integer pageSize = 20;//每页条数
    private Long userId;
    private Integer userType;
    private Long commentId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "TopicCommentDetailQuery{" +
                "topicId=" + topicId +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", userId=" + userId +
                ", userType=" + userType +
                '}';
    }
}
