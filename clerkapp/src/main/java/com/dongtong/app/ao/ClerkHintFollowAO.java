package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.utils.Validate;
import com.dongtong.clerk.dto.ClerkHintFollowDTO;
import com.dongtong.clerk.service.ClerkHintFollowService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.app.ao.ClerkHintFollowAO
 * @Description: 线索跟进AO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/14 13:32
 * version V1.0.0
 */
@Service
public class ClerkHintFollowAO {

	@Autowired
	private ClerkHintFollowService clerkHintFollowService;
	/**
	 * 线索跟进删除
	 * @param clerkHintFollowDTO
	 * @return
	 */
	public ResultDO<Long> delete(ClerkHintFollowDTO clerkHintFollowDTO) {
		if(ValidateHelper.isEmpty(clerkHintFollowDTO)||ValidateHelper.isEmpty(clerkHintFollowDTO.getId())){
			ResultDO checkDO = new ResultDO();
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			return checkDO;
		}
		ResultDO resultDO = clerkHintFollowService.delete(clerkHintFollowDTO);
		return resultDO;
	}

	/**
	 * 线索跟进添加
	 * @param clerkHintFollowDTO
	 * @return
	 */
	public ResultDO<Long> add(ClerkHintFollowDTO clerkHintFollowDTO) {
		ResultDO<Long> checkDO = new ResultDO<Long>();
		if(ValidateHelper.isEmpty(clerkHintFollowDTO) || ValidateHelper.isEmpty(clerkHintFollowDTO.getHintId())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return checkDO;
		}

		if(ValidateHelper.isEmpty(clerkHintFollowDTO.getClerkId())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_CHERK_ID.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_CHERK_ID.getMsg());
			return checkDO;
		}

		if(ValidateHelper.isEmpty(clerkHintFollowDTO.getContent())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_FOLLOW_CONTENT.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_FOLLOW_CONTENT.getMsg());
			return checkDO;
		}
		ResultDO<Long> resultDO = clerkHintFollowService.add(clerkHintFollowDTO);
		return resultDO;
	}

	/**
	 * 线索跟进列表查询
	 * @param clerkHintFollowDTO
	 * @return
	 */
	public ResultDO<List<ClerkHintFollowDTO>> queryListByHintId(ClerkHintFollowDTO clerkHintFollowDTO) {

		if(ValidateHelper.isEmpty(clerkHintFollowDTO) || ValidateHelper.isEmpty(clerkHintFollowDTO.getHintId())){
			ResultDO checkDO = new ResultDO();
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return checkDO;
		}
		ResultDO<List<ClerkHintFollowDTO>> resultDO = clerkHintFollowService.queryListByHintId(clerkHintFollowDTO);
	    return resultDO;
	}
}
