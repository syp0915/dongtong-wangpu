package com.dongtong.clerk.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Package com.dongtong.clerk.dto.ClerkSignOrViewDetailDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/8 10:15
 * version V1.0.0
 */
public class ClerkSignOrViewDetailDTO implements Serializable {
    private Long id;//约看详情ID
    private String address;//地址
    private Integer status;//如果类型是约看 (1:待约看 2:已约看 3:已取消) 如果类型是签约(1:待签约 2:已签约 3:已取消 )
    private String customerName;//客户名称
    private String customerPhone;//客户手机号码
    private String landlord;//房东
    private String landlordPhone;//房东电话
    private String scheduleDate;//日程时间
    private Integer contractStatus;//合同状态（1：合同录入 2:合同未录入）
    private Long clerkId;//业务员ID
    private Integer tagId;//取消状态ID
    private String coverImgUrl;//店铺封面
    private Long shopId;//商铺ID
    private String scheduleIds;//日程ID
    private long districtId;//区域id
    private String districtName;//区域名称
    private long blockId;//板块id
    private String blockName;//板块名称
    private List<RemarkDTO> remarkList;

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public long getBlockId() {
        return blockId;
    }

    public void setBlockId(long blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getScheduleIds() {
        return scheduleIds;
    }

    public void setScheduleIds(String scheduleIds) {
        this.scheduleIds = scheduleIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
    }

    public String getLandlordPhone() {
        return landlordPhone;
    }

    public void setLandlordPhone(String landlordPhone) {
        this.landlordPhone = landlordPhone;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public List<RemarkDTO> getRemarkList() {
        return remarkList;
    }

    public void setRemarkList(List<RemarkDTO> remarkList) {
        this.remarkList = remarkList;
    }
}
