package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.AdvInfoDTO;
import com.dongtong.basic.query.AdvQuery;
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
 * @create 2017-05-05 19:51
 **/
public class AdvInfoServiceTest extends JunitBaseTest{

    @Autowired
    private AdvInfoService advInfoService;

    @Test
    public void testAdvList(){
        AdvQuery query=new AdvQuery();
        query.setPosition("0");
        ResultDO<List<AdvInfoDTO>> resultDO=advInfoService.advList(query);
        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testAdList(){
        AdvQuery query=new AdvQuery();
        query.setPosition("2");
        query.setType(2);

        ResultDO<List<AdvInfoDTO>> resultDO=advInfoService.adList(query);
        System.out.println(JSON.toJSONString(resultDO));
    }
}
