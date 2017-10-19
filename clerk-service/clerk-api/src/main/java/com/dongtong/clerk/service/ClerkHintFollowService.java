package com.dongtong.clerk.service;

import com.dongtong.clerk.dto.ClerkHintFollowDTO;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * @Package com.dongtong.clerk.service.ClerkHintFollowService
 * @Description: 线索跟进Service
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/2 17:10
 * version V1.0.0
 */
public interface ClerkHintFollowService {

	/**
	 * 线索跟进列表查询
	 * @param clerkHintFollowDTO
	 * @return
	 */
	ResultDO<List<ClerkHintFollowDTO>> queryListByHintId(ClerkHintFollowDTO clerkHintFollowDTO);

	/**
	 * 添加线索跟进
	 * @param clerkHintFollowDTO
	 * @return
	 */
	ResultDO<Long> add(ClerkHintFollowDTO clerkHintFollowDTO);

	/**
	 * 删除线索跟进
	 * @param clerkHintFollowDTO
	 * @return
	 */
	ResultDO<Boolean> delete(ClerkHintFollowDTO clerkHintFollowDTO);

}
