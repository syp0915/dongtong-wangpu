package com.dongtong.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.PerpetualAO;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.dongtong.app.controller.PerpetualController
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/10 14:53
 * version V1.0.0
 */
@Api(tags = "PerpetualController",description = "农历接口")
@RestController
@RequestMapping(value = "/api/nongli",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PerpetualController {

    @Autowired
    private PerpetualAO perpetualAO;

    /**
     * @Description: 获取老黄历
     * @Title estimate
     * @Author  wliao
     * @Date 2017/1/4 14:16
     * @param version
     * @return String
     * @throws
     */
    @UnLoginLimit
    @ApiOperation(value = "获取农历",notes = "获取农历")
    @RequestMapping(value = "/wnl/{version:.+}",method = RequestMethod.POST)
    public ResultDO<JSONObject> getWnl(@ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return perpetualAO.selectWnlJsonStr();
    }


}
