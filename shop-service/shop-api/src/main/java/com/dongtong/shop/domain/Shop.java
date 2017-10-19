package com.dongtong.shop.domain;

import com.shfc.common.httpbean.BaseBean;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Package: com.dongtong.shop.domain.Shop.java
 * @Description: 商铺
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author lv bin
 * @date 2017/06/22 16:09
 * version v1.0.0
 */
public class Shop extends BaseBean {
    /**
     * 业务员id -认领人
     */
    private Long clerkId;

    /**
     * 线索id
     */
    private Long hintId;

    /**
     * 出租类型  0-租客转租  1-房东直发
     */
    private Integer rentType;

    /**
     * 出租状态( 0-待出租 1-出租中 2-暂不出租 3-已出租)
     */
    private Integer rentStatus;
    /**
     * 0 已上架、1 已下架
     */
    private Integer shelfStatus;
    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 是否在前台显示 0-否 1-是
     */
    private Integer isShow;

    /**
     * 店铺地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;
    /**
     * 前台是否展示地址0-否 1-是
     */
    private Integer addrIsShow;
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
     * 所在楼层
     */
    private String floor;

    /**
     * 总楼层
     */
    private String totalFloor;

    /**
     * 店铺面积-平米
     */
    private Float area;

    /**
     * 面宽-米
     */
    private Float width;

    /**
     * 进深-米
     */
    private Float depth;

    /**
     * 层高-米
     */
    private Float height;
    /**
     * 是否贴海报 0-否 1 是
     */
    private Integer isPoster;
    /**
     * 二维码编号
     */
    private String shopCode;

    /**
     * 经营状态  0-正在经营  1-停业 
     */
    private Integer operateStatus;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 行业-所属业态id
     */
    private Long industryId;

    /**
     * 收租方式(0- 按月收取 1-季付 2-半年付 3-按年收取 4-两月付)
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
     * 是否面议 0-否 1-是
     */
    private Integer isFace;

    /**
     * 合同剩余：0-无租赁合同 1-原租赁合同已到期 2-原租赁合同未到期
     */
    private Integer compactResidue;

    /**
     * 押金-单位元
     */
    private BigDecimal deposit;

    /**
     * 水费-元/吨
     */
    private Float waterRate;

    /**
     * 电费-元-度
     */
    private Float electricRate;

    /**
     * 燃气费-元/立方
     */
    private Float gasRate;

    /**
     * 物业费-元/平米/月
     */
    private Float propertyRate;

    /**
     * 撤下人类型(0-业务员 1- 用户 2-系统)
     */
    private Integer undoType;

    /**
     * 撤下人id
     */
    private Long undoId;

    /**
     * 撤下标签id
     */
    private Long undoTagId;

    /**
     * 撤下内容
     */
    private String undoContent;

    /**
     * 撤下时间
     */
    private Date undoTime;

    /**
     * 认领时间
     */
    private Date claimTime;

    /**
     * 是否删除 0-否 1-是
     */
    private Integer isDelete;

    /**
     * 审核人
     */
    private Long auditor;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 审核内容
     */
    private String auditContent;
    /**
     * 店铺权重值
     */
    private Float weightValue;
    /**
     * 获取业务员id -认领人
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务员id -认领人
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取线索id
     *
     * @return hint_id
     */
    public Long getHintId() {
        return hintId;
    }

    /**
     * 设置线索id
     *
     * @param hintId
     */
    public void setHintId(Long hintId) {
        this.hintId = hintId;
    }

    /**
     * 获取出租类型  0-租客转租  1-房东直发
     *
     * @return rent_type
     */
    public Integer getRentType() {
        return rentType;
    }

    /**
     * 设置出租类型  0-租客转租  1-房东直发
     *
     * @param rentType
     */
    public void setRentType(Integer rentType) {
        this.rentType = rentType;
    }

    /**
     * 获取出租状态 -9 :待认领  0:待出租 1:出租中 2:已出租  3:已下架
     *
     * @return rent_status
     */
    public Integer getRentStatus() {
        return rentStatus;
    }

    /**
     * 设置出租状态 -9 :待认领  0:待出租 1:出租中 2:已出租  3:已下架
     *
     * @param rentStatus
     */
    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    /**
     * 获取联系人
     *
     * @return contacter
     */
    public String getContacter() {
        return contacter;
    }

    /**
     * 设置联系人
     *
     * @param contacter
     */
    public void setContacter(String contacter) {
        this.contacter = contacter == null ? null : contacter.trim();
    }

    /**
     * 获取联系电话
     *
     * @return contact_tel
     */
    public String getContactTel() {
        return contactTel;
    }

