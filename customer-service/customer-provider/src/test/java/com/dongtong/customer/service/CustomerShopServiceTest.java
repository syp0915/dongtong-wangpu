package com.dongtong.customer.service;

import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.domain.CustomerLiaison;
import com.dongtong.customer.domain.CustomerShopCorrect;
import com.dongtong.customer.dto.req.VisitInfoReqDTO;
import com.dongtong.customer.dto.resp.ShopInfoDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Package com.dongtong.customer.service
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/8 上午10:57
 * version V1.0.0
 */
public class CustomerShopServiceTest extends JunitBaseTest {
    @Autowired
    private CustomerShopService customerShopService;

    @Test
    public void testCreateCorrect(){
        CustomerShopCorrect customerShopCorrect = new CustomerShopCorrect();
        customerShopCorrect.setCustomerId(1L);
        customerShopCorrect.setShopId(2L);
        customerShopCorrect.setTagId(14L);
        customerShopCorrect.setContent("纠错纠错纠错");
        customerShopCorrect.setContacter("张三");
        customerShopCorrect.setContactMobile("");
        ResultDO<Boolean> resultDO = customerShopService.createCorrect(customerShopCorrect);
        System.out.println(resultDO);
    }

    @Test
    public void testCreateVisit() {
        VisitInfoReqDTO visitInfoReqDTO = new VisitInfoReqDTO();
        visitInfoReqDTO.setCustomerId(1L);
        visitInfoReqDTO.setShopId(4L);
        visitInfoReqDTO.setLinkmanPhone("13212345678");
        visitInfoReqDTO.setLinkmanName("张三");
        visitInfoReqDTO.setVisitTime(new Date());
        ResultDO<Long> resultDO = customerShopService.createVisit(visitInfoReqDTO);
        System.out.println(resultDO);
    }

    @Test
    public void testCreateCollected() {
        CustomerCollectedShop customerCollectedShop = new CustomerCollectedShop();
        customerCollectedShop.setCustomerId(1L);
        customerCollectedShop.setShopId(2L);
        ResultDO<Long> resultDO = customerShopService.createCollected(customerCollectedShop);
        System.out.println(resultDO);
    }

    @Test
    public void testCreateLiaisonRecord() {
        CustomerLiaison customerLiaison = new CustomerLiaison();
        customerLiaison.setCustomerId(1L);
        customerLiaison.setShopId(2L);
        customerLiaison.setPhone("13212345678");
        ResultDO<Boolean> resultDO = customerShopService.createLiaisonRecord(customerLiaison);
        System.out.println(resultDO);
    }

    @Test
    public void testQueryShopInfo(){
        ResultDO<ShopInfoDTO> resultDO = customerShopService.queryShopInfo(47L,1L);
        System.out.println(resultDO);
    }

    @Test
    public void testCreateBrowseShop(){
        ResultDO<Boolean> resultDO = customerShopService.createBrowseShop(4L,1L);
        Assert.assertNotNull(resultDO);
    }
}
