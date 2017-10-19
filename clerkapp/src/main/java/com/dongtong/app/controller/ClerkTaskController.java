package com.dongtong.app.controller;

import com.dongtong.app.ao.ClerkTaskAO;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.clerk.dto.ClerkTaskDTO;
import com.dongtong.clerk.query.ClerkTaskQuery;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Package com.dongtong.app.controller.ClerkTaskController
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/16 10:16
 * version V1.0.0
 */
@RequestMapping(value = "/api/clerkTask")
@Api(tags = "ClerkTaskController", description = "商铺代办事项接口")
@RestController
public class ClerkTaskController {

	@Autowired
	private ClerkTaskAO clerkTaskAO;

	@ApiOperation(value = "商铺代办任务列表查询", notes = "商铺代办任务列表查询")
	@RequestMapping(value = "/queryClerkTaskList/{version:.+}", method = RequestMethod.POST)
	public ResultDO<ClerkTaskDTO> customerSignIn(
			@ApiParam(name="clerkTaskQuery",value="商铺代办任务列表查询请求参数", required=true)@RequestBody ClerkTaskQuery clerkTaskQuery,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		clerkTaskQuery.setClerkId(clerkId);
		return clerkTaskAO.queryClerkTaskList(clerkTaskQuery);
	}
}
