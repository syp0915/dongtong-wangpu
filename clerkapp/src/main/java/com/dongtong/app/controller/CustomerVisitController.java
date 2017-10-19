package com.dongtong.app.controller;

import com.dongtong.app.ao.CustomerVisitAO;
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.VisitShopRespDTO;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 约看
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 9:06
 * @since 1.0
 */
@RestController
@RequestMapping(value = "api")
@Api(tags = "CustomerVisitController", description = "约看接口")
public class CustomerVisitController {
    @Resource
    private CustomerVisitAO customerVisitAO;

    @ApiOperation(value = "约看列表", notes = "约看列表")
    @RequestMapping(value = "/visit/getVisitList/{version:.+}", method = RequestMethod.POST)
    public ResultDO <Page<VisitShopRespDTO>>  getVisitList(
            @ApiParam(name="visitShopReqDTO",value="约看列表请求参数", required=true)@RequestBody VisitShopReqDTO visitShopReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return customerVisitAO.getVisitList(visitShopReqDTO);
    }

    @ApiOperation(value = "约看详情", notes = "约看详情")
    @RequestMapping(value = "/visit/getVisitInfo/{version:.+}", method = RequestMethod.POST)
    public ResultDO <VisitShopRespDTO>  getVisitInfo(
            @ApiParam(name="visitShopReqDTO",value="约看详情请求参数", required=true)@RequestBody VisitShopReqDTO visitShopReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return customerVisitAO.getVisitInfo(visitShopReqDTO);
    }

    @ApiOperation(value = "修改约看时间", notes = "修改约看时间")
    @RequestMapping(value = "/visit/updateVisitTime/{version:.+}", method = RequestMethod.POST)
    public ResultDO   updateVisitTime(
            @ApiParam(name="visitShopReqDTO",value="修改约看时间请求参数", required=true)@RequestBody VisitShopReqDTO visitShopReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) throws ParseException {
        return customerVisitAO.updateVisitTime(visitShopReqDTO);
    }

    @ApiOperation(value = "约看取消", notes = "约看取消")
    @RequestMapping(value = "/visit/updateVisitStatus/{version:.+}", method = RequestMethod.POST)
    public ResultDO   updateVisitStatus(
            @ApiParam(name="visitShopReqDTO",value="约看取消请求参数", required=true)@RequestBody VisitShopReqDTO visitShopReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return customerVisitAO.updateVisitStatus(visitShopReqDTO);
    }
}
