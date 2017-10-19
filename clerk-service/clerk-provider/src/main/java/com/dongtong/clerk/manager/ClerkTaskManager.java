package com.dongtong.clerk.manager;

import com.dongtong.clerk.dao.ClerkHistoryMonthRankingMapper;
import com.dongtong.clerk.dao.ClerkHistoryWeekRankingMapper;
import com.dongtong.clerk.dao.ClerkTaskMapper;
import com.dongtong.clerk.dto.ClerkTaskDetailDTO;
import com.dongtong.clerk.dto.ShopInfoDTO;
import com.dongtong.clerk.query.ClerkTaskQuery;
import com.shfc.common.base.Logger;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.clerk.manager.ClerkTaskManager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/10 17:35
 * version V1.0.0
 */
@Service
public class ClerkTaskManager {

	@Autowired
	private ClerkTaskMapper clerkTaskMapper;

	@Autowired
	private ClerkHistoryMonthRankingMapper clerkHistoryMonthRankingMapper;

	@Autowired
	private ClerkHistoryWeekRankingMapper clerkHistoryWeekRankingMapper;

	public ShopInfoDTO queryShopById(Long shopId){
		return clerkTaskMapper.selectShopById(shopId);
	}


	public Page<ClerkTaskDetailDTO> queryClerkTaskList(ClerkTaskQuery query) throws Exception{

		Page<ClerkTaskDetailDTO> page = new Page<>(query.getPageNumber(), query.getPageSize());
		try {
			page.setQuery(query);
			clerkTaskMapper.queryClerkTaskList(page);
			page.setQuery(null);
		}catch (Exception e){
			Logger.error(e, "查询商铺任务列表异常");
			throw e;
		}

		return page;
	}

}
