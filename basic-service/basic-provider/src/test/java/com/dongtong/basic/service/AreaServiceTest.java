package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.RegionInfoDTO;
import com.dongtong.basic.query.AreaQuery;
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
 * @create 2017-05-04 18:02
 **/
public class AreaServiceTest extends JunitBaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testQueryArea(){
        AreaQuery query=new AreaQuery();
        query.setCityId("310000");
        ResultDO<List<RegionInfoDTO>> resultDO=areaService.qyeryArea(query);
        System.out.println(JSON.toJSONString(resultDO));

    }
}
