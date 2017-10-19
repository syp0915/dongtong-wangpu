package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.domain.BaseConfigure;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-06-23 10:22
 * version V1.0.0
 **/
public class BaseConfigureTest extends JunitBaseTest {

    @Autowired
    private BaseConfigureService baseConfigureService;

    @Test
    public void testCustomerServiceTel() {
        ResultDO<BaseConfigure> resultDO = baseConfigureService.queryCustomerServiceTel(0L);
        System.out.println("result----------->" + JSON.toJSON(resultDO.getData()));
    }
}
