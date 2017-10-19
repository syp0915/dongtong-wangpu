package com.dongtong.app.controller;

import com.dongtong.app.JunitBaseMockMvcTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Package com.dongtong.app.controller.ClerkTaskControllerTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/17 14:58
 * version V1.0.0
 */
public class ClerkTaskControllerTest extends JunitBaseMockMvcTest {
	@Test
	public void testQueryHintDetail() throws Exception {
		String params = "{\"shopId\":1,\"pageSize\":20,\"pageNumber\":1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkTask/queryClerkTaskList/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
