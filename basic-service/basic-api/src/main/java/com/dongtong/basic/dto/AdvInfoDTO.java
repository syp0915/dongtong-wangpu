package com.dongtong.basic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 19:27
 **/
@Data
public class AdvInfoDTO implements Serializable {

    private static final long serialVersionUID = -3360937377471529830L;
    private String adPicUrl;
    private String type;//跳转链接类型 0-外部 1-内部连接
    private String toUrl;

    private String title;//标题
    private String innerType;//内部链接类型 1：旺铺寻租、2：预约看铺、3：找资金、3：旺铺铺源、4：生意圈频道、5找服务频道
    private Long businessId;//内部链接业务Id
}
