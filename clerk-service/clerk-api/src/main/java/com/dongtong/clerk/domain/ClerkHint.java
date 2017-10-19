package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.clerk.domain.ClerkHint.java
 * @Description: 线索表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/05 11:19
 * version v1.0.0
 */
public class ClerkHint extends BaseBean {
    /**
     * 商铺地址
     */
    private String shopAddress;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 区域id
     */
    private Long districtId;

    /**
     * 区域名称
     */
    private String districtName;

    /**
     * 板块id
     */
    private Long blockId;

    /**
     * 板块名称
     */
    private String blockName;

    /**
     * 联系人姓名
     */
    private String linkmanName;

    /**
     * 联系人手机号
     */
    private String linkmanPhone;

    /**
     * 预约时间
     */
    private Date subscribeTime;

    /**
     * 发布人ID
     */
    private Long issuerId;

    /**
     * 发布人手机
     */
    private String issuerPhone;

    /**
     * 发布人类型(0用户1业务员)
     */
    private Integer issuerType;

    /**
     * 拥有者(业务员ID)
     */
    private Long ownerId;

    /**
     * 线索状态(0待认领—> 1待实堪——>2已收铺-->3已废弃)
     */
    private Integer status;

    /**
     * 认领时间
     */
    private Date claimTime;

    /**
     * 收铺时间（发布商铺）
     */
    private Date issueShopTime;

    /**
     * 废弃时间
     */
    private Date discardTime;

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * 废弃原因
     */
    private String discardCause;

    private String remark;

    /**
     * 线索来源 1:扫街 2:客户 3:网站
     */
    private Integer hintFrom;

    /**
     * 拓铺员编号
     */
    private Long expandClerkId;

    /**
     * 拓铺员姓名
     */
    private String expandClerkName;

    /**
     * 交易员编号
     */
    private Long tradeClerkId;

    /**
     * 交易员姓名
     */
    private String tradeClerkName;
    /**
     * 总楼层
     */
    private Integer totalFloor;

    /**
     * 所在楼层 多层以逗号隔开
     */
    private String floor;

    /**
     * 是否张贴海报 1:是 0:否
     */
    private Integer hasPoster;

    /**
     * 确认时间
     */
    private Date confirmTime;

    /**
     * 核准时间
     */
    private Date checkTime;

    /**
     * 二维码
     */
    private String shopCode;

    /**
     * 前端是否展示地址
     */
    private Integer addrIsShow;

    /**
     * 前端是否展示手机号
     */
    private Integer isShow;

    /**
     * 获取商铺地址
     *
     * @return shop_address
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * 设置商铺地址
     *
     * @param shopAddress
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    /**
     * 获取经度
     *
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 获取纬度
     *
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 获取区域id
     *
     * @return district_id
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * 设置区域id
     *
     * @param districtId
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    /**
     * 获取区域名称
     *
     * @return district_name
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置区域名称
     *
     * @param districtName
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    /**
     * 获取板块id
     *
     * @return block_id
     */
    public Long getBlockId() {
        return blockId;
    }

    /**
     * 设置板块id
     *
     * @param blockId
     */
    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    /**
     * 获取板块名称
     *
     * @return block_name
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * 设置板块名称
     *
     * @param blockName
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName == null ? null : blockName.trim();
    }

    /**
     * 获取联系人姓名
     *
     * @return linkman_name
     */
    public String getLinkmanName() {
        return linkmanName;
    }

