package com.dongtong.topic.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Package com.dongtong.topic.dto.TopicListDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/10 16:22
 * version V1.0.0
 */
public class TopicListDTO implements Serializable {

    private Long topicId;
    /**
     * 标题
     */
    private String title;
    /**
     * 发布时间
     */
    private String dateTime;
    /**
     * 帖子类型
     */
    private String publishType;
    /**
     * 内容
     */
    private String content;
    /**
     * 赞数
     */
    private int praiseNumber;
    /**
     * 评论数
     */
    private int commentNumber;

    /**
     * 点赞状态
     */
    private String likeStatus;
    /**
     * 标题
     */
    private HfivesObject h5obj;
    /**
     * 类型：0-普通 1-置顶
     */
    private Integer kind;

    /**
     * 帖子是否可以删除
     */
    private Integer canBeDelete;

    private Long publisherId;

    private String nickName;     //v1.2新增

    private String signature;    //v1.2新增

    private String headPicture;    //v1.2新增

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getCanBeDelete() {
        return canBeDelete;
    }

    public void setCanBeDelete(Integer canBeDelete) {
        this.canBeDelete = canBeDelete;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    private List<TopicImgRelDTO> imgList;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPublishType() {
        return publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(int praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        this.likeStatus = likeStatus;
    }

    public HfivesObject getH5obj() {
        return h5obj;
    }

    public void setH5obj(HfivesObject h5obj) {
        this.h5obj = h5obj;
    }

    public List getImgList() {
        return imgList;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setImgList(List<TopicImgRelDTO> imgList) {
        this.imgList = imgList;
    }

    @Override
    public String toString() {
        return "TopicListDTO{" +
                "topicId=" + topicId +
                ", title='" + title + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", publishType='" + publishType + '\'' +
                ", content='" + content + '\'' +
                ", praiseNumber=" + praiseNumber +
                ", commentNumber=" + commentNumber +
                ", likeStatus='" + likeStatus + '\'' +
                ", h5obj=" + h5obj +
                ", kind=" + kind +
                ", canBeDelete=" + canBeDelete +
                ", publisherId=" + publisherId +
                ", nickName='" + nickName + '\'' +
                ", signature='" + signature + '\'' +
                ", headPicture='" + headPicture + '\'' +
                ", imgList=" + imgList +
                '}';
    }
}