    /**
     * 设置联系电话
     *
     * @param contactTel
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    /**
     * 获取是否在前台显示 0-否 1-是
     *
     * @return is_show
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置是否在前台显示 0-否 1-是
     *
     * @param isShow
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取店铺地址
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置店铺地址
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
     * 获取所在楼层
     *
     * @return floor
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置所在楼层
     *
     * @param floor
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * 获取总楼层
     *
     * @return total_floor
     */
    public String getTotalFloor() {
        return totalFloor;
    }

    /**
     * 设置总楼层
     *
     * @param totalFloor
     */
    public void setTotalFloor(String totalFloor) {
        this.totalFloor = totalFloor == null ? null : totalFloor.trim();
    }

    /**
     * 获取店铺面积-平米
     *
     * @return area
     */
    public Float getArea() {
        return area;
    }

    /**
     * 设置店铺面积-平米
     *
     * @param area
     */
    public void setArea(Float area) {
        this.area = area;
    }

    /**
     * 获取面宽-米
     *
     * @return width
     */
    public Float getWidth() {
        return width;
    }

    /**
     * 设置面宽-米
     *
     * @param width
     */
    public void setWidth(Float width) {
        this.width = width;
    }

    /**
     * 获取进深-米
     *
     * @return depth
     */
    public Float getDepth() {
        return depth;
    }

    /**
     * 设置进深-米
     *
     * @param depth
     */
    public void setDepth(Float depth) {
        this.depth = depth;
    }

    /**
     * 获取层高-米
     *
     * @return height
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 设置层高-米
     *
     * @param height
     */
    public void setHeight(Float height) {
        this.height = height;
    }

    /**
     * 获取二维码编号
     *
     * @return shop_code
     */
    public String getShopCode() {
        return shopCode;
    }

    /**
     * 设置二维码编号
     *
     * @param shopCode
     */
    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    /**
     * 获取经营状态  0-正在经营  1-停业 
     *
     * @return operate_status
     */
    public Integer getOperateStatus() {
        return operateStatus;
    }

