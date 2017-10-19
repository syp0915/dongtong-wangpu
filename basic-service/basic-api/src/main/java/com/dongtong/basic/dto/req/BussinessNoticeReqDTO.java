package com.dongtong.basic.dto.req;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto
 * @Description :生意圈类型通知所需参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 9:30
 * version V1.0.0
 **/
public class BussinessNoticeReqDTO extends BaseNoticeReqDTO implements Serializable {


    /**
     * 生意圈通知类型
     */
    private Integer bussinessType;

    /**
     * 帖子名称
     */
    private String postName;

    /**
     * 当帖子被评论时为评论时间；帖子或评论被删时为操作时间
     */
    private String commentOrOprationTime;


    /**
     * 评论手机号
     */
    private String commentTel;

    /**
     * 当帖子被评论时即评论内容；帖子或评论被删时为删除原因
     */
    private String contentOrReason;


    /**
     * 客服电话
     */
    private String cusServiceTel;

    private String commentNickName;

    public String getCommentNickName() {
        return commentNickName;
    }

    public void setCommentNickName(String commentNickName) {
        this.commentNickName = commentNickName;
    }

    public Integer getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(Integer bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getCommentOrOprationTime() {
        return commentOrOprationTime;
    }

    public void setCommentOrOprationTime(String commentOrOprationTime) {
        this.commentOrOprationTime = commentOrOprationTime;
    }

    public String getContentOrReason() {
        return contentOrReason;
    }

    public void setContentOrReason(String contentOrReason) {
        this.contentOrReason = contentOrReason;
    }

    public String getCommentTel() {
        return commentTel;
    }

    public void setCommentTel(String commentTel) {
        this.commentTel = commentTel;
    }

    public String getCusServiceTel() {
        return cusServiceTel;
    }

    public void setCusServiceTel(String cusServiceTel) {
        this.cusServiceTel = cusServiceTel;
    }

    @Override
    public String toString() {
        return "BussinessNoticeReqDTO{" +
                "bussinessType=" + bussinessType +
                ", postName='" + postName + '\'' +
                ", commentOrOprationTime='" + commentOrOprationTime + '\'' +
                ", commentTel='" + commentTel + '\'' +
                ", contentOrReason='" + contentOrReason + '\'' +
                ", cusServiceTel='" + cusServiceTel + '\'' +
                ", commentNickName='" + commentNickName + '\'' +
                '}';
    }
}
