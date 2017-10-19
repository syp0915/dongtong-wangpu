package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.basic.query.PicVerifyReqQuery;
import com.dongtong.basic.query.SmsVerifyReqQuery;
import com.dongtong.customer.query.LoginReqQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/12 上午9:31.
 */
public class VerifyControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testLoginVerify() throws Exception {
        LoginReqQuery query = new LoginReqQuery();
        query.setUserPhone("13888888888");
        query.setSmsVerifyCode("8907");
        query.setMessageId(1L);
        query.setPicVerifyCode("2087");
        query.setPicVerifyId(2L);
        query.setInviteCode("7890");
        query.setOsType(0);
        query.setDeviceId("DSAFSDRTRVDFVER432432432FDSFDS");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/verify/loginVerify/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testSendSmsVerifyCode() throws Exception {
        SmsVerifyReqQuery query = new SmsVerifyReqQuery();
        query.setUserPhone("13482493139");
        query.setSendType(0);
        query.setUseScene(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/verify/sendSmsVerifyCode/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetPicVerifyCode() throws Exception {
        PicVerifyReqQuery query = new PicVerifyReqQuery();
        query.setUseScene(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/verify/getPicVerifyCode/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
