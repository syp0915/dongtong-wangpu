package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.clerk.domain.ClerkAgreementImg.java
 * @Description: 合同图片表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:18
 * version v1.0.0
 */
public class ClerkAgreementImg extends BaseBean {
    /**
     * 合同id
     */
    private Long agreementId;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 图片顺序
     */
    private Integer imgIndex;

    /**
     * 是否为封面(0-否 1-是)
     */
    private Integer isCover;

    /**
     * 获取合同id
     *
     * @return agreement_id
     */
    public Long getAgreementId() {
        return agreementId;
    }

    /**
     * 设置合同id
     *
     * @param agreementId
     */
    public void setAgreementId(Long agreementId) {
        this.agreementId = agreementId;
    }

    /**
     * 获取图片地址
     *
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取图片顺序
     *
     * @return img_index
     */
    public Integer getImgIndex() {
        return imgIndex;
    }

    /**
     * 设置图片顺序
     *
     * @param imgIndex
     */
    public void setImgIndex(Integer imgIndex) {
        this.imgIndex = imgIndex;
    }

    /**
     * 获取是否为封面(0-否 1-是)
     *
     * @return is_cover
     */
    public Integer getIsCover() {
        return isCover;
    }

    /**
     * 设置是否为封面(0-否 1-是)
     *
     * @param isCover
     */
    public void setIsCover(Integer isCover) {
        this.isCover = isCover;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:18
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", agreementId=").append(agreementId);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", imgIndex=").append(imgIndex);
        sb.append(", isCover=").append(isCover);
        sb.append("]");
        return sb.toString();
    }
}