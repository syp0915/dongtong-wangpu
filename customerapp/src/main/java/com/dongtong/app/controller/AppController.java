package com.dongtong.app.controller;

import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.AppAO;
import com.dongtong.basic.dto.resp.AppUpdateRespDTO;
import com.dongtong.basic.query.AppUpdateQuery;
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
 * @date 2017/5/25 下午6:03.
 */
@Api(tags = "AppController", description = "用户端App检查更新")
@RestController
@RequestMapping(value = "/api/user/app",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppController {

    @Autowired(required = false)
    private AppAO appAO;

    @UnLoginLimit
    @ApiOperation(value = "用户端App检查更新", notes = "用户端App检查更新")
    @RequestMapping(value = "/checkUpdate/{version:.+}", method = RequestMethod.POST)
    public ResultDO<AppUpdateRespDTO> checkUpdate(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody AppUpdateQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return appAO.queryAppUpdate(query);
    }
}
