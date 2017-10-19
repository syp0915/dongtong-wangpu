package com.dongtong.topic.dto.resp;

import java.io.Serializable;
import java.util.List;

/**
 * @description 发现旺铺导购详情
 * @package com.dongtong.topic.dto.resp
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/8 0008 10:17
 * @version v1.0.0
 */
public class ContentGuideDetailRespDTO implements Serializable {

    private Long contentId;
    private String title;
    private String desc;
    private String content;
    private String image;
    private List<HotTagsRespDTO> tagList;
    private String dateTime;
    private List<ShopListRespDTO> shopList;

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

    public List<HotTagsRespDTO> getTagList() {
        return tagList;
    }

    public void setTagList(List<HotTagsRespDTO> tagList) {
        this.tagList = tagList;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<ShopListRespDTO> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopListRespDTO> shopList) {
        this.shopList = shopList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ContentGuideDetailRespDTO{" +
                "contentId=" + contentId +
                ", title='" + title + '\'' +
                ", description='" + desc + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", tagList=" + tagList +
                ", dateTime='" + dateTime + '\'' +
                ", shopList=" + shopList +
                '}';
    }
}
