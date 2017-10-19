package com.dongtong.app.controller;

import com.dongtong.app.ao.ClerkHintAO;
import com.dongtong.app.constant.OwnerTypeEnum;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.clerk.bo.ClerkHintBO;
import com.dongtong.clerk.dto.*;
import com.dongtong.clerk.query.*;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @Package com.dongtong.app.controller.ClerkHintController
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/9 14:44
 * version V1.0.0
 */
@RequestMapping(value = "/api/clerkHint")
@Api(tags = "ClerkHintController", description = "线索接口")
@RestController
public class ClerkHintController {
	@Autowired
	private ClerkHintAO clerkHintAO;

	@ApiOperation(value = "发布线索", notes = "发布线索")
	@RequestMapping(value = "/publishHint/{version:.+}", method = RequestMethod.POST)
	public ResultDO<Long> publishHint(
			@ApiParam(name="clerkHintDTO",value="发布线索请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long issuerId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintDTO.setIssuerId(issuerId);
		ResultDO<Long> result = clerkHintAO.publishHint(clerkHintDTO);
		return result;
	}

	@ApiOperation(value = "查询线索详情", notes = "查询线索详情")
	@RequestMapping(value = "/queryHintDetail/{version:.+}", method = RequestMethod.POST)
	public ResultDO<ClerkHintDTO> queryHintDetail(
			@ApiParam(name="clerkHintQuery",value="查询线索详情请求参数", required=true)@RequestBody ClerkHintQuery clerkHintQuery,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		ResultDO<ClerkHintDTO> result = clerkHintAO.queryHintDetail(clerkHintQuery);
		return result;
	}

	@ApiOperation(value = "查询线索列表", notes = "查询线索列表")
	@RequestMapping(value = "/queryHintList/{version:.+}", method = RequestMethod.POST)
	public ResultDO<Page<ClerkHintDTO>> queryHintList(
			@ApiParam(name="clerkHintListQuery",value="查询线索列表请求参数", required=true)@RequestBody ClerkHintListQuery clerkHintListQuery,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long ownerId = HttpSessionUtils.getCurrentAppUserId();
		if(!ValidateHelper.isEmpty(clerkHintListQuery.getHintOwnType())
				&& OwnerTypeEnum.MY.getValue()==clerkHintListQuery.getHintOwnType()){
			clerkHintListQuery.setOwnerId(ownerId);
		}
		ResultDO<Page<ClerkHintDTO>> result = clerkHintAO.queryHintList(clerkHintListQuery);
		return result;
	}

	@ApiOperation(value = "商铺地图查询线索列表", notes = "商铺地图查询线索列表")
	@RequestMapping(value = "/queryClerkHintListForMap/{version:.+}", method = RequestMethod.POST)
	public ResultDO<List<ClerkHint4MapDTO>> queryClerkHintListForMap(
			@ApiParam(name="clerkHintList4MapQuery",value="商铺地图查询线索列表请求参数", required=true)@RequestBody ClerkHintList4MapQuery clerkHintList4MapQuery,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		ResultDO<List<ClerkHint4MapDTO>> result = clerkHintAO.queryClerkHintListForMap(clerkHintList4MapQuery);
		return result;
	}


	@ApiOperation(value = "收铺线索列表", notes = "获取收铺线索列表")
	@RequestMapping(value = "/getShopClueList/{version:.+}", method = RequestMethod.POST)
	public ResultDO <Page<ClerkHintBO>> getShopClueList(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		ResultDO <Page<ClerkHintBO>> result = clerkHintAO.getShopClueList(clerkHintDTO);
		return result;
	}

	@ApiOperation(value = "收铺线索详情", notes = "获取收铺线索详情")
	@RequestMapping(value = "/getShopClueInfo/{version:.+}", method = RequestMethod.POST)
	public ResultDO <ClerkHintDTO> getShopClueInfo(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		ResultDO <ClerkHintDTO> result = clerkHintAO.getShopClueInfo(clerkHintDTO);
		return result;
	}

	@ApiOperation(value = "店铺线索认领", notes = "店铺线索认领")
	@RequestMapping(value = "/shopClaim/{version:.+}", method = RequestMethod.POST)
	public ResultDO shopClaim(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) throws ParseException {
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintDTO.setOwnerId(clerkId);
		Logger.debug(this.getClass(),"clerkId="+clerkHintDTO.getOwnerId());
		ResultDO result = clerkHintAO.shopClaim(clerkHintDTO);
		return result;
	}

	@ApiOperation(value = "店铺线索修改实勘时间", notes = "店铺线索修改实勘时间")
	@RequestMapping(value = "/updateSubscribeTime/{version:.+}", method = RequestMethod.POST)
	public ResultDO updateSubscribeTime(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) throws ParseException {
		ResultDO result = clerkHintAO.updateSubscribeTime(clerkHintDTO);
		return result;
	}
	@ApiOperation(value = "店铺线索废弃", notes = "店铺线索废弃")
	@RequestMapping(value = "/updateClueStatus/{version:.+}", method = RequestMethod.POST)
	public ResultDO updateClueStatus(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long cherkId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintDTO.setModifier(cherkId);
		ResultDO result = clerkHintAO.updateClueStatus(clerkHintDTO);
		return result;
	}

	@ApiOperation(value = "交易员认领线索", notes = "交易员认领线索")
	@RequestMapping(value = "/tradeClerkClaim/{version:.+}", method = RequestMethod.POST)
	public ResultDO tradeClerkClaim(
			@ApiParam(name="clerkHintDTO",value="交易员认领请求参数", required=true)@RequestBody ClerkHintComfirmDTO clerkHintComfirmDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintComfirmDTO.setClerkId(clerkId);
		ResultDO result = clerkHintAO.tradeClerkClaim(clerkHintComfirmDTO);
		return result;
	}

	@ApiOperation(value = "拓铺员确认线索有效", notes = "拓铺员确认线索有效")
	@RequestMapping(value = "/confirmHint/{version:.+}", method = RequestMethod.POST)
	public ResultDO confirmHint(
			@ApiParam(name="clerkHintDTO",value="拓铺员确认线索有效请求参数", required=true)@RequestBody ClerkHintComfirmDTO clerkHintComfirmDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintComfirmDTO.setClerkId(clerkId);
		ResultDO result = clerkHintAO.confirmHint(clerkHintComfirmDTO);
		return result;
	}

	@ApiOperation(value = "线索位置信息修改", notes = "线索位置信息修改")
	@RequestMapping(value = "/updatePositionInfo/{version:.+}", method = RequestMethod.POST)
	public ResultDO updatePositionInfo(
			@ApiParam(name="clerkHintDTO",value="线索位置信息修改请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintDTO.setModifier(clerkId);
		ResultDO result = clerkHintAO.updatePositionInfo(clerkHintDTO);
		return result;
	}

	@ApiOperation(value = "线索客户信息修改", notes = "线索客户信息修改")
	@RequestMapping(value = "/updateCustomerInfo/{version:.+}", method = RequestMethod.POST)
	public ResultDO updateCustomerInfo(
			@ApiParam(name="clerkHintDTO",value="线索客户信息修改请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintDTO.setModifier(clerkId);
		ResultDO result = clerkHintAO.updateCustomerInfo(clerkHintDTO);
		return result;
	}

	@ApiOperation(value = "线索建筑信息修改", notes = "线索建筑信息修改")
	@RequestMapping(value = "/updateBuildingInfo/{version:.+}", method = RequestMethod.POST)
	public ResultDO updateBuildingInfo(
			@ApiParam(name="clerkHintDTO",value="线索建筑信息修改请求参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintDTO.setModifier(clerkId);
		Logger.debug(this.getClass(),"clerkId="+clerkHintDTO.getModifier());
		ResultDO result = clerkHintAO.updateBuildingInfo(clerkHintDTO);
		return result;
	}

	@ApiOperation(value = "线索经营状态修改", notes = "线索经营状态修改")
	@RequestMapping(value = "/updateShopImgInfo/{version:.+}", method = RequestMethod.POST)
	public ResultDO updateShopImgInfo(
			@ApiParam(name="clerkHintDTO",value="线索经营状态修改请求参数", required=true)@RequestBody ClerkHintImgsDTO clerkHintImgsDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		clerkHintImgsDTO.setModifier(clerkId);
		ResultDO result = clerkHintAO.updateShopImgInfo(clerkHintImgsDTO);
		return result;
	}

	@ApiOperation(value = "线索数量统计", notes = "线索数量统计")
	@RequestMapping(value = "/hintStatistics/{version:.+}", method = RequestMethod.POST)
	public ResultDO<ClerkHintStatisticsDTO> hintStatistics(@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
		Long clerkId = HttpSessionUtils.getCurrentAppUserId();
		ResultDO result = clerkHintAO.hintStatistics(clerkId);
		return result;
	}

	@ApiOperation(value = "待确认/待核准的线索列表", notes = "待确认/待核准的线索列表")
	@RequestMapping(value = "/queryHintListByStatus/{version:.+}", method = RequestMethod.POST)
	public ResultDO queryHintListByStatus(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkHintTypeQuery query,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.2.0" )@PathVariable("version") String version){
		return clerkHintAO.queryHintListByStatus(query);
	}

	@ApiOperation(value = "线索待确认/待实勘详情查询", notes = "线索待确认/待实勘详情查询")
	@RequestMapping(value = "/getHintDetailInfo/{version:.+}", method = RequestMethod.POST)
	public ResultDO getHintDetailInfo(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkHintDetailReqDTO reqDTO,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.2.0" )@PathVariable("version") String version){
		return clerkHintAO.getHintDetailInfo(reqDTO);
	}

	@ApiOperation(value = "业务员签约/约看列表v1.2", notes = "业务员签约/约看列表v1.2")
	@RequestMapping(value = "/clerkSign/{version:.+}", method = RequestMethod.POST)
	public ResultDO clerkSign(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkSignTypeQuery query,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.2.0" )@PathVariable("version") String version){
		return clerkHintAO.clerkSign(query);
	}

	@ApiOperation(value = "业务员签约/约看详情", notes = "业务员签约/约看详情")
	@RequestMapping(value = "/SignOrvisitDetail/{version:.+}", method = RequestMethod.POST)
	public ResultDO visitDetail(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody SignOrViewQuery query,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.2.0" )@PathVariable("version") String version){
		return clerkHintAO.querySignOrViewDetail(query);
	}

	@ApiOperation(value = "添加备注", notes = "添加备注")
	@RequestMapping(value = "/addRemark/{version:.+}", method = RequestMethod.POST)
	public ResultDO addRemark(
			@ApiParam(name="clerkHintDTO",value="目录请求参数", required=true)@RequestBody ClerkRemarkDTO query,
			@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.2.0" )@PathVariable("version") String version){
		return clerkHintAO.addRemark(query);
	}

}
