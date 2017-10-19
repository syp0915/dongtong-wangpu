package com.dongtong.topic.dto.req;

import java.io.Serializable;

public class ContentReqDTO implements Serializable{

    private Long contentId;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "ContentReqDTO{" +
                "contentId=" + contentId +
                '}';
    }
}
