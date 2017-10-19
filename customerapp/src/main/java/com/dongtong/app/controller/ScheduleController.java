package com.dongtong.app.controller;

import com.dongtong.app.ao.ScheduleAO;
import com.dongtong.customer.dto.resp.CalendarScheduleRespDTO;
import com.dongtong.customer.dto.resp.ScheduleDetailRespDTO;
import com.dongtong.customer.dto.resp.ScheduleListRespDTO;
import com.dongtong.customer.query.*;
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
 * @date 2017/5/10 下午6:03.
 */
@Api(tags = "ScheduleController", description = "日程")
@RestController
@RequestMapping(value = "/api/user/schedule",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ScheduleController {

    @Autowired(required = false)
    private ScheduleAO scheduleAO;

    @ApiOperation(value = "日程/历史列表", notes = "日程/历史列表")
    @RequestMapping(value = "/list/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ScheduleListRespDTO> getScheduleListByUserId(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody ScheduleListReqQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return scheduleAO.getScheduleListByUserId(query);
    }

    @ApiOperation(value = "日程详情", notes = "日程详情")
    @RequestMapping(value = "/detail/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ScheduleDetailRespDTO> getScheduleInfoById(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody ScheduleDetailReqQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return scheduleAO.getScheduleInfoById(query);
    }

    @ApiOperation(value = "日历-日程列表", notes = "日历-日程列表")
    @RequestMapping(value = "/calendar/list/{version:.+}", method = RequestMethod.POST)
    public ResultDO<CalendarScheduleRespDTO> getCalendarScheduleList(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody CalendarScheduleReqQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return scheduleAO.getCalendarScheduleList(query);
    }

    @ApiOperation(value = "日程撤销", notes = "日程撤销")
    @RequestMapping(value = "/revoke/{version:.+}", method = RequestMethod.POST)
    public ResultDO revokeSchedule(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody ScheduleRevokeQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return scheduleAO.revokeSchedule(query);
    }

    @ApiOperation(value = "确认日程服务", notes = "确认日程服务")
    @RequestMapping(value = "/ensure/{version:.+}", method = RequestMethod.POST)
    public ResultDO ensureSchedule(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody ScheduleEnsureQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return scheduleAO.ensureSchedule(query);
    }
}
