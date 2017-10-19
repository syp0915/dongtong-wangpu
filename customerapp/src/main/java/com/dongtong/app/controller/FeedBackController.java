package com.dongtong.app.controller;

import com.dongtong.app.ao.FeedBackAO;
import com.dongtong.customer.dto.req.FeedBackReqDTO;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunyaping
 * @Package com.dongtong.app.controller
 * @Description:反馈意见
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-15 9:48
 * version V1.0.0
 **/
@Api(tags = "FeedBackController", description = "意见反馈接口")
@RestController
@RequestMapping(value = "/api/feedBack",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FeedBackController {

    @Autowired
    private FeedBackAO feedBackAO;

    @ApiOperation(value = "意见反馈", notes = "意见反馈")
    @RequestMapping(value = "/addFeedBack/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Long> addFeedBack(
            @ApiParam(name="dto",value="筛选参数", required=true)@RequestBody FeedBackReqDTO feedBackReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return feedBackAO.addFeedBack(feedBackReqDTO);

    }
}
