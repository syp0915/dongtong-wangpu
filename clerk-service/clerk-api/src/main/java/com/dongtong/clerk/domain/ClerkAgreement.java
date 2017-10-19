package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Package: com.dongtong.clerk.domain.ClerkAgreement.java
 * @Description: 合同表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:17
 * version v1.0.0
 */
public class ClerkAgreement extends BaseBean {
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**

     * 代办签约id
     */
    private Long signId;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 店铺地址
     */
    private String shopAddress;

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
     * 租约起始时间
     */
    private Date startTime;

    /**
     * 租约截止时间
     */
    private Date endTime;

    /**
     * 收租方式(0- 按月收取 1-季付 2-半年付 3-按年收取)
     */
    private Integer rentWay;

    /**
     * 租金 单位元
     */
    private BigDecimal rent;

    /**
     * 转让费
     */
    private BigDecimal transferFee;

    /**
     * 出租方-姓名
     */
    private String rentName;

    /**
     * 出租方-联系电话
     */
    private String rentMoblie;

    /**
     * 承租方-姓名
     */
    private String lesseeName;

    /**
     * 承租方-联系电话
     */
    private String lesseeMoblie;

    /**
     * 状态  0-正常 1-失效
     */
    private String status;

    /**
     * 审核人
     */
    private Integer auditor;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否删除 0-否 1-是
     */
    private Integer isDelete;

    /**
     * 获取代办签约id
     *
     * @return sign_id
     */
    public Long getSignId() {
        return signId;
    }

    /**
     * 设置代办签约id
     *
     * @param signId
     */
    public void setSignId(Long signId) {
        this.signId = signId;
    }

    /**
     * 获取业务员id
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务员id
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取用户id
     *
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户id
     *
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取店铺id
     *
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店铺id
     *
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取店铺地址
     *
     * @return shop_address
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * 设置店铺地址
     *
     * @param shopAddress
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
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
     * 获取租约起始时间
     *
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置租约起始时间
     *
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取租约截止时间
     *
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置租约截止时间
     *
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取收租方式(0- 按月收取 1-季付 2-半年付 3-按年收取)
     *
     * @return rent_way
     */
    public Integer getRentWay() {
        return rentWay;
    }

    /**
     * 设置收租方式(0- 按月收取 1-季付 2-半年付 3-按年收取)
     *
     * @param rentWay
     */
    public void setRentWay(Integer rentWay) {
        this.rentWay = rentWay;
    }

    /**
     * 获取租金 单位元
     *
     * @return rent
     */
    public BigDecimal getRent() {
        return rent;
    }

    /**
     * 设置租金 单位元
     *
     * @param rent
     */
    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    /**
     * 获取转让费
     *
     * @return transfer_fee
     */
    public BigDecimal getTransferFee() {
        return transferFee;
    }

    /**
     * 设置转让费
     *
     * @param transferFee
     */
    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    /**
     * 获取出租方-姓名
     *
     * @return rent_name
     */
    public String getRentName() {
        return rentName;
    }

    /**
     * 设置出租方-姓名
     *
     * @param rentName
     */
    public void setRentName(String rentName) {
        this.rentName = rentName;
    }

    /**
     * 获取出租方-联系电话
     *
     * @return rent_moblie
     */
    public String getRentMoblie() {
        return rentMoblie;
    }

    /**
     * 设置出租方-联系电话
     *
     * @param rentMoblie
     */
    public void setRentMoblie(String rentMoblie) {
        this.rentMoblie = rentMoblie == null ? null : rentMoblie.trim();
    }

    /**
     * 获取承租方-姓名
     *
     * @return lessee_name
     */
    public String getLesseeName() {
        return lesseeName;
    }

    /**
     * 设置承租方-姓名
     *
     * @param lesseeName
     */
    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName == null ? null : lesseeName.trim();
    }

    /**
     * 获取承租方-联系电话
     *
     * @return lessee_moblie
     */
    public String getLesseeMoblie() {
        return lesseeMoblie;
    }

    /**
     * 设置承租方-联系电话
     *
     * @param lesseeMoblie
     */
    public void setLesseeMoblie(String lesseeMoblie) {
        this.lesseeMoblie = lesseeMoblie == null ? null : lesseeMoblie.trim();
    }

    /**
     * 获取状态  0-正常 1-失效
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态  0-正常 1-失效
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取审核人
     *
     * @return auditor
     */
    public Integer getAuditor() {
        return auditor;
    }

    /**
     * 设置审核人
     *
     * @param auditor
     */
    public void setAuditor(Integer auditor) {
        this.auditor = auditor;
    }

    /**
     * 获取描述
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取是否删除 0-否 1-是
     *
     * @return is_delete
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除 0-否 1-是
     *
     * @param isDelete
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:17
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", signId=").append(signId);
        sb.append(", clerkId=").append(clerkId);
        sb.append(", customerId=").append(customerId);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopAddress=").append(shopAddress);
        sb.append(", districtId=").append(districtId);
        sb.append(", districtName=").append(districtName);
        sb.append(", blockId=").append(blockId);
        sb.append(", blockName=").append(blockName);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", rentWay=").append(rentWay);
        sb.append(", rent=").append(rent);
        sb.append(", transferFee=").append(transferFee);
        sb.append(", rentName=").append(rentName);
        sb.append(", rentMoblie=").append(rentMoblie);
        sb.append(", lesseeName=").append(lesseeName);
        sb.append(", lesseeMoblie=").append(lesseeMoblie);
        sb.append(", status=").append(status);
        sb.append(", auditor=").append(auditor);
        sb.append(", description=").append(description);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}