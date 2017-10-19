package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-12 14:44
 * version V1.0.0
 **/
public class WorkNoticeRespDTO extends BaseNoticeRespDTO implements Serializable{

    /**
     * 工作服务类型 0-实堪 1-约看 2-签约
     *   3-周榜排名 4-月榜排名
     */
    private Integer serviceType;

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }
}
