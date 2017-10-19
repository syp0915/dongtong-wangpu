package com.dongtong.topic.dto.resp;

import java.io.Serializable;
import java.util.List;

/**
 * @description 内容列表响应
 * @package com.dongtong.topic.dto.resp
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/4 16:05
 * @version v1.2.0
 */
public class ContentListRespDTO implements Serializable {

    private Long contentId;
    private String title;
    private String content;
    private String source;
    private List<HotTagsRespDTO> tagList;
    private String image;
    private Integer kind;
    private Integer viewCount;
    private String dateTime;

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ContentListRespDTO{" +
                "contentId=" + contentId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", tagList=" + tagList +
                ", image='" + image + '\'' +
                ", kind=" + kind +
                ", viewCount=" + viewCount +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
