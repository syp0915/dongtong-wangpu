package com.dongtong.topic.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.topic.dto.HfivesObject
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/10 16:25
 * version V1.0.0
 */
public class HfivesObject implements Serializable{
    private String title;//标题
    private String imageUrl;//图片
    private String content;//内容
    private String url;
    private String isThird;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }
}
