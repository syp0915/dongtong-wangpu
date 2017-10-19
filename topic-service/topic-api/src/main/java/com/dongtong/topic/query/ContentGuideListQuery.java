package com.dongtong.topic.query;

import java.io.Serializable;

/**
 * @description 发现旺铺列表查询
 * @package com.dongtong.topic.query
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/8 0008 10:20
 * @version v1.0.0
 */
public class ContentGuideListQuery implements Serializable {

    private Integer contentType;
    private Integer pageNumber = 1;
    private Integer pageSize = 10;

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
        return "ContentGuideListQuery{" +
                "contentType=" + contentType +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
