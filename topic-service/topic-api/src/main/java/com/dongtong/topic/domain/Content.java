package com.dongtong.topic.domain;

import com.shfc.common.httpbean.BaseBean;
import java.util.Date;

/**
 * @Package: com.dongtong.topic.domain.Content.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author chen xiushen
 * @date 2017/08/04 16:36
 * version v1.0.0
 */
public class Content extends BaseBean {
    /**
     * 类型 1-头条资讯 2-研究院内容管理 3-导购管理
     */
    private Boolean type;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片url
     */
    private String image;

    /**
     * 来源id content_source的主键
     */
    private Integer sourceId;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 状态 1-未发布 2-已发布 3-已下线
     */
    private Boolean status;

    /**
     * 是否置顶  1-置顶 2-不置顶
     */
    private Boolean isTop;

    /**
     * 定时器 定时时间
     */
    private Date timer;

    /**
     * 查看次数
     */
    private Integer viewCount;

    /**
     * 赞的次数
     */
    private Integer goodCount;

    /**
     * 踩的次数
     */
    private Integer badCount;

    /**
     * 获取类型 1-头条资讯 2-研究院内容管理 3-导购管理
     *
     * @return type
     */
    public Boolean getType() {
        return type;
    }

    /**
     * 设置类型 1-头条资讯 2-研究院内容管理 3-导购管理
     *
     * @param type
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * 获取标题
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取图片url
     *
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片url
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 获取来源id content_source的主键
     *
     * @return source_id
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * 设置来源id content_source的主键
     *
     * @param sourceId
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取发布时间
     *
     * @return publish_time
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置发布时间
     *
     * @param publishTime
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取状态 1-未发布 2-已发布 3-已下线
     *
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态 1-未发布 2-已发布 3-已下线
     *
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取是否置顶  1-置顶 2-不置顶
     *
     * @return is_top
     */
    public Boolean getIsTop() {
        return isTop;
    }

    /**
     * 设置是否置顶  1-置顶 2-不置顶
     *
     * @param isTop
     */
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /**
     * 获取定时器 定时时间
     *
     * @return timer
     */
    public Date getTimer() {
        return timer;
    }

    /**
     * 设置定时器 定时时间
     *
     * @param timer
     */
    public void setTimer(Date timer) {
        this.timer = timer;
    }

    /**
     * 获取查看次数
     *
     * @return view_count
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 设置查看次数
     *
     * @param viewCount
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 获取赞的次数
     *
     * @return good_count
     */
    public Integer getGoodCount() {
        return goodCount;
    }

    /**
     * 设置赞的次数
     *
     * @param goodCount
     */
    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    /**
     * 获取踩的次数
     *
     * @return bad_count
     */
    public Integer getBadCount() {
        return badCount;
    }

    /**
     * 设置踩的次数
     *
     * @param badCount
     */
    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
    }
}