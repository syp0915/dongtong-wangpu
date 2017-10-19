package com.dongtong.clerk.service;

import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.dto.ClerkTaskDTO;
import com.dongtong.clerk.dto.ShopInfoDTO;
import com.dongtong.clerk.manager.ClerkTaskManager;
import com.dongtong.clerk.query.ClerkTaskQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.clerk.service.ClerkTaskServiceImpl
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/10 17:33
 * version V1.0.0
 */
@Service
public class ClerkTaskServiceImpl implements ClerkTaskService {

	@Autowired
	private ClerkTaskManager clerkTaskManager;

	@Override
	public ResultDO<ClerkTaskDTO> queryClerkTaskList(ClerkTaskQuery query) {
		ResultDO<ClerkTaskDTO> resultDO = new ResultDO<>();

		ResultDO checkDO = checkQueryParams(query);
		if(!checkDO.isSuccess()){
			return resultDO;
		}

		try {
			ShopInfoDTO shop = clerkTaskManager.queryShopById(query.getShopId());
			if(ValidateHelper.isEmpty(shop)){
				resultDO.setSuccess(false);
				resultDO.setErrCode(ErrorConstant.SHOP_NOT_EXIST.getCode());
				resultDO.setErrMsg(ErrorConstant.SHOP_NOT_EXIST.getMsg());
				return resultDO;
			}
			ClerkTaskDTO taskDTO = new ClerkTaskDTO();
			taskDTO.setAddress(shop.getAddress());
			taskDTO.setArea(shop.getArea());
			taskDTO.setPendingList(clerkTaskManager.queryClerkTaskList(query));
			resultDO.setData(taskDTO);
			resultDO.setSuccess(true);
		}catch (Exception e){
			Logger.error(e, "商铺代办任务列表查询异常",e);
			resultDO.setErrMsg("系统异常，请联系管理员");
		}

		return resultDO;
	}


	private ResultDO checkQueryParams(ClerkTaskQuery query) {
		ResultDO checkDO = new ResultDO();
		if(ValidateHelper.isEmpty(query.getShopId())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_SHOP_ID.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_SHOP_ID.getMsg());
			return checkDO;
		}
		checkDO.setSuccess(true);
		return checkDO;
	}
}
