package com.dongtong.app.controller;

import com.dongtong.app.JunitBaseMockMvcTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @description 历史榜单
 * @package com.dongtong.app.controller
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/16 14:00
 * @version v1.0.0
 */
public class HistoryRankingTest extends JunitBaseMockMvcTest {

    /**
     * @description 历史个人排行
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:12
     */
    @Test
    public void testPersonalRanking() throws Exception {
        String params = "{\"classify\":2,\"type\":1,\"number\":4}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/history/historyPersonalRanking/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 历史排行
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:12
     */
    @Test
    public void testHistoryRanking() throws Exception {
        String params = "{\"classify\":\"2\",\"type\":1}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/history/historyRanking/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 历史排行列表
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:12
     */
    @Test
    public void testHisitoryRankingList() throws Exception {
        String params = "{\"classify\":\"2\",\"type\":0,\"number\":21,\"pageSize\":20,\"pageNumber\":1}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/history/historyRankingList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
