package com.dongtong.customer.dto.req;

import com.dongtong.customer.domain.CustomerVisitShop;
import lombok.Data;

/**
 * @Package com.dongtong.customer.dto.req
 * @Description: 预约看铺
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/12 下午5:26
 * version V1.0.0
 */
@Data
public class VisitInfoReqDTO extends CustomerVisitShop {
    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 地址
     */
    private String address;
}
