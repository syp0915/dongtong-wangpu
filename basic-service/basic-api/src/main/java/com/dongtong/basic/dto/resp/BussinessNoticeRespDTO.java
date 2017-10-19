package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description :生意圈列表响应参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-11 19:57
 * version V1.0.0
 **/
public class BussinessNoticeRespDTO extends BaseNoticeRespDTO implements Serializable{

    /**
     * 生意圈通知类型 0：评论通知1：帖子被撤通知2：评论被删通知
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
     * 当帖子被评论时即评论内容；帖子或评论被删时为删除原因
     */
    private String contentOrReason;


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


}
