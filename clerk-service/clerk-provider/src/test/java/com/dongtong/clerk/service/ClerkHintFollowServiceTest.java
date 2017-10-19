package com.dongtong.clerk.service;

import com.dongtong.clerk.JunitBaseTest;
import com.dongtong.clerk.domain.ClerkHintFollow;
import com.dongtong.clerk.dto.ClerkHintFollowDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Package com.dongtong.clerk.service.ClerkHintFollowServiceTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/8 11:25
 * version V1.0.0
 */
public class ClerkHintFollowServiceTest extends JunitBaseTest{

	@Autowired
	private ClerkHintFollowService clerkHintFollowService;

	@Test
	public void testAdd(){
		ClerkHintFollowDTO clerkHintFollowDTO = new ClerkHintFollowDTO();
		clerkHintFollowDTO.setHintId(205L);
		clerkHintFollowDTO.setClerkId(101L);
		clerkHintFollowDTO.setContent("dafa");
		ResultDO resultDO = clerkHintFollowService.add(clerkHintFollowDTO);
		Assert.assertNotNull(resultDO);
	}

	@Test
	public void testAdd1(){
		ClerkHintFollowDTO clerkHintFollowDTO = new ClerkHintFollowDTO();
		clerkHintFollowDTO.setHintId(205L);
		clerkHintFollowDTO.setClerkId(102L);
		clerkHintFollowDTO.setContent("dafaaaa");
		ResultDO resultDO = clerkHintFollowService.add(clerkHintFollowDTO);
		Assert.assertNotNull(resultDO);
	}

	@Test
	public void testQueryListByHintId(){
		Long hintId = 205L;
		ClerkHintFollowDTO clerkHintFollowDTO = new ClerkHintFollowDTO();
		clerkHintFollowDTO.setHintId(hintId);
		ResultDO<List<ClerkHintFollowDTO>> listDO = clerkHintFollowService.queryListByHintId(clerkHintFollowDTO);
		Assert.assertNotNull(listDO);
		Assert.assertTrue(listDO.isSuccess());
	}

	@Test
	public void testDelete(){
		ClerkHintFollowDTO clerkHintFollowDTO = new ClerkHintFollowDTO();
		clerkHintFollowDTO.setClerkId(102L);
		clerkHintFollowDTO.setHintId(205L);
		clerkHintFollowDTO.setId(2L);
		ResultDO resultDO = clerkHintFollowService.delete(clerkHintFollowDTO);
		Assert.assertNotNull(resultDO);
	}
}
