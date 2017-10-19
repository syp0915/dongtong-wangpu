package com.dongtong.basic.service;

import com.dongtong.basic.JunitBaseTest;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package com.dongtong.basic.service.BaseShopNumberServiceTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/8/14 16:35
 * version V1.0.0
 */
public class BaseShopNumberServiceTest extends JunitBaseTest {
    @Autowired
    private BaseShopNumberService baseShopNumberService;

    @Test
    public void  testCreateShopNumber(){
        ResultDO<String> resultDO = baseShopNumberService.createShopNumber("021");
        System.out.println(resultDO.toString());
    }
}
