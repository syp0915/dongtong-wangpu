package com.dongtong.clerk.dto;

import com.dongtong.clerk.util.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:zhoumin
 * @Description:线索待确认、待核准列表
 * @Date:Created in 14:05 2017/8/5.
 */
public class ClerkHintList implements Serializable {

    private static final long serialVersionUID = 3525746428934468285L;
    /**
     *线索id
     */
    private Long id;

    /**
     * 地址
     */
    private String address;

    /**
     *日程时间
     */
    private String scheduleDate;

    /**
     * 1-旺铺寻租(线索)2-旺铺寻租(实勘)3-预约看铺(租客) 4-租客看铺(房东) 5-签约租铺(租客) 6-租客签约(房东)
     */
    private Integer status;

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

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
