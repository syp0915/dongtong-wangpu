package com.dongtong.topic.query;

import java.io.Serializable;

public class ContentListQuery implements Serializable{

    private Long tagId;
    private Integer contentType;
    private Integer pageNumber = 1;
    private Integer pageSize = 10;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "ContentListQuery{" +
                "tagId='" + tagId + '\'' +
                ", contentType=" + contentType +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
