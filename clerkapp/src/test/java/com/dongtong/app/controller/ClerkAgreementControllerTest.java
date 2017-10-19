package com.dongtong.app.controller;

import com.dongtong.app.JunitBaseMockMvcTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 19:24
 * @since 1.0
 */
public class ClerkAgreementControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testInsertContract() throws Exception {
        String params = "{\n" +
                "    \"shopAddress\": \"黄浦江\",\n" +
                "    \"startTime\": \"20170516123321\",\n" +
                "    \"endTime\": \"20180516123321\",\n" +
                "    \"rent\": \"100\",\n" +
                "    \"transferFee\": \"100\",\n" +
                "    \"rentName\": \"张三\",\n" +
                "    \"rentMobile\": \"13023251564\",\n" +
                "    \"lesseeName\": \"李四\",\n" +
                "    \"lesseeMobile\": \"15021546545\",\n" +
                "    \"agreementUrl\": [\"www.baidu.com\",\"aaaa\",\"img.adada.sadad\"],\n" +
                "    \"rentWay\": \"1\",\n" +
                "    \"signId\": \"1\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/contract/insertContract/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void testGetAgreementInfo() throws Exception {
        String params = "{\"signId\":\"2\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/contract/getAgreementInfo/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
