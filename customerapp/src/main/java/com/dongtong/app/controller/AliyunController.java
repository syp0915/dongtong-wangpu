package com.dongtong.app.controller;

import com.dongtong.app.ao.AliyunAO;
import com.dongtong.basic.dto.resp.AliyunSTSRespDTO;
import com.dongtong.basic.query.AliyunSTSQuery;
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
@Api(tags = "AliyunController", description = "阿里云oss")
@RestController
@RequestMapping(value = "/api/user/aliyun",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AliyunController {

    @Autowired(required = false)
    private AliyunAO aliyunAO;

    @ApiOperation(value = "获取阿里云oss临时访问令牌信息", notes = "获取阿里云oss临时访问令牌信息")
    @RequestMapping(value = "/getTmpAccessInfo/{version:.+}", method = RequestMethod.POST)
    public ResultDO<AliyunSTSRespDTO> getTmpAccessInfo(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody AliyunSTSQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return aliyunAO.getTmpAccessInfo(query);
    }
}
