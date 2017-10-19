package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.basic.domain.BaseAdvertisement.java
 * @Description: 广告
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:09
 * version v1.0.0
 */
public class BaseAdvertisement extends BaseBean {
    /**
     * 广告名称
     */
    private String name;

    /**
     * 类型  1-banner 2-开屏页 3-广告
     */
    private Integer adType;

    /**
     * 广告排序
     */
    private Integer orderNum;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 广告图片url
     */
    private String adPicUrl;

    /**
     * 跳转链接类型  0-外部 1-内部连接 
     */
    private Integer toUrlType;

    /**
     * 跳转url 
     */
    private String toUrl;

    /**
     * 内部连接,,对应表base_inner_url
     */
    private Long innerUrlId;

    /**
     * 0:用户端，1：业务端
     */
    private Integer source;

    /**
     * 平台投放位置 0:首页，1:找服务，2:旺铺寻租,3:找资金,4:预约看铺
     */
    private Integer position;

    /**
     * 点击数
     */
    private Long clickNum;

    /**
     * 生效时间
     */
    private Date beginTime;

    /**
     * 失效时间
     */
    private Date endTime;

    /**
     * 上下线状态 0-上线 1-下线
     */
    private Integer status;

    /**
     * 广告描述文字
     */
    private String content;

    /**
     * 获取广告名称
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置广告名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取类型  1-banner 2-开屏页 3-广告
     *
     * @return ad_type
     */
    public Integer getAdType() {
        return adType;
    }

    /**
     * 设置类型  1-banner 2-开屏页 3-广告
     *
     * @param adType
     */
    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    /**
     * 获取广告排序
     *
     * @return order_num
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置广告排序
     *
     * @param orderNum
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取权重
     *
     * @return weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取广告图片url
     *
     * @return ad_pic_url
     */
    public String getAdPicUrl() {
        return adPicUrl;
    }

    /**
     * 设置广告图片url
     *
     * @param adPicUrl
     */
    public void setAdPicUrl(String adPicUrl) {
        this.adPicUrl = adPicUrl == null ? null : adPicUrl.trim();
    }

    /**
     * 获取跳转链接类型  0-外部 1-内部连接 
     *
     * @return to_url_type
     */
    public Integer getToUrlType() {
        return toUrlType;
    }

    /**
     * 设置跳转链接类型  0-外部 1-内部连接 
     *
     * @param toUrlType
     */
    public void setToUrlType(Integer toUrlType) {
        this.toUrlType = toUrlType;
    }

    /**
     * 获取跳转url 
     *
     * @return to_url
     */
    public String getToUrl() {
        return toUrl;
    }

    /**
     * 设置跳转url 
     *
     * @param toUrl
     */
    public void setToUrl(String toUrl) {
        this.toUrl = toUrl == null ? null : toUrl.trim();
    }

    /**
     * 获取内部连接,,对应表base_inner_url
     *
     * @return inner_url_id
     */
    public Long getInnerUrlId() {
        return innerUrlId;
    }

    /**
     * 设置内部连接,,对应表base_inner_url
     *
     * @param innerUrlId
     */
    public void setInnerUrlId(Long innerUrlId) {
        this.innerUrlId = innerUrlId;
    }

    /**
     * 获取0:用户端，1：业务端
     *
     * @return source
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置0:用户端，1：业务端
     *
     * @param source
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取平台投放位置 0:首页，1:找服务，2:旺铺寻租,3:找资金,4:预约看铺
     *
     * @return position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * 设置平台投放位置 0:首页，1:找服务，2:旺铺寻租,3:找资金,4:预约看铺
     *
     * @param position
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 获取点击数
     *
     * @return click_num
     */
    public Long getClickNum() {
        return clickNum;
    }

    /**
     * 设置点击数
     *
     * @param clickNum
     */
    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }

    /**
     * 获取生效时间
     *
     * @return begin_time
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置生效时间
     *
     * @param beginTime
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取失效时间
     *
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置失效时间
     *
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取上下线状态 0-上线 1-下线
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置上下线状态 0-上线 1-下线
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取广告描述文字
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置广告描述文字
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/04 16:09
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", adType=").append(adType);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", weight=").append(weight);
        sb.append(", adPicUrl=").append(adPicUrl);
        sb.append(", toUrlType=").append(toUrlType);
        sb.append(", toUrl=").append(toUrl);
        sb.append(", innerUrlId=").append(innerUrlId);
        sb.append(", source=").append(source);
        sb.append(", position=").append(position);
        sb.append(", clickNum=").append(clickNum);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}