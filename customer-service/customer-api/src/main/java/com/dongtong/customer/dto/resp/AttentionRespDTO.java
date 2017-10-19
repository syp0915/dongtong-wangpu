package com.dongtong.customer.dto.resp;

import com.dongtong.customer.domain.CustomerAttentionPlate;
import com.dongtong.customer.domain.CustomerAttentionVocation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @package com.dongtong.customer.dto.resp
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/8 0008 17:29
 * @version v1.2.0
 *
 * v1.2--新增
 */
public class AttentionRespDTO implements Serializable{

    private List<AttentionVocationRespDTO> vocationList = new ArrayList<>();

    private List<AttentionPlateRespDTO> plateList = new ArrayList<>();

    private List<Integer> areaList = new ArrayList<>();//关注面积  eg：[1,2] 1：20m²以下 2：20-50m²  3：50-100m² 4：100-200m²  5：200-500m²  6:500-1000m²  7:1000m²以上

    public List<AttentionVocationRespDTO> getVocationList() {
        return vocationList;
    }

    public void setVocationList(List<AttentionVocationRespDTO> vocationList) {
        this.vocationList = vocationList;
    }

    public List<AttentionPlateRespDTO> getPlateList() {
        return plateList;
    }

    public void setPlateList(List<AttentionPlateRespDTO> plateList) {
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
        return "AttentionRespDTO{" +
                "vocationList=" + vocationList +
                ", plateList=" + plateList +
                ", areaList=" + areaList +
                '}';
    }
}
