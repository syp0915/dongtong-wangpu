package com.dongtong.shop.dto;

import com.dongtong.shop.utils.DateFormatUtils;
import com.dongtong.shop.utils.StringFilterUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Package com.dongtong.shop.dto.BaseShopDTO
 * @Description: shop 基础公共类
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 13:36
 * version V1.0.0
 */
public class BaseShopDTO implements Serializable{
    private static final long serialVersionUID = -8154752365866614260L;
    /**
     * 商铺id
     * M
     */
    private Long id;
    /**
     * 封面图片
     * M
     */
    private String coverImg;
    /**
     * 商铺地址
     * M
     */
    private String address;
    /**
     * 前台是否展示地址0-否 1-是
     */
    private Integer addrIsShow;
    /**
     * 出租类型-文本
     * M
     * eg:直租/转租
     */
    private String rentTypeName;
    /**
     * 店铺面积
     * M
     */
    private Float area;
    /**
     * 区域id
     * M
     */
    private Long districtId;
    /**
     * 区域名称
     * M
     */
    private String districtName;
    /**
     * 板块id
     * M
     */
    private Long blockId;
    /**
     * 板块名称
     * M
     */
    private String blockName;
    /**
     * 收租方式
     * M
     * 0- 按月收取 1-季付 2-半年付 3-按年收取 4-2月付
     */
    private Integer rentWay;

    /**
     * 租金
     * M
     * 单位元
     */
    private BigDecimal rent;
    /**
     * 是否面议
     * N
     * 是否面议 0-否 1-是
     */
    private Integer isFace;
    /**
     * 商铺距当前位置距离
     * M
     */
    private Float distance;
    /**
     * 转让费
     * M
     * 单位元　
     */
    private BigDecimal transferFee;
    /**
     * 更新时间-文本
     * M
     * 1小时内，显示X分钟前，24小时以内，显示x小时前，超过一天，显示具体发布日期 2016-11-11
     */
    private String updateTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 商铺标题 title
     */
    private String title;

    /**
     * 行业名称 String
     */
    private String industryName;

    /**
     * 商铺id
     * M
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 商铺id
     * M
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 封面图片
     * M
     */
    public String getCoverImg() {
        return this.coverImg;
    }

    /**
     * 封面图片
     * M
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 商铺地址
     * M
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 商铺地址
     * M
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAddrIsShow() {
        return addrIsShow;
    }

    public void setAddrIsShow(Integer addrIsShow) {
        this.addrIsShow = addrIsShow;
    }

    /**
     * 出租类型-文本
     * M
     * eg:直租/转租
     */
    public String getRentTypeName() {
        return this.rentTypeName;
    }

    /**
     * 出租类型-文本
     * M
     * eg:直租/转租
     */
    public void setRentTypeName(String rentTypeName) {
        this.rentTypeName = rentTypeName;
    }

    /**
     * 店铺面积
     * M
     */
    public Float getArea() {
        return this.area;
    }

    /**
     * 店铺面积
     * M
     */
    public void setArea(Float area) {
        this.area = area;
        setTitle(this.address);//调用 setTitle 的入口，需要保证mapper.xml area字段的顺序在address和rentTypeName之后
    }

    /**
     * 区域id
     * M
     */
    public Long getDistrictId() {
        return this.districtId;
    }

    /**
     * 区域id
     * M
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    /**
     * 区域名称
     * M
     */
    public String getDistrictName() {
        return this.districtName;
    }

    /**
     * 区域名称
     * M
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * 板块id
     * M
     */
    public Long getBlockId() {
        return this.blockId;
    }

    /**
     * 板块id
     * M
     */
    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    /**
     * 板块名称
     * M
     */
    public String getBlockName() {
        return this.blockName;
    }

    /**
     * 板块名称
     * M
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    /**
     * 收租方式
     * M
     * 0- 按月收取 1-季付 2-半年付 3-按年收取
     */
    public Integer getRentWay() {
        return this.rentWay;
    }

    /**
     * 收租方式
     * M
     * 0- 按月收取 1-季付 2-半年付 3-按年收取
     */
    public void setRentWay(Integer rentWay) {
        this.rentWay = rentWay;
    }

    /**
     * 租金
     * M
     * 单位元
     */
    public BigDecimal getRent() {
        return this.rent;
    }

    /**
     * 租金
     * M
     * 单位元
     */
    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    /**
     * 是否面议
     * N
     * 是否面议 0-否 1-是
     */
    public Integer getIsFace() {
        return this.isFace;
    }

    /**
     * 是否面议
     * N
     * 是否面议 0-否 1-是
     */
    public void setIsFace(Integer isFace) {
        this.isFace = isFace;
    }

    /**
     * 商铺距当前位置距离
     * M
     */
    public Float getDistance() {
        return this.distance;
    }

    /**
     * 商铺距当前位置距离
     * M
     */
    public void setDistance(Float distance) {
        this.distance = distance;
        if(distance != null){
            // 保留两位小数
            this.distance = (float)(Math.round(distance*100))/100;
        }
    }

    /**
     * 转让费
     * M
     * 单位元　
     */
    public BigDecimal getTransferFee() {
        return this.transferFee;
    }

    /**
     * 转让费
     * M
     * 单位元　
     */
    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    /**
     * 更新时间-文本
     * M
     * 1小时内，显示X分钟前，24小时以内，显示x小时前，超过一天，显示具体发布日期 2016-11-11
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间-文本
     * M
     * 1小时内，显示X分钟前，24小时以内，显示x小时前，超过一天，显示具体发布日期 2016-11-11
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        if(modifyTime != null){
            setUpdateTime(DateFormatUtils.parseDateShow(modifyTime));
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(this.addrIsShow!=null && this.addrIsShow.intValue()==0){
            this.title = StringFilterUtils.addressFilter(title);
        }else {
            this.title = this.address;
        }
        if(this.area != null){
            this.title = this.title + this.area.intValue() + "㎡旺铺";
        }

        this.title = this.title.concat(getRentTypeName());
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }
}
