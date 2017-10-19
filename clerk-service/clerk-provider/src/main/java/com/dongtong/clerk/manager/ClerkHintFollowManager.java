package com.dongtong.clerk.manager;

import com.dongtong.clerk.constant.ClerkHintStatusEnum;
import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.dao.ClerkHintFollowMapper;
import com.dongtong.clerk.dao.ClerkHintMapper;
import com.dongtong.clerk.dao.ClerkMapper;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.domain.ClerkHintFollow;
import com.dongtong.clerk.dto.ClerkHintFollowDTO;
import com.dongtong.clerk.enums.ClerkRoleType;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Package com.dongtong.clerk.manager.ClerkHintFollowManager
 * @Description: 线索跟进
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/2 17:59
 * version V1.0.0
 */
@Service
public class ClerkHintFollowManager {

	@Autowired
	private ClerkHintFollowMapper clerkHintFollowMapper;

	@Autowired
	private ClerkMapper clerkMapper;

	@Autowired
	private ClerkHintMapper clerkHintMapper;

	/**
	 * 查询线索跟进列表
	 * @param clerkHintFollowDTO
	 * @return
	 */
	public ResultDO<List<ClerkHintFollowDTO>> queryListByHintId(ClerkHintFollowDTO clerkHintFollowDTO) {
		ResultDO<List<ClerkHintFollowDTO>> resultDO = new ResultDO<List<ClerkHintFollowDTO>>();

		List<ClerkHintFollowDTO> followList = clerkHintFollowMapper.selectListByHintId(clerkHintFollowDTO.getHintId());
		resultDO.setData(followList);
		resultDO.setSuccess(true);
		return resultDO;
	}

	public ResultDO<Long> addClerkHintFollow(ClerkHintFollowDTO clerkHintFollowDTO) {
		ResultDO<Long> resultDO = new ResultDO<>();
		Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintFollowDTO.getClerkId());
		if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
			resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
			return resultDO;
		}

		ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintFollowDTO.getHintId());

		if(ValidateHelper.isEmpty(clerkHint)){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
			resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
			return resultDO;
		}

		//线索状态校验（交易员待核准 或者拓铺员带确认）
		if(!(clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue()
				|| clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITING_CONFIRM.getValue())){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
			resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
			return resultDO;
		}

		//交易员或拓铺员角色校验（交易员 ,且线索为该交易员认领 或者拓铺员，且线索拥有者为拓铺员）
		if(ValidateHelper.isEmpty(clerk.getRoleType())||ValidateHelper.isEmpty(clerkHint.getOwnerId())
				||!((clerk.getRoleType().intValue()== ClerkRoleType.DEAL_CLERK.getValue()
				||clerk.getRoleType().intValue() == ClerkRoleType.EXPAND_CLERK.getValue())
				&& clerk.getId().longValue()==clerkHint.getOwnerId().longValue())){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
			resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
			return resultDO;
		}

		//拓铺员并且为状态为待确认,或者交易员并且状态为待实勘才可添加
		ClerkHintFollow clerkHintFollow = convertHintFollow(clerkHintFollowDTO);

		int result = clerkHintFollowMapper.insertSelective(clerkHintFollow);
		if(result==1){
			resultDO.setSuccess(true);
			resultDO.setErrMsg("添加成功");
		}else {
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.UPDATE_DATA_FAIL.getCode());
			resultDO.setErrMsg(ErrorConstant.UPDATE_DATA_FAIL.getMsg());
			return resultDO;
		}

		return resultDO;
	}

	private ClerkHintFollow convertHintFollow(ClerkHintFollowDTO clerkHintFollowDTO) {
		ClerkHintFollow clerkHintFollow = new ClerkHintFollow();
		clerkHintFollow.setClerkId(clerkHintFollowDTO.getClerkId());
		clerkHintFollow.setHintId(clerkHintFollowDTO.getHintId());
		clerkHintFollow.setContent(clerkHintFollowDTO.getContent());
		clerkHintFollow.setCreater(clerkHintFollowDTO.getClerkId());
		clerkHintFollow.setCreateTime(new Date());
		clerkHintFollow.setVersion(0L);
		return clerkHintFollow;
	}

	public ResultDO<Boolean> deleteClerkHintFollow(ClerkHintFollowDTO clerkHintFollowDTO) {
		ResultDO<Boolean> resultDO = new ResultDO<>();
		Logger.debug(this.getClass(),"clerkId="+clerkHintFollowDTO.getClerkId());
		Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintFollowDTO.getClerkId());
		if(ValidateHelper.isEmpty(clerk)){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
			resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
			return resultDO;
		}

		ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintFollowDTO.getHintId());

		if(ValidateHelper.isEmpty(clerkHint)){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
			resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
			return resultDO;
		}

		if((clerk.getRoleType().intValue()==ClerkRoleType.EXPAND_CLERK.getValue()
				&& clerkHint.getStatus().intValue()== ClerkHintStatusEnum.WAITING_CONFIRM.getValue()
				&& clerkHint.getOwnerId().intValue()==clerk.getId().intValue())
				||(clerk.getRoleType().intValue()==ClerkRoleType.DEAL_CLERK.getValue()
				&& clerkHint.getStatus().intValue()== ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue()
				&& clerkHint.getOwnerId().intValue()==clerk.getId().intValue())){
			int result = clerkHintFollowMapper.deleteByPrimaryKey(clerkHintFollowDTO.getId());
			if(result==1){
				resultDO.setSuccess(true);
				resultDO.setErrMsg("删除成功");
			}else{
				resultDO.setSuccess(false);
				resultDO.setErrCode(ErrorConstant.DELETE_DATA_FAIL.getCode());
				resultDO.setErrMsg(ErrorConstant.DELETE_DATA_FAIL.getMsg());
			}
		}else{
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
			resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
		}
		return resultDO;
	}
}
