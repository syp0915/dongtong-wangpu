package com.dongtong.basic.dto.resp;

import java.io.Serializable;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto
 * @Description :通知列表响应参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-11 17:48
 * version V1.0.0
 **/
public class NotifyTypeListRespDTO implements Serializable{

    /**
     * 通知列表
     */
    private List<NoticeRespDTO> baseNoticeDTOList;

    public List<NoticeRespDTO> getBaseNoticeDTOList() {
        return baseNoticeDTOList;
    }

    public void setBaseNoticeDTOList(List<NoticeRespDTO> baseNoticeDTOList) {
        this.baseNoticeDTOList = baseNoticeDTOList;
    }
}
