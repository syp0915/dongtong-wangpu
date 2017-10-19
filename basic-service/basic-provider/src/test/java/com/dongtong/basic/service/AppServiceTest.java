package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.resp.AppUpdateRespDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-07-19 17:25
 **/
public class AppServiceTest extends JunitBaseTest {

    @Autowired(required = false)
    private AppService appService;

    @Test
    public void  testQueryAppUpdate(){
        ResultDO<AppUpdateRespDTO> resultDO=appService.queryAppUpdate(0,0,"1.1.1");
        System.out.println(JSON.toJSONString(resultDO));
    }
}
