package com.dongtong.clerk.dao;

import com.dongtong.clerk.dto.ClerkTaskDetailDTO;
import com.dongtong.clerk.dto.ShopInfoDTO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package com.dongtong.clerk.manager.ClerkTaskMapper
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/10 17:59
 * version V1.0.0
 */
@Repository
public interface ClerkTaskMapper {

	/**
	 * 商铺代办任务列表查询
	 * @Author zhanghz
	 * @param page
	 * @return
	 */
	List<ClerkTaskDetailDTO> queryClerkTaskList(Page<ClerkTaskDetailDTO> page);

	ShopInfoDTO selectShopById(Long shopId);
}
