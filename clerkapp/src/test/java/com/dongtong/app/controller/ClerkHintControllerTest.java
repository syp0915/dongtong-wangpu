package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.clerk.dto.ClerkHintDetailReqDTO;
import com.dongtong.clerk.query.ClerkHintTypeQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Package com.dongtong.app.controller.ClerkHintControllerTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/16 16:16
 * version V1.0.0
 */
public class ClerkHintControllerTest  extends JunitBaseMockMvcTest {

	@Test
	public void testPublishHint() throws Exception {
		String params = "{\"shopAddress\":\"武东路198号\",\"districtId\":1,\"districtName\":\"杨浦区\",\"linkmanName\":\"白百何\",\"linkmanPhone\":\"13498191271\",\"longitude\":\"111\",\"latitude\":\"111\",\"blockId\":11,\"blockName\":\"财大科技园\",\"issuerType\":\"\",\"subscribeTime\":\"20170516101000\",\"remark\":\"商铺线索\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/publishHint/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void testQueryHintDetail() throws Exception {
		String params = "{\"clerkId\":1,\"longitude\":\"11\",\"latitude\":\"11\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/queryHintDetail/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void testQueryHintList() throws Exception {
		String params = "{\"hintOwnType\":1,\"areaId\":1,\"orderType\":1,\"longitude\":\"11\",\"latitude\":\"11\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/queryHintList/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void testQueryHintList1() throws Exception {
		String params = "{\"hintOwnType\":2,\"longitude\":\"121.490769\",\"latitude\":\"31.243668\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/queryHintList/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}


	@Test
	public void testQueryClerkHintListForMap() throws Exception {
		String params = "{\"blockId\":11L}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/queryClerkHintListForMap/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void testQueryClerkHintListForMap1() throws Exception {
		String params = "{\"blockId\":11L,\"ownType\":2}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/queryClerkHintListForMap/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void testGetShopClueList() throws Exception {
		String params = "{\"pageSize\":\"3\",\"pageNum\":\"1\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/getShopClueList/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	@Test
	public void testGetShopClueInfo() throws Exception {
		String params = "{\"id\":\"2\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/getShopClueInfo/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	@Test
	public void testShopClaim() throws Exception {
		String params = "{\"id\":\"2\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/shopClaim/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	@Test
	public void testUpdateSubscribeTime() throws Exception {
		String params = "{\"id\":\"2\",\"subscribeTime\":\"20170518122326\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/updateSubscribeTime/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	@Test
	public void testUpdateClueStatus() throws Exception {
		String params = "{\"id\":\"2\",\"discardCause\":\"不想租\",\"tagId\":\"1\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/updateClueStatus/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(params))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void testQueryHintListByStatus() throws Exception {
		ClerkHintTypeQuery query = new ClerkHintTypeQuery();
		query.setType(1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/queryHintListByStatus/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, tokenKey)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(JSON.toJSONString(query)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void testGetHintDetailInfo() throws Exception {
		ClerkHintDetailReqDTO reqDTO = new ClerkHintDetailReqDTO();
		reqDTO.setType(1);
		reqDTO.setId(1L);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/clerkHint/getHintDetailInfo/" + VERSION)
				.header(DEFAULT_TOKEN_NAME, tokenKey)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(JSON.toJSONString(reqDTO)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