    /**
     * 设置经营状态  0-正在经营  1-停业 
     *
     * @param operateStatus
     */
    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }

    /**
     * 获取店铺名称
     *
     * @return shop_name
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店铺名称
     *
     * @param shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 获取行业-所属业态id
     *
     * @return industry_id
     */
    public Long getIndustryId() {
        return industryId;
    }

    /**
     * 设置行业-所属业态id
     *
     * @param industryId
     */
    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    /**
     * 获取收租方式(0- 按月收取 1-季付 2-半年付 3-按年收取 4-两月付)
     *
     * @return rent_way
     */
    public Integer getRentWay() {
        return rentWay;
    }

    /**
     * 设置收租方式(0- 按月收取 1-季付 2-半年付 3-按年收取 4-两月付)
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
     * 获取是否面议 0-否 1-是
     *
     * @return is_face
     */
    public Integer getIsFace() {
        return isFace;
    }

    /**
     * 设置是否面议 0-否 1-是
     *
     * @param isFace
     */
    public void setIsFace(Integer isFace) {
        this.isFace = isFace;
    }

    /**
     * 获取合同剩余：0-无租赁合同 1-原租赁合同已到期 2-原租赁合同未到期
     *
     * @return compact_residue
     */
    public Integer getCompactResidue() {
        return compactResidue;
    }

    /**
     * 设置合同剩余：0-无租赁合同 1-原租赁合同已到期 2-原租赁合同未到期
     *
     * @param compactResidue
     */
    public void setCompactResidue(Integer compactResidue) {
        this.compactResidue = compactResidue;
    }

    /**
     * 获取押金-单位元
     *
     * @return deposit
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * 设置押金-单位元
     *
     * @param deposit
     */
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    /**
     * 获取水费-元/吨
     *
     * @return water_rate
     */
    public Float getWaterRate() {
        return waterRate;
    }

    /**
     * 设置水费-元/吨
     *
     * @param waterRate
     */
    public void setWaterRate(Float waterRate) {
        this.waterRate = waterRate;
    }

    /**
     * 获取电费-元-度
     *
     * @return electric_rate
     */
    public Float getElectricRate() {
        return electricRate;
    }

    /**
     * 设置电费-元-度
     *
     * @param electricRate
     */
    public void setElectricRate(Float electricRate) {
        this.electricRate = electricRate;
    }

    /**
     * 获取燃气费-元/立方
     *
     * @return gas_rate
     */
    public Float getGasRate() {
        return gasRate;
    }

    /**
     * 设置燃气费-元/立方
     *
     * @param gasRate
     */
    public void setGasRate(Float gasRate) {
        this.gasRate = gasRate;
    }

    /**
     * 获取物业费-元/平米/月
     *
     * @return property_rate
     */
    public Float getPropertyRate() {
        return propertyRate;
    }

    /**
     * 设置物业费-元/平米/月
     *
     * @param propertyRate
     */
    public void setPropertyRate(Float propertyRate) {
        this.propertyRate = propertyRate;
    }

    /**
     * 获取撤下人类型(0-业务员 1- 用户 2-系统)
     *
     * @return undo_type
     */
    public Integer getUndoType() {
        return undoType;
    }

    /**
     * 设置撤下人类型(0-业务员 1- 用户 2-系统)
     *
     * @param undoType
     */
    public void setUndoType(Integer undoType) {
        this.undoType = undoType;
    }

    /**
     * 获取撤下人id
     *
     * @return undo_id
     */
    public Long getUndoId() {
        return undoId;
    }

    /**
     * 设置撤下人id
     *
     * @param undoId
     */
    public void setUndoId(Long undoId) {
        this.undoId = undoId;
    }

    /**
     * 获取撤下标签id
     *
     * @return undo_tag_id
     */
    public Long getUndoTagId() {
        return undoTagId;
    }

    /**
     * 设置撤下标签id
     *
     * @param undoTagId
     */
    public void setUndoTagId(Long undoTagId) {
        this.undoTagId = undoTagId;
    }

    /**
     * 获取撤下内容
     *
     * @return undo_content
     */
    public String getUndoContent() {
        return undoContent;
    }

    /**
     * 设置撤下内容
     *
     * @param undoContent
     */
    public void setUndoContent(String undoContent) {
        this.undoContent = undoContent == null ? null : undoContent.trim();
    }

    /**
     * 获取撤下时间
     *
     * @return undo_time
     */
    public Date getUndoTime() {
        return undoTime;
    }

    /**
     * 设置撤下时间
     *
     * @param undoTime
     */
    public void setUndoTime(Date undoTime) {
        this.undoTime = undoTime;
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
     * 获取审核人
     *
     * @return auditor
     */
    public Long getAuditor() {
        return auditor;
    }

    /**
     * 设置审核人
     *
     * @param auditor
     */
    public void setAuditor(Long auditor) {
        this.auditor = auditor;
    }

    /**
     * 获取审核状态
     *
     * @return audit_status
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态
     *
     * @param auditStatus
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取审核内容
     *
     * @return audit_content
     */
    public String getAuditContent() {
        return auditContent;
    }

    /**
     * 设置审核内容
     *
     * @param auditContent
     */
    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent == null ? null : auditContent.trim();
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Integer getAddrIsShow() {
        return addrIsShow;
    }

    public void setAddrIsShow(Integer addrIsShow) {
        this.addrIsShow = addrIsShow;
    }

    public Integer getIsPoster() {
        return isPoster;
    }

    public void setIsPoster(Integer isPoster) {
        this.isPoster = isPoster;
    }

    public Float getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(Float weightValue) {
        this.weightValue = weightValue;
    }

    /**
     * @Title toString
     * @Author lv bin
     * @Date 2017/06/22 16:09
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        return "Shop{" +
                "clerkId=" + clerkId +
                ", hintId=" + hintId +
                ", rentType=" + rentType +
                ", rentStatus=" + rentStatus +
                ", shelfStatus=" + shelfStatus +
                ", contacter='" + contacter + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", isShow=" + isShow +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", addrIsShow=" + addrIsShow +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", blockId=" + blockId +
                ", blockName='" + blockName + '\'' +
                ", floor='" + floor + '\'' +
                ", totalFloor='" + totalFloor + '\'' +
                ", area=" + area +
                ", width=" + width +
                ", depth=" + depth +
                ", height=" + height +
                ", isPoster=" + isPoster +
                ", shopCode='" + shopCode + '\'' +
                ", operateStatus=" + operateStatus +
                ", shopName='" + shopName + '\'' +
                ", industryId=" + industryId +
                ", rentWay=" + rentWay +
                ", rent=" + rent +
                ", transferFee=" + transferFee +
                ", isFace=" + isFace +
                ", compactResidue=" + compactResidue +
                ", deposit=" + deposit +
                ", waterRate=" + waterRate +
                ", electricRate=" + electricRate +
                ", gasRate=" + gasRate +
                ", propertyRate=" + propertyRate +
                ", undoType=" + undoType +
                ", undoId=" + undoId +
                ", undoTagId=" + undoTagId +
                ", undoContent='" + undoContent + '\'' +
                ", undoTime=" + undoTime +
                ", claimTime=" + claimTime +
                ", isDelete=" + isDelete +
                ", auditor=" + auditor +
                ", auditStatus=" + auditStatus +
                ", auditContent='" + auditContent + '\'' +
                ", weightValue=" + weightValue +
                '}';
    }
}