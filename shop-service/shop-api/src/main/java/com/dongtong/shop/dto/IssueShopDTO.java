package com.dongtong.shop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Package com.dongtong.shop.dto.IssueShopDTO
 * @Description: 发版商铺
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/5 11:15
 * version V1.0.0
 */
public class IssueShopDTO implements Serializable {
    /**
     * 商铺ID
     */
    private Long shopId;
    /**
     * 业务员id
     */
    private Long clerkId;
    /**
     * 拓铺员ID
     */
    private Long expandClerkId;

    /**
     * 线索id
     */
    private Long hintId;

    /**
     * 出租类型  0-租客转租  1-房东直发
     */
    private Integer rentType;

    /**
     * 出租状态 0-待出租 1-出租中 2-暂不出租 3-已出租
     */
    private Integer rentStatus;
    /**
     * 发布状态 0 已上架、1 已下架
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
     * 配套
     */
    private List<Long> support;
    /**
     * 是否张贴海报 1:是 0:否
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
     * 收租方式(0- 按月收取 1-季付 2-半年付 3-按年收取 4 二月付)
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
     * 合同剩余-单位月
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
     * 商铺照片列表
     */
    private List<ShopImgDTO> shopImgList;
    /**
     *适合经营
     */
    private List<Long> operateRel;
    /**
     *特色
     */
    private List<Long> feature;
    /**
     * 临铺信息左1
     */
    private ShopNearDTO nearLeftOne;
    /**
     * 临铺信息左2
     */
    private ShopNearDTO nearLeftTwo;
    /**
     * 临铺信息右1
     */
    private ShopNearDTO nearRightOne;
    /**
     * 临铺信息右2
     */
    private ShopNearDTO nearRightTwo;
    /**
     * 店铺权重值
     */
    private String weightValue;
    /**
     * 创建人
     */
    private Long creater;

    /**
     * 修改人
     * @return
     */
    private Long modifier;
    /**
     * 线索记录的商铺地址
     */
    private String hintAddress;

    public List<Long> getSupport() {
        return support;
    }

    public void setSupport(List<Long> support) {
        this.support = support;
    }

    public List<Long> getOperateRel() {
        return operateRel;
    }

    public void setOperateRel(List<Long> operateRel) {
        this.operateRel = operateRel;
    }

    public List<Long> getFeature() {
        return feature;
    }

    public void setFeature(List<Long> feature) {
        this.feature = feature;
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

    public Long getHintId() {
        return hintId;
    }

    public void setHintId(Long hintId) {
        this.hintId = hintId;
    }

    public Integer getRentType() {
        return rentType;
    }

    public void setRentType(Integer rentType) {
        this.rentType = rentType;
    }

    public Integer getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(String totalFloor) {
        this.totalFloor = totalFloor;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getDepth() {
        return depth;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public Integer getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Integer getRentWay() {
        return rentWay;
    }

    public void setRentWay(Integer rentWay) {
        this.rentWay = rentWay;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    public Integer getIsFace() {
        return isFace;
    }

    public void setIsFace(Integer isFace) {
        this.isFace = isFace;
    }

    public Integer getCompactResidue() {
        return compactResidue;
    }

    public void setCompactResidue(Integer compactResidue) {
        this.compactResidue = compactResidue;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Float getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(Float waterRate) {
        this.waterRate = waterRate;
    }

    public Float getElectricRate() {
        return electricRate;
    }

    public void setElectricRate(Float electricRate) {
        this.electricRate = electricRate;
    }

    public Float getGasRate() {
        return gasRate;
    }

    public void setGasRate(Float gasRate) {
        this.gasRate = gasRate;
    }

    public Float getPropertyRate() {
        return propertyRate;
    }

    public void setPropertyRate(Float propertyRate) {
        this.propertyRate = propertyRate;
    }

    public List<ShopImgDTO> getShopImgList() {
        return shopImgList;
    }

    public void setShopImgList(List<ShopImgDTO> shopImgList) {
        this.shopImgList = shopImgList;
    }

    public ShopNearDTO getNearLeftOne() {
        return nearLeftOne;
    }

    public void setNearLeftOne(ShopNearDTO nearLeftOne) {
        this.nearLeftOne = nearLeftOne;
    }

    public ShopNearDTO getNearLeftTwo() {
        return nearLeftTwo;
    }

    public void setNearLeftTwo(ShopNearDTO nearLeftTwo) {
        this.nearLeftTwo = nearLeftTwo;
    }

    public ShopNearDTO getNearRightOne() {
        return nearRightOne;
    }

    public void setNearRightOne(ShopNearDTO nearRightOne) {
        this.nearRightOne = nearRightOne;
    }

    public ShopNearDTO getNearRightTwo() {
        return nearRightTwo;
    }

    public void setNearRightTwo(ShopNearDTO nearRightTwo) {
        this.nearRightTwo = nearRightTwo;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
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

    public String getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(String weightValue) {
        this.weightValue = weightValue;
    }

    public Long getExpandClerkId() {
        return expandClerkId;
    }

    public void setExpandClerkId(Long expandClerkId) {
        this.expandClerkId = expandClerkId;
    }

    public String getHintAddress() {
        return hintAddress;
    }

    public void setHintAddress(String hintAddress) {
        this.hintAddress = hintAddress;
    }
}
