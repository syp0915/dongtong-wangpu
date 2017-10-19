package com.dongtong.basic.service;

import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.MetroLineInfoDTO;
import com.dongtong.basic.query.MetroQuery;
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
 * @create 2017-05-05 14:11
 **/
public class MetroServiceTest extends JunitBaseTest {

    @Autowired
    private MetroService metroService;
    @Test
    public void testQueryMetro(){
        MetroQuery query=new MetroQuery();
        query.setCityId("310000");
        ResultDO<List<MetroLineInfoDTO>> resultDO=metroService.queryMetro(query);
        System.out.println(resultDO);
    }
}
