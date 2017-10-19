package com.dongtong.topic.dto.resp;

import java.io.Serializable;

public class HotTagsRespDTO implements Serializable {

    private Long tagId;
    private String tagIcon;
    private String tagName;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagIcon() {
        return tagIcon;
    }

    public void setTagIcon(String tagIcon) {
        this.tagIcon = tagIcon;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "HotTagsRespDTO{" +
                "tagId=" + tagId +
                ", tagIcon='" + tagIcon + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
