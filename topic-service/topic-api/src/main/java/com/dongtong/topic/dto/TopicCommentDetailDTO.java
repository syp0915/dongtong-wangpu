package com.dongtong.topic.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.topic.dto.TopicCommentDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/9 18:24
 * version V1.0.0
 */
public class TopicCommentDetailDTO implements Serializable {
    private String id;//评论ID
    private String headPicture;//头像
    private String phone;//电话
    private String dateTime;//评论时间
    private String content;    //评论内容
    private Integer isOwn;    //评论是否为自己
    private Long commentatorId;    //评论者Id

    //v1.2新增
    private Long parentId;
    private String nickName;
    private String parentNickName;
    private Integer isReply;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getParentNickName() {
        return parentNickName;
    }

    public void setParentNickName(String parentNickName) {
        this.parentNickName = parentNickName;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public Long getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(Long commentatorId) {
        this.commentatorId = commentatorId;
    }

    public Integer getIsOwn() {
        return isOwn;
    }

    public void setIsOwn(Integer isOwn) {
        this.isOwn = isOwn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "TopicCommentDetailDTO{" +
                "id='" + id + '\'' +
                ", headPicture='" + headPicture + '\'' +
                ", phone='" + phone + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", content='" + content + '\'' +
                ", isOwn=" + isOwn +
                ", commentatorId=" + commentatorId +
                ", parentId=" + parentId +
                ", nickName='" + nickName + '\'' +
                ", parentNickName='" + parentNickName + '\'' +
                ", isReply=" + isReply +
                '}';
    }
}
