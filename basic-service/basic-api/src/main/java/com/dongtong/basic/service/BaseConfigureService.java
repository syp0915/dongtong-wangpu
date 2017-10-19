package com.dongtong.basic.service;

import com.dongtong.basic.domain.BaseConfigure;
import com.shfc.common.result.ResultDO;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description：TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-06-22 16:35
 * version V1.0.0
 **/
public interface BaseConfigureService {

    /**
     * 根据code查询配置参数值
     * @param code
     * @return
     */
    ResultDO<BaseConfigure> queryCustomerServiceTel(Long code);
}
