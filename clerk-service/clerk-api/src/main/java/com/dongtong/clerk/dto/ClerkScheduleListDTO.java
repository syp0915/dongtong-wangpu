package com.dongtong.clerk.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.dto.ClerkScheduleListDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/7 15:35
 * version V1.0.0
 */
public class ClerkScheduleListDTO implements Serializable {

    private static final long serialVersionUID = 3525746428934468285L;
    private Long id;//id
    private String address;//地址
    private Integer status;//状态1:待签约 2:已签约 3:已取消 1:待约看 2:已约看 3:已取消
    private Integer contract;//1:合同已录入 2：合同未录入(签约列表才有)
    private String scheduleDate;//约见时间

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }
}
