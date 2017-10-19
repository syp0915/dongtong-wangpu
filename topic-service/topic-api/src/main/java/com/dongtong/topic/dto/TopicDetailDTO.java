package com.dongtong.topic.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Package com.dongtong.topic.dto.TopicDetailDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/9 18:19
 * version V1.2.0
 */
public class TopicDetailDTO implements Serializable{

    private String title;               //标题
    private String dateTime;            //发布时间
    private int publishType;            //v1.0-帖子类型(0：帖子 ，1：资讯), v1.2-帖子类型(0：帖子 ，1：转载 ，2：长图文)
    private String content;             //内容
    private List imgList;               //图片集合
    private int praiseNumber;           //赞数
    private int commentNumber;          //评论数
    private int likeStatus;             //点赞状态
    @Deprecated
    private String attentionContent;    //v1.0-关注内容    v1.2废弃
    @Deprecated
    private String phone;               //v1.0-手机号码    v1.2废弃
    private String nickName;            //v1.2新增-昵称
    private String signature;           //v1.2新增-签名
    private Integer canBeDelete;        //帖子是否可以删除
    private Integer kind;
    private Long publisherId;
    private Integer isDel;

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    /**

     * 标题
     */
    private HfivesObject h5obj;

    /**
     * 头像图片
     */
    private String headPicture;

    public Integer getCanBeDelete() {
        return canBeDelete;
    }

    public void setCanBeDelete(Integer canBeDelete) {
        this.canBeDelete = canBeDelete;
    }

    public HfivesObject getH5obj() {
        return h5obj;
    }

    public void setH5obj(HfivesObject h5obj) {
        this.h5obj = h5obj;
    }

    @Deprecated
    public String getPhone() {
        return phone;
    }
    @Deprecated
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
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

    public int getPublishType() {
        return publishType;
    }

    public void setPublishType(int publishType) {
        this.publishType = publishType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List getImgList() {
        return imgList;
    }

    public void setImgList(List imgList) {
        this.imgList = imgList;
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

    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }

    @Deprecated
    public String getAttentionContent() {
        return attentionContent;
    }
    @Deprecated
    public void setAttentionContent(String attentionContent) {
        this.attentionContent = attentionContent;
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

    @Override
    public String toString() {
        return "TopicDetailDTO{" +
                "title='" + title + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", publishType=" + publishType +
                ", content='" + content + '\'' +
                ", imgList=" + imgList +
                ", praiseNumber=" + praiseNumber +
                ", commentNumber=" + commentNumber +
                ", likeStatus=" + likeStatus +
                ", attentionContent='" + attentionContent + '\'' +
                ", phone='" + phone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", signature='" + signature + '\'' +
                ", canBeDelete=" + canBeDelete +
                ", kind=" + kind +
                ", publisherId=" + publisherId +
                ", isDel=" + isDel +
                ", h5obj=" + h5obj +
                ", headPicture='" + headPicture + '\'' +
                '}';
    }
}
