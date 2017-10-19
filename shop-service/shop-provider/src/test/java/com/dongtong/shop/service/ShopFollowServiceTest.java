package com.dongtong.shop.service;

import com.dongtong.shop.JunitBaseTest;
import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.dto.LatestShopDTO;
import com.dongtong.shop.dto.ShopFollowDTO;
import com.dongtong.shop.dto.ShopFollowInfoDTO;
import com.dongtong.shop.manager.ShopManager;
import com.shfc.common.result.ResultDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Package com.dongtong.shop.service.ShopFollowServiceTest
 * @Description: ShopFollowService
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 15:52
 * version V1.0.0
 */
public class ShopFollowServiceTest extends JunitBaseTest {
    @Autowired
    private ShopFollowService shopFollowService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopManager shopManager;

    @Test
    public void saveFollow(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        if(resultDO.isSuccess() && resultDO.getData() != null){
            Shop shop = shopManager.getShopById(resultDO.getData().getId());

            ShopFollowInfoDTO dto = new ShopFollowInfoDTO();

            dto.setShopId(shop.getId());
            dto.setContent("测试一下收到货开始的和");
            dto.setClerkId(shop.getClerkId());
            ResultDO<Long> resultDO1 = shopFollowService.saveFollow(dto);
            Assert.assertTrue(resultDO1.isSuccess());
        }
    }

    @Test
    public void getFollowListByShopId(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        if(resultDO.isSuccess() && resultDO.getData() != null){
            ResultDO<List<ShopFollowDTO>> resultDO1 = shopFollowService.getFollowListByShopId(resultDO.getData().getId());

            Assert.assertTrue(resultDO1.isSuccess());
        }
    }

    @Test
    public void testDeleteFollowById(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        if(resultDO.isSuccess() && resultDO.getData() != null){
            Shop shop = shopManager.getShopById(resultDO.getData().getId());
            ShopFollowInfoDTO dto = new ShopFollowInfoDTO();
            dto.setShopId(shop.getId());
            dto.setContent("测试一下收到货开始的和");
            dto.setClerkId(shop.getClerkId());
            ResultDO<Long> resultDO1 = shopFollowService.saveFollow(dto);
            if(resultDO1.isSuccess() && resultDO1.getData() != null){
                ResultDO<Boolean> resultDO2 = shopFollowService.deleteFollowById(resultDO1.getData(), shop.getClerkId());
                Assert.assertTrue(resultDO2.isSuccess());
            }
        }
    }
}
