package com.dongtong.basic.dto.resp;

import java.io.Serializable;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description :TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-12 9:37
 * version V1.0.0
 **/
public class NotifyListRespDTO extends HttpBaseBean implements Serializable{

    /**
     * 服务通知响应列表
     */
    private List<ServiceNoticeRespDTO> serviceNoticeRespDTOList;

    /**
     * 生意圈响应列表
     */
    private List<BussinessNoticeRespDTO> bussinessNoticeRespDTOList;

    /**
     * 工作响应列表
     */
    private List<WorkNoticeRespDTO> workNoticeRespDTOList;

    /**
     * 商铺响应列表
     */
    private List<BaseNoticeRespDTO> shopNoticeRespDTOList;

    public List<ServiceNoticeRespDTO> getServiceNoticeRespDTOList() {
        return serviceNoticeRespDTOList;
    }

    public void setServiceNoticeRespDTOList(List<ServiceNoticeRespDTO> serviceNoticeRespDTOList) {
        this.serviceNoticeRespDTOList = serviceNoticeRespDTOList;
    }

    public List<BussinessNoticeRespDTO> getBussinessNoticeRespDTOList() {
        return bussinessNoticeRespDTOList;
    }

    public void setBussinessNoticeRespDTOList(List<BussinessNoticeRespDTO> bussinessNoticeRespDTOList) {
        this.bussinessNoticeRespDTOList = bussinessNoticeRespDTOList;
    }

    public List<WorkNoticeRespDTO> getWorkNoticeRespDTOList() {
        return workNoticeRespDTOList;
    }

    public void setWorkNoticeRespDTOList(List<WorkNoticeRespDTO> workNoticeRespDTOList) {
        this.workNoticeRespDTOList = workNoticeRespDTOList;
    }

    public List<BaseNoticeRespDTO> getShopNoticeRespDTOList() {
        return shopNoticeRespDTOList;
    }

    public void setShopNoticeRespDTOList(List<BaseNoticeRespDTO> shopNoticeRespDTOList) {
        this.shopNoticeRespDTOList = shopNoticeRespDTOList;
    }
}
