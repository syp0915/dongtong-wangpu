package com.dongtong.clerk.dto;

import com.dongtong.clerk.util.DateFormatUtils;
import com.shfc.common.base.ValidateHelper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.clerk.dto
 * @Description:TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 10:45
 * version V1.0.0
 **/
public class ClerkHintDTO implements Serializable {

    private static final long serialVersionUID = -7193096031350078242L;

    private Long id;
    /**
     * 商铺地址
     */
    private String shopAddress;
    /**
     * 废弃原因
     */
    private String discardCause;

    private String remark;

    private int tagId;
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
    private String subscribeTime;

    /**
     * 短信验证码
     */
    private String smsVerifyCode;
    /**
     * 短信验证码ID
     */
    private Long	messageId;
    /**
     * 图片验证码
     */
    private String	picVerifyCode;
    /**
     * 图片验证码Id
     */
    private Long 	picVerifyId;

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
     * 线索状态
     */
    private Integer status;

    /**
     * 距离
     */
    private String distance;

    /**
     * 拥有者ID
     */
    private Long ownerId;

    /**
     * 拥有者姓名
     */
    private String ownerName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 时间展示
     */
    private String showCreateTime;

    /**
     * 排序类型
     */
    private String orderType;

    /**
     * 排序字段
     */
    private String orderColumn;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    private int pageSize;
    private int pageNumber;

    /**
     * 线索来源 1:扫街 2:客户 3:网站
     */
    private Integer hintFrom;

    /**
     * 总楼层
     */
    private Integer totalFloor;

    /**
     * 所在楼层
     */
    private String floor;

    /**
     * 是否张贴海报 1:是 0:否
     */
    private Integer hasPoster;

    /**
     * 二维码编号
     */
    private String shopCode;

    /**
     * 前端是否展示手机号
     */
    private Integer isShow;

    /**
     * 前端是否展示地址
     */
    private Integer addrIsShow;

    /**
     * 拓铺员编号
     */
    private Long expandClerkId;

    /**
     * 拓铺员名称
     */
    private String expandClerkName;

    /**
     * 交易员编号
     */
    private String tradeClerkId;

    /**
     * 交易员名称
     */
    private String tradeClerkName;

    /**
     * 拓铺员电话
     */
    private String expandClerkPhone;

    /**
     * 交易员名称
     */
    private String tradeClerkPhone;

    /**
     * 线索跟进列表
     */
    private List followList;

    /**
     * 确认时间
     */
    private Date confirmTime;

    /**
     * 核准时间
     */
    private Date checkTime;

    /**
     * 修改人编号
     */
    private Long modifier;

    /**
     * 店铺图片列表
     */
    private List<ShopImgDTO> shopImgList;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getDiscardCause() {
        return discardCause;
    }

    public void setDiscardCause(String discardCause) {
        this.discardCause = discardCause;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Long getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Long issuerId) {
        this.issuerId = issuerId;
    }

    public String getIssuerPhone() {
        return issuerPhone;
    }

    public void setIssuerPhone(String issuerPhone) {
        this.issuerPhone = issuerPhone;
    }

    public Integer getIssuerType() {
        return issuerType;
    }

    public void setIssuerType(Integer issuerType) {
        this.issuerType = issuerType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
        if(!ValidateHelper.isEmpty(distance)){
            // 保留两位小数
            this.distance = (float)(Math.round(Float.parseFloat(distance)*100))/100+"";
        }
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getPicVerifyCode() {
        return picVerifyCode;
    }

    public void setPicVerifyCode(String picVerifyCode) {
        this.picVerifyCode = picVerifyCode;
    }

    public Long getPicVerifyId() {
        return picVerifyId;
    }

    public void setPicVerifyId(Long picVerifyId) {
        this.picVerifyId = picVerifyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if(createTime != null){
            setShowCreateTime(DateFormatUtils.parseDateShow(createTime));
        }
    }

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
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

    public Integer getHintFrom() {
        return hintFrom;
    }

    public void setHintFrom(Integer hintFrom) {
        this.hintFrom = hintFrom;
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

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getAddrIsShow() {
        return addrIsShow;
    }

    public void setAddrIsShow(Integer addrIsShow) {
        this.addrIsShow = addrIsShow;
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

    public String getTradeClerkId() {
        return tradeClerkId;
    }

    public void setTradeClerkId(String tradeClerkId) {
        this.tradeClerkId = tradeClerkId;
    }

    public String getTradeClerkName() {
        return tradeClerkName;
    }

    public void setTradeClerkName(String tradeClerkName) {
        this.tradeClerkName = tradeClerkName;
    }

    public String getExpandClerkPhone() {
        return expandClerkPhone;
    }

    public void setExpandClerkPhone(String expandClerkPhone) {
        this.expandClerkPhone = expandClerkPhone;
    }

    public String getTradeClerkPhone() {
        return tradeClerkPhone;
    }

    public void setTradeClerkPhone(String tradeClerkPhone) {
        this.tradeClerkPhone = tradeClerkPhone;
    }

    public List getFollowList() {
        return followList;
    }

    public void setFollowList(List followList) {
        this.followList = followList;
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

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public List<ShopImgDTO> getShopImgList() {
        return shopImgList;
    }

    public void setShopImgList(List<ShopImgDTO> shopImgList) {
        this.shopImgList = shopImgList;
    }
}
