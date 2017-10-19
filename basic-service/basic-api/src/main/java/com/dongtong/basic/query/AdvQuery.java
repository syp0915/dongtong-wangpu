package com.dongtong.basic.query;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * 广告查询
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-08 14:09
 **/
@Data
public class AdvQuery implements Serializable {
    private static final long serialVersionUID = -8870574568468965863L;

    private String position;//0:首页，1:找服务，2:旺铺寻租,3:找资金,4:预约看铺

    private Integer type;//2-开屏页 3-广告

}
