package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.resp.AliyunSTSRespDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/25 下午5:42.
 */
public class AliyunServiceTest extends JunitBaseTest{

    @Autowired(required = false)
    private AliyunService aliyunService;

    @Test
    public void testGetTmpAccessInfo(){
        ResultDO<AliyunSTSRespDTO> resultDO = aliyunService.getTmpAccessInfo("customer", "iOS", 1L);
        System.out.println("result-------------->" + JSON.toJSONString(resultDO));
    }
}
