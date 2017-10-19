package com.dongtong.clerk.service;

import com.dongtong.clerk.dto.ClerkTaskDTO;
import com.dongtong.clerk.query.ClerkTaskQuery;
import com.shfc.common.result.ResultDO;

/**
 * @Package com.dongtong.clerk.service.ClerkTaskService
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/10 17:12
 * version V1.0.0
 */
public interface ClerkTaskService {

	/**
	 * 查询商铺代办任务列表
	 * @Author zhanghz
	 * @param query
	 * @return
	 */
	ResultDO<ClerkTaskDTO> queryClerkTaskList(ClerkTaskQuery query);

}
