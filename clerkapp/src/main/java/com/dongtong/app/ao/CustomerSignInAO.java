package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.clerk.dto.CustomerSignInDTO;
import com.dongtong.clerk.query.CustomerSignInQuery;
import com.dongtong.clerk.service.CustomerSignInService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.app.ao.CustomerSignInAO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/16 9:28
 * version V1.0.0
 */
@Service
public class CustomerSignInAO {
	@Autowired
	private CustomerSignInService customerSignInService;

	/**
	 * 用户签到
	 * @param customerSignInDTO
	 * @return
	 */
	public ResultDO<Integer> customerSignIn(CustomerSignInDTO customerSignInDTO){
		ResultDO checkDO = checkCustomerSignIn(customerSignInDTO);
		if(!checkDO.isSuccess()){
			return checkDO;
		}

		return customerSignInService.customerSignIn(customerSignInDTO);
	}

	/**
	 * 用户签到历史查询
	 * @param signInQuery
	 * @return
	 */
	public ResultDO<Page<CustomerSignInDTO>> querySignList(CustomerSignInQuery signInQuery){
		ResultDO checkDO = checkQuerySignList(signInQuery);
		if(!checkDO.isSuccess()){
			return checkDO;
		}

		return customerSignInService.querySignList(signInQuery);
	}

	private ResultDO checkQuerySignList(CustomerSignInQuery signInQuery) {
		ResultDO check = new ResultDO();

		if(ValidateHelper.isEmpty(signInQuery.getCustomerId())){
			check.setSuccess(false);
			check.setErrCode(ErrorConstant.NULL_CUSTOMER_ID.getCode());
			check.setErrMsg(ErrorConstant.NULL_CUSTOMER_ID.getMsg());
			return check;
		}
		check.setSuccess(true);
		return check;
	}

	private ResultDO checkCustomerSignIn(CustomerSignInDTO customerSignInDTO) {
		ResultDO check = new ResultDO();

		if(ValidateHelper.isEmpty(customerSignInDTO.getCustomerId())){
			check.setSuccess(false);
			check.setErrCode(ErrorConstant.NULL_CUSTOMER_ID.getCode());
			check.setErrMsg(ErrorConstant.NULL_CUSTOMER_ID.getMsg());
			return check;
		}
		if(ValidateHelper.isEmpty(customerSignInDTO.getAddress())){
			check.setSuccess(false);
			check.setErrCode(ErrorConstant.NULL_ADRESS.getCode());
			check.setErrMsg(ErrorConstant.NULL_ADRESS.getMsg());
			return check;
		}
		if(ValidateHelper.isEmpty(customerSignInDTO.getLatitude())
				||ValidateHelper.isEmpty(customerSignInDTO.getLongitude())){
			check.setSuccess(false);
			check.setErrCode(ErrorConstant.NULL_LON_OR_LAT.getCode());
			check.setErrMsg(ErrorConstant.NULL_LON_OR_LAT.getMsg());
			return check;
		}

		check.setSuccess(true);
		return check;
	}


}
