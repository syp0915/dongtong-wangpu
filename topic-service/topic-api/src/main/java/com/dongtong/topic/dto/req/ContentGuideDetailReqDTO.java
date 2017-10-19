package com.dongtong.topic.dto.req;

import java.io.Serializable;

/**
 * @description 发现旺铺导购详情
 * @package com.dongtong.topic.dto.req
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/8 0008 10:32
 * @version v1.0.0
 */
public class ContentGuideDetailReqDTO implements Serializable {

    private Long contentId;
    private String longitude;
    private String latitude;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "ContentGuideDetailReqDTO{" +
                "contentId=" + contentId +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
