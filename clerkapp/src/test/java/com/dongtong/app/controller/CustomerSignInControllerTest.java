package com.dongtong.app.controller;

import com.dongtong.app.JunitBaseMockMvcTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Package com.dongtong.app.controller.CustomerSignInControllerTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/17 15:05
 * version V1.0.0
 */
public class CustomerSignInControllerTest extends JunitBaseMockMvcTest {

	@Test
	public void testCustomerSignIn() throws Exception {
		String params = "{\"address\":\"武东路198号\",\"longitude\":\"11\",\"latitude\":\"11\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/signIn/customerSignIn/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void testQuerySignList() throws Exception {
		String params = "{\"pageSize\":20,\"pageNumber\":1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/signIn/querySignList/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
