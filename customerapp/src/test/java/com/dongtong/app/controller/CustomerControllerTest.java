package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.customer.dto.CustomerSignDTO;
import com.dongtong.customer.dto.CustomerVisitDTO;
import com.dongtong.customer.dto.LoadDTO;
import com.dongtong.customer.query.BaseQuery;
import com.dongtong.shop.query.ShopVisitQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//import com.dongtong.shop.query.ShopVisitQuery;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-11 17:02
 *
 **/
public class CustomerControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testLoadList() throws Exception{
        BaseQuery query=new BaseQuery();
        query.setPageNumber(1);
        query.setPageSize(10);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/loadList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testApplyLoad() throws Exception{
        LoadDTO dto=new LoadDTO();
       dto.setLoanLimit("100");
        dto.setLoanMaturity("3");
        dto.setContactName("悟空");
        dto.setContactMobile("18260121982");
//        dto.setSubscribeTime("2016-09-10 12:00:00");
        dto.setMessageId(5L);
        dto.setSmsVerifyCode("8283");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/applyLoad/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testQueryCustomerInfo() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/queryCustomerInfo/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString("{}")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUpdateHeadPortrait() throws Exception{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("headPortrait","http://03.imgmini.eastday.com/mobile/20170511/20170511125948_426af5dde539e75dd0f2ea1b32146d2e_1.jpeg");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/updateHeadPortrait/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(jsonObject)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUpdateFollow() throws Exception{
        String param = "";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/updateFollow/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(param))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testApplySign() throws Exception{
        CustomerSignDTO dto=new CustomerSignDTO();
        dto.setShopId(2L);
        dto.setContactName("看看");
        dto.setContactMobile("18260121981");
        dto.setSubscribeTime("2017-05-27 12:00:00");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/applySign/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testApplyVisit() throws Exception{
        CustomerSignDTO dto=new CustomerSignDTO();
        dto.setShopId(6L);
        dto.setContactName("吴开阳");
        dto.setContactMobile("13901921669");
        dto.setSubscribeTime("2018-05-28 15:07:50");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/applyVisit/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testServiceInfo() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/serviceInfo/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testShopVisitedList() throws Exception{
        ShopVisitQuery query=new ShopVisitQuery();
        query.setPageNumber(1);
        query.setPageSize(10);
//        query.setLatitude("31.3046400000");
//        query.setLongitude("121.4919600000");


        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/shopVisitedList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testMyVisitStatistic() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/myVisitStatistic/"+VERSION)
        .header(DEFAULT_TOKEN_NAME,tokenKey)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testDeleteAppointment() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/deleteAppointment/"+ VERSION)
        .header(DEFAULT_TOKEN_NAME,tokenKey)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{\"visitId\":7}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testVisitStatistics() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/visitStatistics/"+ VERSION)
                .header(DEFAULT_TOKEN_NAME,tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testLoadStatistics() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/loadStatistics/"+ VERSION)
                .header(DEFAULT_TOKEN_NAME,tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 我的业务参数统计
     * @throws Exception
     */
    @Test
    public void testIndexStatistics() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/indexStatistics/"+ VERSION)
                .header(DEFAULT_TOKEN_NAME,tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    /**
     * h5页面约看申请
     * @throws Exception
     */
    @Test
    public void testApplyVisitShop() throws Exception{
        CustomerVisitDTO dto=new CustomerVisitDTO();
        dto.setDeviceId("oBG83T3jYiL3u1+3nZq//6SbieuwvDSh39Bo+I11SVGX8vD69mfTfQ==");
        dto.setOsType(0);
        dto.setShopId(16L);
        dto.setContactName("吴开阳");
        dto.setContactMobile("1760213283");
        dto.setSubscribeTime("2017-05-25 15:07:50");
        dto.setSmsVerifyCode("5588");
        dto.setMessageId(222L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/h5/applyVisit/" + VERSION)
                //.header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    /**
     * @description 测试修改用户信息
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/8/11 0011 9:16
     */
    @Test
    public void testUpdateCustomerInfo() throws Exception{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("signature","王者不可阻挡");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/updateNickNameAndSign/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(jsonObject)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUpdateCustomerPhone() throws Exception{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("newPhone","18616763371");
        jsonObject.put("oldPhone","13011729867");
        jsonObject.put("smsVerifyCode","4444");
        jsonObject.put("messageId","33333");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/updateCustomerPhone/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(jsonObject)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
