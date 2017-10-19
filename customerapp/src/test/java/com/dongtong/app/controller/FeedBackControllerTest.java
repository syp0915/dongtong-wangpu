package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.customer.dto.req.FeedBackReqDTO;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author sunyaping
 * @Package com.dongtong.app.controller
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-15 15:58
 * version V1.0.0
 **/
public class FeedBackControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testAddFeedBack()throws Exception{
        FeedBackReqDTO feedBackReqDTO=new FeedBackReqDTO();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        feedBackReqDTO.setCustomerId(userId);
        feedBackReqDTO.setFeedback("和jfk费用分担分享ADFS给try一天");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/feedBack/addFeedBack/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(feedBackReqDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
}
