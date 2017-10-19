package com.dongtong.clerk.manager;

import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.dao.CustomerSignInMapper;
import com.dongtong.clerk.domain.CustomerSignIn;
import com.dongtong.clerk.dto.CustomerSignInDTO;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Package com.dongtong.clerk.manager.CustomerSignInManager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/12 9:28
 * version V1.0.0
 */
@Service
public class CustomerSignInManager {

	@Autowired
	private CustomerSignInMapper customerSignInMapper;
	/**
	 * 用户签到业务逻辑处理
	 * @param customerSignDTO
	 * @return
	 */
	public ResultDO<Integer> customerSign(CustomerSignInDTO customerSignDTO) {
		ResultDO<Integer> resultDO = new ResultDO<>();
		CustomerSignIn signIn = new CustomerSignIn();
		signIn.setCustomerId(customerSignDTO.getCustomerId());
		signIn.setAddress(customerSignDTO.getAddress());
		signIn.setLatitude(customerSignDTO.getLatitude());
		signIn.setLongitude(customerSignDTO.getLongitude());
		signIn.setCreater(customerSignDTO.getCustomerId());
		signIn.setCreateTime(new Date());
		int isSucc = customerSignInMapper.insert(signIn);
		if(isSucc==0){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.SIGN_IN_ERROR.getCode());
			resultDO.setErrMsg(ErrorConstant.SIGN_IN_ERROR.getMsg());
			return resultDO;
		}else{
			Integer signCnt = customerSignInMapper.selectSignInCount(customerSignDTO.getCustomerId());
			resultDO.setSuccess(true);
			resultDO.setData(signCnt);
			return resultDO;
		}

	}

	public Page<CustomerSignInDTO> querySignList(Page<CustomerSignInDTO> page) {
		customerSignInMapper.selectSignInByPage(page);
		page.setQuery(null);
		return page;
	}
}
