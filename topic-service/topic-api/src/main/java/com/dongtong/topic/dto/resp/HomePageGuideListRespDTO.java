package com.dongtong.topic.dto.resp;

import java.io.Serializable;

/**
 * @description 找旺铺首页发现旺铺列表
 * @package com.dongtong.topic.dto.resp
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/8 0008 10:14
 * @version v1.0.0
 */
public class HomePageGuideListRespDTO implements Serializable{

    private Long contentId;
    private String title;
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
