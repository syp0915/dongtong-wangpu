package com.dongtong.app.controller;

import com.dongtong.app.ao.ScheduleAO;
import com.dongtong.customer.dto.ScheduleTypeDTO;
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
 * @author zhoumin
 * @version V1.0
 * @date 2017/8/11 下午3:03.
 */
@Api(tags = "ScheduleController", description = "日程")
@RestController
@RequestMapping(value = "/api/schedule",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ScheduleController {

    @Autowired(required = false)
    private ScheduleAO scheduleAO;

    @ApiOperation(value = "设置日程", notes = "设置日程")
    @RequestMapping(value = "/addScheduleByType/{version:.+}", method = RequestMethod.POST)
    public ResultDO<String> addScheduleByType(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody ScheduleTypeDTO scheduleTypeDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return scheduleAO.addScheduleByType(scheduleTypeDTO);
    }
}
