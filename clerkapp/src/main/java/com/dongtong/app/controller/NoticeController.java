package com.dongtong.app.controller;

import com.dongtong.app.ao.NoticeAO;
import com.dongtong.basic.dto.req.BaseNoticeReqDTO;
import com.dongtong.basic.dto.resp.NotifyListRespDTO;
import com.dongtong.basic.dto.resp.NotifyTypeListRespDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.query.NoticeQuery;
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
 * @Description :消息通知
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-12 18:06
 * version V1.0.0
 **/
@Api(tags = "NoticeController", description = "消息通知接口")
@RestController
@RequestMapping(value = "/api/notice",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NoticeController {

    @Autowired
    private NoticeAO noticeAO;

    @ApiOperation(value = "是否有未读消息", notes = "是否有未读消息")
    @RequestMapping(value = "/isUnRead/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> IsUnRead(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return noticeAO.IsUnRead(ReceiveType.CLERK.getValue());

    }

    @ApiOperation(value = "消息通知列表", notes = "消息通知列表")
    @RequestMapping(value = "/getNoticeList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<NotifyTypeListRespDTO> getNoticeList(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return noticeAO.getNoticeList();

    }

    @ApiOperation(value = "消息通知分类列表", notes = "分类列表")
    @RequestMapping(value = "/getNoticeByTypeList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<NotifyListRespDTO> getNoticeByTypeList(
            @ApiParam(name="dto",value="筛选参数", required=true)@RequestBody NoticeQuery noticeQuery,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return noticeAO.getNoticeByTypeList(noticeQuery);

    }
}
