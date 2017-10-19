package com.dongtong.shop.jobs;

import com.dongtong.shop.JunitBaseTest;
import com.dongtong.shop.dto.LatestShopDTO;
import com.dongtong.shop.service.ShopService;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package com.dongtong.shop.jobs.ShopStationRelJobTest
 * @Description: ShopStationRelJob
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 17:05
 * version V1.0.0
 */
public class ShopStationRelJobTest extends JunitBaseTest {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopStationRelJob shopStationRelJob;

    @Test
    public void testAsyncShopStationRelJob(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        if(resultDO.isSuccess() && resultDO.getData() != null){
            shopStationRelJob.asyncShopStationRelJob(resultDO.getData().getId());
        }
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
