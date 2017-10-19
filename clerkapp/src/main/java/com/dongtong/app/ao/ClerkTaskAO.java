package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.clerk.dto.ClerkTaskDTO;
import com.dongtong.clerk.query.ClerkTaskQuery;
import com.dongtong.clerk.service.ClerkTaskService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.app.ao.ClerkTaskAO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/16 10:19
 * version V1.0.0
 */
@Service
public class ClerkTaskAO {
	@Autowired
	private ClerkTaskService clerkTaskService;

	/**
	 * 商铺代办任务查询
	 * @param clerkTaskQuery
	 * @return
	 */
	public ResultDO<ClerkTaskDTO> queryClerkTaskList(ClerkTaskQuery clerkTaskQuery){
		ResultDO checkDO = checkQueryClerkTaskList(clerkTaskQuery);
		if(!checkDO.isSuccess()){
			return checkDO;
		}
		ResultDO<ClerkTaskDTO> result = clerkTaskService.queryClerkTaskList(clerkTaskQuery);
		return result;
	}

	private ResultDO checkQueryClerkTaskList(ClerkTaskQuery clerkTaskQuery) {
		ResultDO checkDO = new ResultDO();
		if(ValidateHelper.isEmpty(clerkTaskQuery.getShopId())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_SHOP_ID.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_SHOP_ID.getMsg());
			return checkDO;
		}
		checkDO.setSuccess(true);
		return checkDO;
	}
}
