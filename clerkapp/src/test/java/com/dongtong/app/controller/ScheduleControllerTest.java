package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.customer.dto.ScheduleTypeDTO;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Author:zhoumin
 * @Description:
 * @Date:Created in 10:28 2017/8/12.
 */
public class ScheduleControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testAddScheduleByType() throws Exception{
        ScheduleTypeDTO scheduleTypeDTO = new ScheduleTypeDTO();
        scheduleTypeDTO.setBizId(16L);
        scheduleTypeDTO.setType(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/schedule/addScheduleByType/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(scheduleTypeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
