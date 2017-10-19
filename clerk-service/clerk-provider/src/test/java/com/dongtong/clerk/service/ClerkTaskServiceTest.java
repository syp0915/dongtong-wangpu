package com.dongtong.clerk.service;

import com.dongtong.clerk.JunitBaseTest;
import com.dongtong.clerk.dto.ClerkTaskDTO;
import com.dongtong.clerk.query.ClerkTaskQuery;
import com.shfc.common.result.ResultDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package com.dongtong.clerk.service.ClerkTaskServiceTask
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/10 18:40
 * version V1.0.0
 */
public class ClerkTaskServiceTest extends JunitBaseTest {
	@Autowired
	private ClerkTaskService clerkTaskService;
	@Test
	public void testQueryClerkTaskList(){
		ClerkTaskQuery query = new ClerkTaskQuery();
		query.setClerkId(107L);
		query.setShopId(27L);
		query.setPageNumber(1);
		query.setPageSize(20);
		ResultDO<ClerkTaskDTO> taskDTO = clerkTaskService.queryClerkTaskList(query);
		Assert.assertTrue(taskDTO.isSuccess());
	}
}
