package com.dongtong.app.controller;

import com.dongtong.app.ao.BackLogAO;
import com.dongtong.app.model.NeedDealList;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 待办是事项列表
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 18:00
 * @since 1.0
 */
@RequestMapping(value = "/api/work")
@Api(tags = "BackLogController", description = "待办事项列表")
@RestController
public class BackLogController {
    @Resource
    private BackLogAO backLogAO;

    @ApiOperation(value = "待办事项列表", notes = "待办事项列表")
    @RequestMapping(value = "/getNeedDealList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<NeedDealList> getNeedDealList(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return backLogAO.getNeedDealList();

    }
}
