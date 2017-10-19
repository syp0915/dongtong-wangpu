package com.dongtong.topic.dto.resp;

import com.dongtong.shop.dto.TagDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShopListRespDTO implements Serializable {
    private String recommendWord;
    private Long shopId;
    private String title;
    private Long districtId;
    private String districtName;
    private Long blockId;
    private String blockName;
    private Float area;
    private Integer rentType;     // 0-租客转租  1-房东直发
    private String coverImg;
    private BigDecimal rent;
    private BigDecimal transferFee;
    private Integer isFace;
    private List<TagDTO> featureList;
    private Float distance;
    private String updateTime;

    public String getRecommendWord() {
        return recommendWord;
    }

    public void setRecommendWord(String recommendWord) {
        this.recommendWord = recommendWord;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Integer getRentType() {
        return rentType;
    }

    public void setRentType(Integer rentType) {
        this.rentType = rentType;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
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

    public List<TagDTO> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<TagDTO> featureList) {
        this.featureList = featureList;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ShopListRespDTO{" +
                "recommendWord='" + recommendWord + '\'' +
                ", shopId=" + shopId +
                ", title='" + title + '\'' +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", blockId=" + blockId +
                ", blockName='" + blockName + '\'' +
                ", area=" + area +
                ", rentType=" + rentType +
                ", coverImg='" + coverImg + '\'' +
                ", rent=" + rent +
                ", transferFee=" + transferFee +
                ", isFace=" + isFace +
                ", featureList=" + featureList +
                ", distance=" + distance +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
