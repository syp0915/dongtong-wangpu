package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseNotification.java
 * @Description: 通知表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author sunyaping
 * @date 2017/06/05 10:22
 * version v1.0.0
 */
public class BaseNotification extends BaseBean {
    /**
     * 接收人id
     */
    private Long receiverId;

    /**
     * 接收人类型：0-客户 1-业务员
     */
    private Integer receiverType;

    /**
     * 通知类型：0-服务 1-系统 2-生意圈 3-商铺 4-工作
     */
    private Integer notifyType;

    /**
     * 业务ID(用于展示消息详情)
     */
    private Long bizId;

    /**
     * 通知摘要
     */
    private String digest;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 状态：0-已查看 1-未查看
     */
    private Integer status;

    private Integer serviceType;

    /**
     * 获取接收人id
     *
     * @return receiver_id
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * 设置接收人id
     *
     * @param receiverId
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 获取接收人类型：0-客户 1-业务员
     *
     * @return receiver_type
     */
    public Integer getReceiverType() {
        return receiverType;
    }

    /**
     * 设置接收人类型：0-客户 1-业务员
     *
     * @param receiverType
     */
    public void setReceiverType(Integer receiverType) {
        this.receiverType = receiverType;
    }

    /**
     * 获取通知类型：0-服务 1-系统 2-生意圈 3-商铺 4-工作
     *
     * @return notify_type
     */
    public Integer getNotifyType() {
        return notifyType;
    }

    /**
     * 设置通知类型：0-服务 1-系统 2-生意圈 3-商铺 4-工作
     *
     * @param notifyType
     */
    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * 获取业务ID(用于展示消息详情)
     *
     * @return biz_id
     */
    public Long getBizId() {
        return bizId;
    }

    /**
     * 设置业务ID(用于展示消息详情)
     *
     * @param bizId
     */
    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    /**
     * 获取通知摘要
     *
     * @return digest
     */
    public String getDigest() {
        return digest;
    }

    /**
     * 设置通知摘要
     *
     * @param digest
     */
    public void setDigest(String digest) {
        this.digest = digest == null ? null : digest.trim();
    }

    /**
     * 获取通知内容
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置通知内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取状态：0-已查看 1-未查看
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0-已查看 1-未查看
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return service_type
     */
    public Integer getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType
     */
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @Title toString
     * Author sunyaping
     * @date 2017/06/05 10:22
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", receiverId=").append(receiverId);
        sb.append(", receiverType=").append(receiverType);
        sb.append(", notifyType=").append(notifyType);
        sb.append(", bizId=").append(bizId);
        sb.append(", digest=").append(digest);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", serviceType=").append(serviceType);
        sb.append("]");
        return sb.toString();
    }
}