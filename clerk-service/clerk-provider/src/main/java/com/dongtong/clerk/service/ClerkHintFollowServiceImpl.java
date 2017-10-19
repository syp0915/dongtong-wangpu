package com.dongtong.clerk.service;

import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.dto.ClerkHintFollowDTO;
import com.dongtong.clerk.manager.ClerkHintFollowManager;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.clerk.service.ClerkHintFollowServiceImpl
 * @Description: 线索跟进Service
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/2 17:19
 * version V1.0.0
 */
@Service
public class ClerkHintFollowServiceImpl implements ClerkHintFollowService {

	@Autowired
	private ClerkHintFollowManager clerkHintFollowManager;

	/**
	 * 查询线索跟进列表
	 * @param clerkHintFollowDTO
	 * @return
	 */
	@Override
	public ResultDO<List<ClerkHintFollowDTO>> queryListByHintId(ClerkHintFollowDTO clerkHintFollowDTO) {
		if(ValidateHelper.isEmpty(clerkHintFollowDTO) && ValidateHelper.isEmpty(clerkHintFollowDTO.getClerkId())){
			ResultDO resultDO = new ResultDO();
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_CLERT_HINT_ID.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_CLERT_HINT_ID.getMsg());
			return resultDO;
		}
		return clerkHintFollowManager.queryListByHintId(clerkHintFollowDTO);
	}

	@Override
	public ResultDO<Long> add(ClerkHintFollowDTO clerkHintFollowDTO) {

		return clerkHintFollowManager.addClerkHintFollow(clerkHintFollowDTO);
	}

	@Override
	public ResultDO<Boolean> delete(ClerkHintFollowDTO clerkHintFollowDTO) {
		return clerkHintFollowManager.deleteClerkHintFollow(clerkHintFollowDTO);
	}
}
