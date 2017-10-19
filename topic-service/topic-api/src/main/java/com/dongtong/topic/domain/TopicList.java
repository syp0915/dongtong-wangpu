package com.dongtong.topic.domain;

import com.dongtong.topic.dto.HfivesObject;
import com.dongtong.topic.dto.TopicImgRelDTO;

import java.util.List;

public class TopicList {

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
     * 链接地址
     */
    private String url;
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

    private Integer publisherType;     //v1.2新增

    private Long thirdArticleId;

    public Integer getPublisherType() {
        return publisherType;
    }

    public void setPublisherType(Integer publisherType) {
        this.publisherType = publisherType;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public void setImgList(List imgList) {
        this.imgList = imgList;
    }

    public Long getThirdArticleId() {
        return thirdArticleId;
    }

    public void setThirdArticleId(Long thirdArticleId) {
        this.thirdArticleId = thirdArticleId;
    }

    @Override
    public String toString() {
        return "TopicListDTO{" +
                "topicId=" + topicId +
                ", title='" + title + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", pulishType='" + publishType + '\'' +
                ", content='" + content + '\'' +
                ", praiseNumber=" + praiseNumber +
                ", commentNumber=" + commentNumber +
                ", url='" + url + '\'' +
                ", likeStatus='" + likeStatus + '\'' +
                ", h5obj=" + h5obj +
                ", kind=" + kind +
                ", canBeDelete=" + canBeDelete +
                ", publisherId=" + publisherId +
                ", imgList=" + imgList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicList topicList = (TopicList) o;

        return topicId != null ? topicId.equals(topicList.topicId) : topicList.topicId == null;
    }

    @Override
    public int hashCode() {
        return topicId != null ? topicId.hashCode() : 0;
    }
}
