package com.dongtong.shop.dto;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-15 13:26
 **/
public class ShopVisitCustomerDTO extends ShopCustomerDTO {
    /**
     * 约看
     */
    private Long customerId;
    /**
     * 商铺状态
     * 出租状态 0-待出租 1-出租中 2-已出租  3-已下架
     */
    private Integer rentStatus;
    /**
     * 发布状态 ：0 已上架、1 已下架
     */
    private Integer shelfStatus;
    /**
     * 其他约看数
     */
    private Integer rowNo;
    /**
     * 约看时间
     */
    private String applyTime;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }
}
