package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.IndustryInfoDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 17:26
 **/
public class IndustryServiceTest extends JunitBaseTest{
    @Autowired
    private IndustryService industryService;
    @Test
    public void testQueryIndustry(){
        ResultDO<List<IndustryInfoDTO>> resultDO=industryService.queryIndustry();
        System.out.println(JSON.toJSONString(resultDO));

    }

    @Test
    public  void testIndustryList(){
        ResultDO<List<IndustryInfoDTO>> resultDO=industryService.industryList();
        System.out.println(JSON.toJSONString(resultDO));
    }
}
