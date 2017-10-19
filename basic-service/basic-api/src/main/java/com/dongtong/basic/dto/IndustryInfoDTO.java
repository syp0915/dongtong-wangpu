package com.dongtong.basic.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 14:58
 **/
public class IndustryInfoDTO implements Serializable {
    private String industryName;//业态名称
    private Long industryId;//业态Id
    private String depict;//经营范围描述

    List<BusinessInfoDTO> list;


    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public List<BusinessInfoDTO> getList() {
        return list;
    }

    public void setList(List<BusinessInfoDTO> list) {
        this.list = list;
    }
}
