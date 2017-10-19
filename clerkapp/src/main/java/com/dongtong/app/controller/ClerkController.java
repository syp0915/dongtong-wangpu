package com.dongtong.app.controller;

import com.dongtong.app.ao.ClerkAO;
import com.dongtong.clerk.dto.ClerkDetailDTO;
import com.dongtong.clerk.dto.UpdateClerkDetailDTO;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Package com.dongtong.app.controller.ClerkController
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/16 10:26
 * version V1.0.0
 */
@Api(tags = "ClerkController", description = "业务员基本信息接口")
@RestController
@RequestMapping(value = "/api/clerk",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClerkController {
    @Autowired
    private ClerkAO clerkAO;

    /**
     * 我的--个人中心详情接口
     * @param
     * @return
     */
    @ApiOperation(value = "我的--个人中心详情接口", notes = "我的--个人中心详情接口")
    @RequestMapping(value = "/getClerkDetailById/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ClerkDetailDTO> getClerkDetailById(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return clerkAO.getClerkDetailById();
    }

    /**
     * 我的--基本信息添加接口
     * @param
     * @return
     */
    @ApiOperation(value = "我的--基本信息添加接口", notes = "我的--基本信息添加接口")
    @RequestMapping(value = "/updateClerkDetail/{version:.+}", method = RequestMethod.POST)
    public ResultDO updateClerkDetail(
            @ApiParam(name="updateClerkDetailDTO",value="目录请求参数", required=true)@RequestBody UpdateClerkDetailDTO updateClerkDetailDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return clerkAO.updateClerkDetail(updateClerkDetailDTO);
    }

    /**
     * 是否有新通知接口
     * @return
     */
    @ApiOperation(value = "是否有新通知接口", notes = "是否有新通知接口")
    @RequestMapping(value = "/isNewNotification/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> isNewNotification(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return clerkAO.isNewNotification();
    }

    /**
     * 修改个人信息
     * @return
     */
    @ApiOperation(value = "个人信息修改", notes = "个人信息修改")
    @RequestMapping(value = "/updateClerkInfo/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> updateClerkInfo(
            @ApiParam(name="updateClerkDetailDTO",value="目录请求参数", required=true)@RequestBody UpdateClerkDetailDTO updateClerkDetailDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return clerkAO.updateClerkInfo(updateClerkDetailDTO);
    }
}
