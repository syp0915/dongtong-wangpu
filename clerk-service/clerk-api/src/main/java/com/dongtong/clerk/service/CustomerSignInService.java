package com.dongtong.clerk.service;

import com.dongtong.clerk.dto.CustomerSignInDTO;
import com.dongtong.clerk.query.CustomerSignInQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;

/**
 * @Package com.dongtong.clerk.service.CustomerSignInService
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/12 9:08
 * version V1.0.0
 */
public interface CustomerSignInService {

	/**
	 * 用户签到
	 * @param customerSignDTO
	 * @return
	 */
	ResultDO<Integer> customerSignIn(CustomerSignInDTO customerSignDTO);

	/**
	 * 签到历史列表查询
	 * @param signQuery
	 * @return
	 */
	ResultDO<Page<CustomerSignInDTO>> querySignList(CustomerSignInQuery signQuery);
}
