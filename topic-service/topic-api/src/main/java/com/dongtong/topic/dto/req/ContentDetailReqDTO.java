package com.dongtong.topic.dto.req;

import java.io.Serializable;

public class ContentDetailReqDTO implements Serializable{

    private Long ContentId;

    public Long getContentId() {
        return ContentId;
    }

    public void setContentId(Long contentId) {
        ContentId = contentId;
    }

    @Override
    public String toString() {
        return "ContentDetailReqDTO{" +
                "ContentId=" + ContentId +
                '}';
    }
}
