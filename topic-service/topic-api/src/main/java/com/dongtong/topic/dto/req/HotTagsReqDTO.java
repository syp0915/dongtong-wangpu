package com.dongtong.topic.dto.req;

import java.io.Serializable;

public class HotTagsReqDTO implements Serializable {

    private Integer contentType;

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "HotTagsReqDTO{" +
                "contentType=" + contentType +
                '}';
    }
}
