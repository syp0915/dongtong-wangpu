package com.dongtong.app.controller;

import com.dongtong.app.ao.ClerkAgreementAO;
import com.dongtong.app.exception.AppWebException;
import com.dongtong.clerk.bo.ClerkAgreementBO;
import com.dongtong.clerk.dto.req.ClerkAgreementDTO;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 合同
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 10:12
 * @since 1.0
 */
@RestController
@RequestMapping(value = "api")
@Slf4j
@Api(tags = "ClerkAgreementController", description = "合同接口")
public class ClerkAgreementController {
    @Resource
    private ClerkAgreementAO clerkAgreementAO;
    @ApiOperation(value = "录入合同", notes = "录入合同")
    @RequestMapping(value = "/contract/insertContract/{version:.+}", method = RequestMethod.POST)
    public ResultDO insertContract(
            @ApiParam(name="clerkAgreementDTO",value="录入合同请求参数", required=true)@RequestBody ClerkAgreementDTO clerkAgreementDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) throws ParseException, AppWebException {
        log.info("clerkAgreementDTO:{}",clerkAgreementDTO.toString());
        return clerkAgreementAO.insertContract(clerkAgreementDTO);
    }
    @ApiOperation(value = "合同详情", notes = "合同详情")
    @RequestMapping(value = "/contract/getAgreementInfo/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ClerkAgreementBO> getAgreementInfo(
            @ApiParam(name="clerkAgreementDTO",value="录入合同请求参数", required=true)@RequestBody ClerkAgreementDTO clerkAgreementDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) throws ParseException {
        log.info("clerkAgreementDTO:{}",clerkAgreementDTO.toString());
        return clerkAgreementAO.getAgreementInfo(clerkAgreementDTO);
    }
}
