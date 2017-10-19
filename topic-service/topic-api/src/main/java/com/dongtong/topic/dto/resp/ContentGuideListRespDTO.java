package com.dongtong.topic.dto.resp;

import java.io.Serializable;

/**
 * @description 发现旺铺导购列表
 * @package com.dongtong.topic.dto.resp
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/8 0008 10:17
 * @version v1.0.0
 */
public class ContentGuideListRespDTO implements Serializable{

    private Long contentId;
    private String title;
    private String content;
    private String image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ContentGuideListRespDTO{" +
                "contentId=" + contentId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
