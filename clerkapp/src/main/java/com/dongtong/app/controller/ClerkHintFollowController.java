package com.dongtong.app.controller;

import com.dongtong.app.ao.ClerkHintFollowAO;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.clerk.dto.ClerkHintDTO;
import com.dongtong.clerk.dto.ClerkHintFollowDTO;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package com.dongtong.app.controller.ClerkHintFollowController
 * @Description: 线索跟进controller
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/14 13:27
 * version V1.0.0
 */
@RequestMapping(value = "/api/clerkHintFollow")
@Api(tags = "ClerkHintFollowController", description = "线索跟进接口")
@RestController
public class ClerkHintFollowController {

	@Autowired
	private ClerkHintFollowAO clerkHintFollowAO;

	@ApiOperation(value = "线索跟进删除", notes = "线索跟进删除")
	@RequestMapping(value = "/delete/{version:.+}", method = RequestMethod.POST)
	public ResultDO<Long> delete(
			@ApiParam(name="clerkHintFollowDTO",value="线索跟进删除请求参数", required=true)@RequestBody ClerkHintFollowDTO clerkHintFollowDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long userId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintFollowDTO.setClerkId(userId);
		Logger.debug(this.getClass(),"clerkId="+clerkHintFollowDTO.getClerkId());
		ResultDO<Long> result = clerkHintFollowAO.delete(clerkHintFollowDTO);
		return result;
	}

	@ApiOperation(value = "线索跟进添加", notes = "线索跟进添加")
	@RequestMapping(value = "/add/{version:.+}", method = RequestMethod.POST)
	public ResultDO<Long> add(
			@ApiParam(name="clerkHintFollowDTO",value="线索跟进添加请求参数", required=true)@RequestBody ClerkHintFollowDTO clerkHintFollowDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long userId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintFollowDTO.setClerkId(userId);
		ResultDO<Long> result = clerkHintFollowAO.add(clerkHintFollowDTO);
		return result;
	}

	@ApiOperation(value = "线索跟进列表查询", notes = "线索跟进列表查询")
	@RequestMapping(value = "/queryListByHintId/{version:.+}", method = RequestMethod.POST)
	public ResultDO<List<ClerkHintFollowDTO>> queryListByHintId(
			@ApiParam(name="clerkHintFollowDTO",value="线索跟进列表查询请求参数", required=true)@RequestBody ClerkHintFollowDTO clerkHintFollowDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long userId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintFollowDTO.setClerkId(userId);
		ResultDO<List<ClerkHintFollowDTO>> result = clerkHintFollowAO.queryListByHintId(clerkHintFollowDTO);
		return result;
	}
}
