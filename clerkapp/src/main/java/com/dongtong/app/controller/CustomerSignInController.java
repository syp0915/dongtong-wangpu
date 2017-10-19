package com.dongtong.app.controller;

import com.dongtong.app.ao.CustomerSignInAO;
import com.dongtong.app.utils.AuthSessionUtils;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.clerk.dto.CustomerSignInDTO;
import com.dongtong.clerk.query.CustomerSignInQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Package com.dongtong.app.controller.CustomerSignInController
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/16 9:19
 * version V1.0.0
 */
@RequestMapping(value = "/api/signIn")
@Api(tags = "CustomerSignInController", description = "签到接口")
@RestController
public class CustomerSignInController {
	@Autowired
	private CustomerSignInAO customerSignInAO;

	@ApiOperation(value = "用户签到", notes = "用户签到")
	@RequestMapping(value = "/customerSignIn/{version:.+}", method = RequestMethod.POST)
	public ResultDO<Integer> customerSignIn(
			@ApiParam(name="customerSignInDTO",value="用户签到请求参数", required=true)@RequestBody CustomerSignInDTO customerSignInDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long customerId = HttpSessionUtils.getObject(AuthSessionUtils.APP_CURRENT_USER_ID);
		customerSignInDTO.setCustomerId(customerId);
		return customerSignInAO.customerSignIn(customerSignInDTO);
	}

	@ApiOperation(value = "用户签到历史查询", notes = "用户签到历史查询")
	@RequestMapping(value = "/querySignList/{version:.+}", method = RequestMethod.POST)
	public ResultDO<Page<CustomerSignInDTO>> customerSignIn(
			@ApiParam(name="customerSignInQuery",value="用户签到历史查询请求参数", required=true)@RequestBody(required = false) CustomerSignInQuery customerSignInQuery,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		if(customerSignInQuery==null){
			customerSignInQuery = new CustomerSignInQuery();
		}
		Long customerId = HttpSessionUtils.getCurrentAppUserId();
		customerSignInQuery.setCustomerId(customerId);
		return customerSignInAO.querySignList(customerSignInQuery);
	}

}
