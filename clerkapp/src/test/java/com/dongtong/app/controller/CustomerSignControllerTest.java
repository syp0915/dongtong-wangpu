package com.dongtong.app.controller;

import com.dongtong.app.JunitBaseMockMvcTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 18:54
 * @since 1.0
 */
public class CustomerSignControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testGetSignList() throws Exception {
        String params = "{\"pageSize\":\"3\",\"pageNumber\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sign/getSignList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void testGetSignInfo() throws Exception {
        String params = "{\"id\":\"2\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sign/getSignInfo/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void testUpdateSignTime() throws Exception {
        String params = "{\"id\":\"3\",\"visitTime\":\"20170516223355\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sign/updateSignTime/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void testUpdateSignStatus() throws Exception {
        String params = "{\"id\":\"2\",\"cancelCause\":\"不想租\",\"tagId\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sign/updateSignStatus/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
