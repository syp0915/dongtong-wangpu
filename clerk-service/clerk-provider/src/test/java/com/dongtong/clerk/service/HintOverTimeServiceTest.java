package com.dongtong.clerk.service;

import com.dongtong.clerk.JunitBaseTest;
import com.dongtong.clerk.domain.HintOverTime;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author zhoumin
 * @version V1.0
 * @date 2017/8/12 下午1:39.
 */
public class HintOverTimeServiceTest extends JunitBaseTest {

    @Autowired(required = false)
    private HintOverTimeService hintOverTimeService;

    @Test
    public void testAddHintOverTime(){
        Long hintId = 195L;
        String date = "2017-8-15 15:43:28";

        ResultDO<Long> resultDO = hintOverTimeService.addHintOverTime(hintId,date);
        System.out.println("####################################");
        System.out.println(resultDO.toString());
    }

    @Test
    public void testOverTimeUpdate(){
        hintOverTimeService.overTimeUpdate();
    }

    @Test
    public void testQueryPushHintOverTime(){
        ResultDO<List<HintOverTime>> resultDO = hintOverTimeService.queryPushHintOverTime();
        System.out.println(resultDO.toString());
    }

}