    /**
     * 设置联系人姓名
     *
     * @param linkmanName
     */
    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName == null ? null : linkmanName.trim();
    }

    /**
     * 获取联系人手机号
     *
     * @return linkman_phone
     */
    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    /**
     * 设置联系人手机号
     *
     * @param linkmanPhone
     */
    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone == null ? null : linkmanPhone.trim();
    }

    /**
     * 获取预约时间
     *
     * @return subscribe_time
     */
    public Date getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * 设置预约时间
     *
     * @param subscribeTime
     */
    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    /**
     * 获取发布人ID
     *
     * @return issuer_id
     */
    public Long getIssuerId() {
        return issuerId;
    }

    /**
     * 设置发布人ID
     *
     * @param issuerId
     */
    public void setIssuerId(Long issuerId) {
        this.issuerId = issuerId;
    }

    /**
     * 获取发布人手机
     *
     * @return issuer_phone
     */
    public String getIssuerPhone() {
        return issuerPhone;
    }

    /**
     * 设置发布人手机
     *
     * @param issuerPhone
     */
    public void setIssuerPhone(String issuerPhone) {
        this.issuerPhone = issuerPhone == null ? null : issuerPhone.trim();
    }

    /**
     * 获取发布人类型(0用户1业务员)
     *
     * @return issuer_type
     */
    public Integer getIssuerType() {
        return issuerType;
    }

    /**
     * 设置发布人类型(0用户1业务员)
     *
     * @param issuerType
     */
    public void setIssuerType(Integer issuerType) {
        this.issuerType = issuerType;
    }

    /**
     * 获取拥有者(业务员ID)
     *
     * @return owner_id
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * 设置拥有者(业务员ID)
     *
     * @param ownerId
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取线索状态(0待认领—> 1待实堪——>2已收铺-->3已废弃)
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置线索状态(0待认领—> 1待实堪——>2已收铺-->3已废弃)
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取认领时间
     *
     * @return claim_time
     */
    public Date getClaimTime() {
        return claimTime;
    }

    /**
     * 设置认领时间
     *
     * @param claimTime
     */
    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    /**
     * 获取收铺时间（发布商铺）
     *
     * @return issue_shop_time
     */
    public Date getIssueShopTime() {
        return issueShopTime;
    }

    /**
     * 设置收铺时间（发布商铺）
     *
     * @param issueShopTime
     */
    public void setIssueShopTime(Date issueShopTime) {
        this.issueShopTime = issueShopTime;
    }

    /**
     * 获取废弃时间
     *
     * @return discard_time
     */
    public Date getDiscardTime() {
        return discardTime;
    }

    /**
     * 设置废弃时间
     *
     * @param discardTime
     */
    public void setDiscardTime(Date discardTime) {
        this.discardTime = discardTime;
    }

    /**
     * 获取标签id
     *
     * @return tag_id
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * 设置标签id
     *
     * @param tagId
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取废弃原因
     *
     * @return discard_cause
     */
    public String getDiscardCause() {
        return discardCause;
    }

    /**
     * 设置废弃原因
     *
     * @param discardCause
     */
    public void setDiscardCause(String discardCause) {
        this.discardCause = discardCause == null ? null : discardCause.trim();
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    public Integer getHintFrom() {
        return hintFrom;
    }

    public void setHintFrom(Integer hintFrom) {
        this.hintFrom = hintFrom;
    }

    public Long getExpandClerkId() {
        return expandClerkId;
    }

    public void setExpandClerkId(Long expandClerkId) {
        this.expandClerkId = expandClerkId;
    }

    public String getExpandClerkName() {
        return expandClerkName;
    }

    public void setExpandClerkName(String expandClerkName) {
        this.expandClerkName = expandClerkName;
    }

    public Long getTradeClerkId() {
        return tradeClerkId;
    }

    public void setTradeClerkId(Long tradeClerkId) {
        this.tradeClerkId = tradeClerkId;
    }

    public String getTradeClerkName() {
        return tradeClerkName;
    }

    public void setTradeClerkName(String tradeClerkName) {
        this.tradeClerkName = tradeClerkName;
    }

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getHasPoster() {
        return hasPoster;
    }

    public void setHasPoster(Integer hasPoster) {
        this.hasPoster = hasPoster;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public Integer getAddrIsShow() {
        return addrIsShow;
    }

    public void setAddrIsShow(Integer addrIsShow) {
        this.addrIsShow = addrIsShow;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/05 11:19
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopAddress=").append(shopAddress);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", districtId=").append(districtId);
        sb.append(", districtName=").append(districtName);
        sb.append(", blockId=").append(blockId);
        sb.append(", blockName=").append(blockName);
        sb.append(", linkmanName=").append(linkmanName);
        sb.append(", linkmanPhone=").append(linkmanPhone);
        sb.append(", subscribeTime=").append(subscribeTime);
        sb.append(", issuerId=").append(issuerId);
        sb.append(", issuerPhone=").append(issuerPhone);
        sb.append(", issuerType=").append(issuerType);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", status=").append(status);
        sb.append(", claimTime=").append(claimTime);
        sb.append(", issueShopTime=").append(issueShopTime);
        sb.append(", discardTime=").append(discardTime);
        sb.append(", tagId=").append(tagId);
        sb.append(", discardCause=").append(discardCause);
        sb.append("]");
        return sb.toString();
    }
}