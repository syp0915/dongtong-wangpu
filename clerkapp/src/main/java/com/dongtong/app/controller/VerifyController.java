package com.dongtong.app.controller;


import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.LoginAO;
import com.dongtong.app.ao.VerifyAO;
import com.dongtong.basic.dto.resp.PicVerifyRespDTO;
import com.dongtong.basic.dto.resp.SmsVerifyRespDTO;
import com.dongtong.basic.query.PicVerifyReqQuery;
import com.dongtong.basic.query.SmsVerifyReqQuery;
import com.dongtong.clerk.dto.resp.LoginRespDTO;
import com.dongtong.clerk.query.LoginReqQuery;
import com.dongtong.customer.query.UpdateDeviceIdQuery;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/10 下午5:42.
 */
@Api(tags = "VerifyController", description = "发送验证接口")
@RestController
@RequestMapping(value = "/api/clerk/verify",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VerifyController {

    @Autowired(required = false)
    private VerifyAO verifyAO;

    @Autowired(required = false)
    private LoginAO loginAO;

    @UnLoginLimit
    @ApiOperation(value = "业务员登录", notes = "业务员登录")
    @RequestMapping(value = "/loginVerify/{version:.+}", method = RequestMethod.POST)
    public ResultDO<LoginRespDTO> loginVerify(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody LoginReqQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return loginAO.customerLogin(query);
    }

    @UnLoginLimit
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @RequestMapping(value = "/sendSmsVerifyCode/{version:.+}", method = RequestMethod.POST)
    public ResultDO<SmsVerifyRespDTO> sendSmsVerifyCode(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody SmsVerifyReqQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return verifyAO.sendSmsVerifyCode(query);
    }

    @UnLoginLimit
    @ApiOperation(value = "获取图片验证码", notes = "获取图片验证码")
    @RequestMapping(value = "/getPicVerifyCode/{version:.+}", method = RequestMethod.POST)
    public ResultDO<PicVerifyRespDTO> getPicVerifyCode(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody PicVerifyReqQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return verifyAO.getVerifyPic(query);
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @RequestMapping(value = "/logoutVerify/{version:.+}", method = RequestMethod.POST)
    public ResultDO logoutVerify(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return loginAO.clerkLogout();
    }

    @ApiOperation(value = "更新设备id", notes = "更新设备id")
    @RequestMapping(value = "/updateDeviceId/{version:.+}", method = RequestMethod.POST)
    public ResultDO updateDeviceId(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody UpdateDeviceIdQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return loginAO.updateDeviceId(query);
    }
}
