package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.shop.dto.ShopFollowInfoDTO;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Package com.dongtong.app.controller.ShopFollowControllerTest
 * @Description: ShopFollowControllerTest
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/11 15:07
 * version V1.0.0
 */
public class ShopFollowControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testAdd() throws Exception {

        ShopFollowInfoDTO dto = new ShopFollowInfoDTO();
        dto.setShopId(1L);
        dto.setClerkId(4L);
        dto.setContent("测试按时间卡斯柯杰卡斯");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/follow/add/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetByShopId() throws Exception {

        JSONObject object = new JSONObject();
        object.put("shopId", 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/follow/getByShopId/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(object.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {

        JSONObject object = new JSONObject();
        object.put("id", 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/follow/delete/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(object.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
