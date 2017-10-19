package com.dongtong.customer.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.domain.CustomerAttentionPlate;
import com.dongtong.customer.domain.CustomerAttentionVocation;
import com.dongtong.customer.dto.CustomerDTO;
import com.dongtong.customer.dto.CustomerInfoDTO;
import com.dongtong.customer.dto.req.AttentionPlateReqDTO;
import com.dongtong.customer.dto.req.AttentionReqDTO;
import com.dongtong.customer.dto.req.AttentionVocationReqDTO;
import com.dongtong.customer.dto.resp.StatisticDTO;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-10 11:05
 **/
public class CustomerInfoServiceTest extends JunitBaseTest {

    @Autowired
    private CustomerInfoService customerInfoService;


    @Test
    public void testCustomerInfo(){
        ResultDO<CustomerDTO> resultDO=customerInfoService.customerInfo(3L);
        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testUpdateHeadPortrait(){
        ResultDO<String> resultDO=customerInfoService.updateHeadPortrait(2L,"http://news.xinhuanet.com/photo/2017-05/10/1120949149_14943891242461n.jpg");
        System.out.println(JSON.toJSONString(resultDO));

    }

    @Test
    public void testQueryCustomerInfo(){
        ResultDO<CustomerInfoDTO> resultDO=customerInfoService.queryCustomerInfo(3L);
        Logger.info(this.getClass(),"接口返回的内容："+JSON.toJSONString(resultDO));
    }
    @Test
    public void testMyCollectStatistic(){
        ResultDO<StatisticDTO> resultDO = customerInfoService.myCollectStatistic(3L);
        System.out.println(JSON.toJSON(resultDO));
    }


    @Test
    public void testUpdateFollow(){
        AttentionReqDTO dto = new AttentionReqDTO();

        Long customerId = 3L;
        List<AttentionVocationReqDTO> vocationList = new ArrayList<>();
        List<AttentionPlateReqDTO> plateList = new ArrayList<>();
        List<Integer> areaList = new ArrayList<>();;
        areaList.add(3);
        areaList.add(5);
        AttentionVocationReqDTO vocation1 = new AttentionVocationReqDTO();
        vocation1.setVocationId(1L);
        vocation1.setVocationName("餐饮业");
        AttentionVocationReqDTO vocation2 = new AttentionVocationReqDTO();
        vocation2.setVocationId(2L);
        vocation2.setVocationName("生活服务");
        AttentionVocationReqDTO vocation3 = new AttentionVocationReqDTO();
        vocation3.setVocationId(3L);
        vocation3.setVocationName("服饰购物");
        vocationList.add(vocation1);
        vocationList.add(vocation2);
        vocationList.add(vocation3);

        AttentionPlateReqDTO plate1 = new AttentionPlateReqDTO();
        plate1.setCityId(2L);
        plate1.setCityName("上海");
        plate1.setDistrictId(2L);
        plate1.setDistrictName("杨浦区");
        plate1.setPlateId(4L);
        plate1.setPlateName("五角场");
        AttentionPlateReqDTO plate2 = new AttentionPlateReqDTO();
        plate2.setCityId(2L);
        plate2.setCityName("上海");
        plate2.setDistrictId(2L);
        plate2.setDistrictName("杨浦区");
        plate2.setPlateId(4L);
        plate2.setPlateName("五角场");
        AttentionPlateReqDTO plate3 = new AttentionPlateReqDTO();
        plate3.setCityId(2L);
        plate3.setCityName("上海");
        plate3.setDistrictId(2L);
        plate3.setDistrictName("杨浦区");
        plateList.add(plate1);
        plateList.add(plate2);
        plateList.add(plate3);

        dto.setCustomerId(customerId);
        dto.setVocationList(vocationList);
        dto.setPlateList(plateList);
        dto.setAreaList(areaList);
        ResultDO<String> resultDO=customerInfoService.updateFollow(dto);
        Logger.info(this.getClass(),"*******************************************************");
        Logger.info(this.getClass(),"接口返回的值：" + resultDO);
        Logger.info(this.getClass(),"*******************************************************");
    }

    @Test
    public void testMyVisitStatistic(){
        ResultDO<StatisticDTO> resultDO = customerInfoService.myVisitStatistic(2L);
        System.out.println(JSON.toJSON(resultDO));
    }

    @Test
    public void testUpdateCustomerInfo(){
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.setHeadPortrait("http://news.xinhuanet.com/photo/2017-05/10/1120949149_14943891242461n.jpg");
        dto.setNickName("王者");
        dto.setSignature("我是一个安静的用户");
        dto.setPhone("18516763371");
        dto.setCustomerId(3L);
        ResultDO<String> resultDO=customerInfoService.updateCustomerInfo(dto);
        System.out.println(JSON.toJSONString(resultDO));

    }
}
