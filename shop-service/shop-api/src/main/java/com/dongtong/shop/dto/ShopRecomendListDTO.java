package com.dongtong.shop.dto;

import com.dongtong.shop.utils.StringFilterUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description 旺铺导购推荐旺铺
 * @package com.dongtong.shop.dto
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/11 0011 16:31
 * @version v1.0.0
 */
public class ShopRecomendListDTO implements Serializable {

    private Long shopId;
    private String coverImg;
    private String rentTypeName;
    private Float area;
    private Long districtId;
    private String districtName;
    private Long blockId;
    private String blockName;
    private Integer rentType;
    private Integer addrIsShow;

    private String title;
    private String address;

    private BigDecimal rent;
    private Integer isFace;
    private Float distance;
    private BigDecimal transferFee;
    private String updateTime;

    private List<TagDTO> featureList;

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<TagDTO> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<TagDTO> featureList) {
        this.featureList = featureList;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getRentTypeName() {
        return rentTypeName;
    }

    public void setRentTypeName(String rentTypeName) {
        this.rentTypeName = rentTypeName;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
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

    public Integer getRentType() {
        return rentType;
    }

    public void setRentType(Integer rentType) {
        this.rentType = rentType;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public Integer getIsFace() {
        return isFace;
    }

    public void setIsFace(Integer isFace) {
        this.isFace = isFace;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        setTitle(this.address);
    }

    public String getTitle() {
        return this.title;
    }

    public void setAddrIsShow(Integer addrIsShow) {
        this.addrIsShow = addrIsShow;
    }

    public void setTitle(String title) {
        if(this.addrIsShow!=null && this.addrIsShow.intValue()==0){
            this.title = StringFilterUtils.addressFilter(title);
        }

        if(this.area != null){
            if(this.title != null){
                this.title = this.title + this.area.intValue() + "㎡旺铺";
            } else {
                this.title = this.area.intValue() + "㎡旺铺";
            }
        }

        if(this.title != null){
            this.title = this.title.concat(getRentTypeName());
        }
    }

    @Override
    public String toString() {
        return "ShopRecomendListDTO{" +
                "shopId=" + shopId +
                ", featureList=" + featureList +
                ", coverImg='" + coverImg + '\'' +
                ", rentTypeName='" + rentTypeName + '\'' +
                ", area=" + area +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", blockId=" + blockId +
                ", blockName='" + blockName + '\'' +
                ", rentType=" + rentType +
                ", rent=" + rent +
                ", isFace=" + isFace +
                ", distance=" + distance +
                ", transferFee=" + transferFee +
                ", updateTime='" + updateTime + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}