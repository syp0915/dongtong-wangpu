package com.dongtong.clerk.service;

import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.dto.CustomerSignInDTO;
import com.dongtong.clerk.manager.CustomerSignInManager;
import com.dongtong.clerk.query.CustomerSignInQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.clerk.service.CustomerSignServiceImpl
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/12 9:21
 * version V1.0.0
 */
@Service
public class CustomerSignInServiceImpl implements CustomerSignInService {

	@Autowired
	private CustomerSignInManager customerSignInManager;

	@Override
	public ResultDO<Integer> customerSignIn(CustomerSignInDTO customerSignDTO) {
		ResultDO checkDO = checkSignIn(customerSignDTO);
		if(!checkDO.isSuccess()){
			return checkDO;
		}
		ResultDO<Integer> resultDO = customerSignInManager.customerSign(customerSignDTO);
		return resultDO;
	}

	@Override
	public ResultDO<Page<CustomerSignInDTO>> querySignList(CustomerSignInQuery signQuery) {
		ResultDO checkDO = checkSignQuery(signQuery);
		if(!checkDO.isSuccess()){
			return checkDO;
		}

		Page<CustomerSignInDTO> page = new Page<>(signQuery.getPageNumber(), signQuery.getPageSize());
		ResultDO resultDO = new ResultDO();
		try {
			page.setQuery(signQuery);
			resultDO.setData(customerSignInManager.querySignList(page));
			resultDO.setSuccess(true);
		}catch (Exception e){
			Logger.error(e, "查询签到历史失败",e);
			resultDO.setErrCode(ErrorConstant.SYSTEM_ERROR.getCode());
			resultDO.setErrMsg(ErrorConstant.SYSTEM_ERROR.getMsg());
		}
		return resultDO;
	}

	private ResultDO checkSignQuery(CustomerSignInQuery signQuery) {
		ResultDO check = new ResultDO();

		if(ValidateHelper.isEmpty(signQuery.getCustomerId())){
			check.setSuccess(false);
			check.setErrCode(ErrorConstant.NULL_CUSTOMER_ID.getCode());
			check.setErrMsg(ErrorConstant.NULL_CUSTOMER_ID.getMsg());
			return check;
		}
		check.setSuccess(true);
		return check;
	}

	private ResultDO checkSignIn(CustomerSignInDTO customerSignDTO) {
		ResultDO check = new ResultDO();

		if(ValidateHelper.isEmpty(customerSignDTO.getCustomerId())){
			check.setSuccess(false);
			check.setErrCode(ErrorConstant.NULL_CUSTOMER_ID.getCode());
			check.setErrMsg(ErrorConstant.NULL_CUSTOMER_ID.getMsg());
			return check;
		}
		if(ValidateHelper.isEmpty(customerSignDTO.getAddress())){
			check.setSuccess(false);
			check.setErrCode(ErrorConstant.NULL_ADRESS.getCode());
			check.setErrMsg(ErrorConstant.NULL_ADRESS.getMsg());
			return check;
		}
		check.setSuccess(true);
		return check;
	}
}
