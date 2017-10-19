package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.clerk.dto.UpdateClerkDetailDTO;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Package com.dongtong.app.controller.ClerkControllerTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/16 10:47
 * version V1.0.0
 */
public class ClerkControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testGetClerkDetailById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clerk/getClerkDetailById/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUpdateClerkDetail() throws Exception {
        UpdateClerkDetailDTO updateClerkDetailDTO = new UpdateClerkDetailDTO();
        updateClerkDetailDTO.setUrl("www.baidu.com");
        updateClerkDetailDTO.setRealName("肖晓明");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clerk/updateClerkDetail/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(updateClerkDetailDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testIsNewNotification() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clerk/isNewNotification/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}
