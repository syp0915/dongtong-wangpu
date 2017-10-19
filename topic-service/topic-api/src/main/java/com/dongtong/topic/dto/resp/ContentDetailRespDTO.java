package com.dongtong.topic.dto.resp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ContentDetailRespDTO implements Serializable {

    private Long contentId;
    private String title;
    private String desc;
    private String content;
    private String source;
    private List<HotTagsRespDTO> tagList;
    private String image;
    private Integer kind;
    private Integer viewCount;
    private Integer goodCount;
    private Integer badCount;
    private String dateTime;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<HotTagsRespDTO> getTagList() {
        return tagList;
    }

    public void setTagList(List<HotTagsRespDTO> tagList) {
        this.tagList = tagList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getBadCount() {
        return badCount;
    }

    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ContentDetailRespDTO{" +
                "contentId=" + contentId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", tagList=" + tagList +
                ", image='" + image + '\'' +
                ", kind=" + kind +
                ", viewCount=" + viewCount +
                ", goodCount=" + goodCount +
                ", badCount=" + badCount +
                ", dateTime=" + dateTime +
                '}';
    }
}
