package com.dongtong.app.controller;

import com.dongtong.app.ao.CustomerSignAO;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.resp.SignRespDTO;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 签约
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 9:54
 * @since 1.0
 */
@RestController
@RequestMapping(value = "api")
@Api(tags = "CustomerSignController", description = "签约接口")
public class CustomerSignController {
    @Resource
    private CustomerSignAO customerSignAO;
    @ApiOperation(value = "签约列表", notes = "签约列表")
    @RequestMapping(value = "/sign/getSignList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<SignRespDTO>> getSignList(
            @ApiParam(name="signReqDTO",value="签约列表请求参数", required=true)@RequestBody SignReqDTO signReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return customerSignAO.getSignList(signReqDTO);
    }

    @ApiOperation(value = "签约列表", notes = "签约列表")
    @RequestMapping(value = "/sign/getSignInfo/{version:.+}", method = RequestMethod.POST)
    public ResultDO <SignRespDTO>getSignInfo(
            @ApiParam(name="signReqDTO",value="签约列表请求参数", required=true)@RequestBody SignReqDTO signReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return customerSignAO.getSignInfo(signReqDTO);
    }
    @ApiOperation(value = "修改时间", notes = "修改时间")
    @RequestMapping(value = "/sign/updateSignTime/{version:.+}", method = RequestMethod.POST)
    public ResultDO updateSignTime(
            @ApiParam(name="signReqDTO",value="修改时间请求参数", required=true)@RequestBody SignReqDTO signReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) throws ParseException {
        return customerSignAO.updateSignTime(signReqDTO);
    }
    @ApiOperation(value = "签约取消", notes = "签约取消")
    @RequestMapping(value = "/sign/updateSignStatus/{version:.+}", method = RequestMethod.POST)
    public ResultDO updateSignStatus(
            @ApiParam(name="signReqDTO",value="签约取消请求参数", required=true)@RequestBody SignReqDTO signReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return customerSignAO.updateSignStatus(signReqDTO);
    }
}
