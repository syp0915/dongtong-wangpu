package com.dongtong.shop.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Package com.dongtong.shop.dto
 * @Description: 商铺详情DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/9 上午10:10
 * version V1.0.0
 */
public class ShopDetailDTO extends BaseShopDTO {
    /**
     * 出租类型:  0-租客转租  1-房东直发
     */
    private Integer rentType;
    /**
     * 查看条数
     */
    private Integer visitCount;
    /**
     * 联络条数
     */
    private Integer contactCount;

    /**
     * 铺位照片
     */
    private List<ShopImgDTO> imageList;

    /**
     * 实勘人id
     */
    private Long clerkId;

    /**
     * 实勘人name
     */
    private String clerkName;

    /**
     * 实勘时间
     */
    private String issueShopTime;

    /**
     * 出租状态( 0-待出租 1-出租中 2-暂不出租 3-已出租)
     */
    private Integer rentStatus;

    /**
     * 上下架状态：0 已上架、1 已下架
     */
    private Integer shelfStatus;
    /**
     * 特色
     */
    private List<TagDTO> featureList;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 邻铺信息
     */
    private List<ShopNearDetailDTO> nearInfoList;

    /**
     * 面宽
     */
    private Float width;

    /**
     * 进深
     */
    private Float depth;

    /**
     * 层高
     */
    private Float height;

    /**
     * 可经营业态
     */
    private List<IndustryDTO> industryList;

    /**
     * 电费
     */
    private Float electricRate;

    /**
     * 水费
     */
    private Float waterRate;

    /**
     * 燃气费
     */
    private Float gasRate;

    /**
     * 物业费
     */
    private Float propertyRate;

    /**
     * 配套设施
     */
    private List<TagDTO> supportList;

    /**
     * 经营状态：0-正在经营  1-停业
     */
    private Integer operateStatus;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 是否显示客户信息：0-否 1-是
     */
    private Integer isShow;

    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系人电话
     */
    private String contactTel;

    /**
     * 所在楼层
     */
    private String floor;

    /**
     * 总楼层
     */
    private String totalFloor;

    /**
     * 押金
     */
    private BigDecimal deposit;

    /**
     * 剩余合同
     */
    private Integer compactResidue;

    /**
     * 是否收藏：非空为收藏id
     */
    private boolean isCollected;

    /**
     * 是否预约：非空为约看id
     */
    private boolean isVisit;

    /**
     * 是否贴海报 0-否 1 是
     */
    private Integer isPoster;

    /**
     * 二维码编号
     */
    private String shopCode;

    /**
     * 业态id
     */
    private Long industryId;
    /**
     * 业态名称
     */
    private String name;

    /**
     * 业态父id
     */
    private Long parentId;

    /**
     * 是否删除：0-否 1-是
     */
    private Integer isDelete;

    /**
     * 创建人id
     */
    private Long creater;

    /**
     * 拓铺员id
     */
    private Long expandClerkId;

    /**
     * 拓铺员姓名
     */
    private String expandClerkName;

    /**
     * 拓铺员电话
     */
    private String expandClerkPhone;

    /**
     * 交易员id
     */
    private Long tradeClerkId;

    /**
     * 交易员姓名
     */
    private String tradeClerkName;

    /**
     * 交易员电话
     */
    private String tradeClerkPhone;

    public Integer getRentType() {
        return rentType;
    }

    public void setRentType(Integer rentType) {
        this.rentType = rentType;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    public List<ShopImgDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ShopImgDTO> imageList) {
        this.imageList = imageList;
    }

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getIssueShopTime() {
        return issueShopTime;
    }

    public void setIssueShopTime(String issueShopTime) {
        this.issueShopTime = issueShopTime;
    }

    public Integer getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    public List<TagDTO> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<TagDTO> featureList) {
        this.featureList = featureList;
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

    public List<ShopNearDetailDTO> getNearInfoList() {
        return nearInfoList;
    }

    public void setNearInfoList(List<ShopNearDetailDTO> nearInfoList) {
        this.nearInfoList = nearInfoList;
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

    public List<IndustryDTO> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<IndustryDTO> industryList) {
        this.industryList = industryList;
    }

    public Float getElectricRate() {
        return electricRate;
    }

    public void setElectricRate(Float electricRate) {
        this.electricRate = electricRate;
    }

    public Float getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(Float waterRate) {
        this.waterRate = waterRate;
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

    public List<TagDTO> getSupportList() {
        return supportList;
    }

    public void setSupportList(List<TagDTO> supportList) {
        this.supportList = supportList;
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

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Integer getCompactResidue() {
        return compactResidue;
    }

    public void setCompactResidue(Integer compactResidue) {
        this.compactResidue = compactResidue;
    }

    public boolean getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public boolean getIsVisit() {
        return isVisit;
    }

    public void setIsVisit(boolean isVisit) {
        this.isVisit = isVisit;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Integer getIsPoster() {
        return isPoster;
    }

    public void setIsPoster(Integer isPoster) {
        this.isPoster = isPoster;
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

    public String getExpandClerkPhone() {
        return expandClerkPhone;
    }

    public void setExpandClerkPhone(String expandClerkPhone) {
        this.expandClerkPhone = expandClerkPhone;
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

    public String getTradeClerkPhone() {
        return tradeClerkPhone;
    }

    public void setTradeClerkPhone(String tradeClerkPhone) {
        this.tradeClerkPhone = tradeClerkPhone;
    }
}
