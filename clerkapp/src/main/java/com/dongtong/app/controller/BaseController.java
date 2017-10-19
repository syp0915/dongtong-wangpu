package com.dongtong.app.controller;

import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.BaseAO;
import com.dongtong.basic.dto.IndustryInfoDTO;
import com.dongtong.basic.dto.RegionInfoDTO;
import com.dongtong.basic.dto.TagInfoDTO;
import com.dongtong.basic.query.AreaQuery;
import com.dongtong.basic.query.TagQuery;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * 基础服务
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-08 09:39
 **/
@Api(tags = "BaseController",description = "基础接口")
@RestController
@RequestMapping(value = "/api/base",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BaseController {
    @Autowired
    private BaseAO baseAO;


    @UnLoginLimit
    @ApiOperation(value = "标签列表",notes = "标签列表")
    @RequestMapping(value = "/tagList/{version:.+}",method = RequestMethod.POST)
    public ResultDO<List<TagInfoDTO>> tagList(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody TagQuery reqJson,
                            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return baseAO.tagList(reqJson);
    }


    @UnLoginLimit
    @ApiOperation(value = "区域板块",notes = "区域板块")
    @RequestMapping(value ="/area/{version:.+}",method = RequestMethod.POST)
    public ResultDO<List<RegionInfoDTO>> queryArea(
            @ApiParam(name = "reqJson",value = "请求参数",required = true)@RequestBody AreaQuery reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){
        return baseAO.areaList(reqJson);
    }


    @UnLoginLimit
    @ApiOperation(value = "业态经营范围",notes = "业态经营范围")
    @RequestMapping(value = "/industryList/{version:.+}",method = RequestMethod.POST)
    public ResultDO<List<IndustryInfoDTO>> industryList(
            @ApiParam(name ="version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version")String version){
        return baseAO.industryList();
    }
}
