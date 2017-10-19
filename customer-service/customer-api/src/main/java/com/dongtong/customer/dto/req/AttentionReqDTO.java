package com.dongtong.customer.dto.req;

import java.io.Serializable;
import java.util.List;

/**
 * @description 
 * @package com.dongtong.customer.dto.req
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/9 0009 13:47
 * @version v1.0.0
 */
public class AttentionReqDTO implements Serializable {

    private Long customerId;

    private List<AttentionVocationReqDTO> vocationList;

    private List<AttentionPlateReqDTO> plateList;

    private List<Integer> areaList;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<AttentionVocationReqDTO> getVocationList() {
        return vocationList;
    }

    public void setVocationList(List<AttentionVocationReqDTO> vocationList) {
        this.vocationList = vocationList;
    }

    public List<AttentionPlateReqDTO> getPlateList() {
        return plateList;
    }

    public void setPlateList(List<AttentionPlateReqDTO> plateList) {
        this.plateList = plateList;
    }

    public List<Integer> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Integer> areaList) {
        this.areaList = areaList;
    }

    @Override
    public String toString() {
        return "AttentionReqDTO{" +
                "customerId=" + customerId +
                ", vocationList=" + vocationList +
                ", plateList=" + plateList +
                ", areaList=" + areaList +
                '}';
    }
}
