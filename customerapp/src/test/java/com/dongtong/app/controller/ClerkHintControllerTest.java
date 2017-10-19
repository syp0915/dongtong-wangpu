package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.clerk.dto.ClerkHintDTO;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

/**
 * @author sunyaping
 * @Package com.dongtong.app.controller
 * @Description ：
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-15 15:32
 * version V1.0.0
 **/
public class ClerkHintControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testAddClerkHint() throws Exception{
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        clerkHintDTO.setIssuerId(userId);
        clerkHintDTO.setShopAddress("临平路23号");
        clerkHintDTO.setLinkmanName("小摸啊");
        clerkHintDTO.setLinkmanPhone("18260121982");
        clerkHintDTO.setDistrictId(2L);
        clerkHintDTO.setDistrictName("杨浦区");
        clerkHintDTO.setBlockId(1L);
        clerkHintDTO.setBlockName("武东路");
//        clerkHintDTO.setSubscribeTime("2017-06-12 12:23:22");
        clerkHintDTO.setCreateTime(new Date());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/addClerkHint/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(clerkHintDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetReleaseTotal() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/getReleaseTotal/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}
