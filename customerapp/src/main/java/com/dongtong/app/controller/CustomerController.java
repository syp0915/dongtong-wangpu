package com.dongtong.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.CustomerAO;
import com.dongtong.customer.dto.*;
import com.dongtong.customer.dto.req.AttentionReqDTO;
import com.dongtong.customer.dto.req.UpdatePhoneVerifyReqDTO;
import com.dongtong.customer.dto.resp.IndexStatisticsRespDTO;
import com.dongtong.customer.dto.resp.StatisticDTO;
import com.dongtong.customer.query.BaseQuery;
import com.dongtong.customer.query.CustomerServiceQuery;
import com.dongtong.customer.query.ServiceListListQuery;
import com.dongtong.shop.dto.ShopVisitCustomerDTO;
import com.dongtong.shop.query.ShopVisitQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//import com.dongtong.shop.query.ShopVisitQuery;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-11 14:55
 **/
@Api(tags = "CustomerController", description = "用户接口")
@RestController
@RequestMapping(value = "/api/customer",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerController {

    @Autowired
    private CustomerAO customerAO;

    @ApiOperation(value = "申请贷款",notes = "申请贷款")
    @RequestMapping(value = "/applyLoad/{version:.+}",method = RequestMethod.POST)
    public ResultDO<String> applyLoad(
            @ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody LoadDTO reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0") @PathVariable("version")String version){

        return customerAO.applyLoad(reqJson);

    }


    @ApiOperation(value = "贷款申请列表",notes = "贷款申请列表")
    @RequestMapping(value = "/loadList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<LoadDTO>> loadList(
            @ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody BaseQuery reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.loanList(reqJson);

    }


    @ApiOperation(value = "签约租铺申请",notes = "签约租铺申请")
    @RequestMapping(value = "/applySign/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Long> applySign(
            @ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody CustomerSignDTO reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.applySign(reqJson);

    }

    /**
     * @description
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/8/9 0009 15:36
     * @param
     * @return
     *
     * v1.2修改
     *   --新增查询关注内容逻辑
     *   --新增查询我的服务逻辑
     *   --手机号码去掉脱敏
     *   --新增昵称和签名字段返回
     */
    @ApiOperation(value = "用户基本信息",notes = "用户基本信息")
    @RequestMapping(value = "/queryCustomerInfo/{version:.+}", method = RequestMethod.POST)
    public ResultDO<CustomerInfoDTO> queryCustomerInfo(
           // @ApiParam(name = "reqJson",value = "请求参数") String reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.queryCustomerInfo();
    }

    @ApiOperation(value = "用户头像修改",notes = "用户头像修改")
    @RequestMapping(value = "/updateHeadPortrait/{version:.+}", method = RequestMethod.POST)
    public ResultDO<String> updateHeadPortrait(
            @ApiParam(name = "reqJson",value = "请求参数") @RequestBody JSONObject reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.updateHeadPortrait(reqJson.getString("headPortrait"));

    }

    /**
     * @description
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/8/9 0009 15:35
     * @param reqJson
     * @return ResultDO<String>
     *
     * v1.2修改
     *   --修改入参
     *   --修改关注板块和关注行业可以为多选
     */
    @ApiOperation(value = "用户关注商铺设置",notes = "用户关注商铺设置")
    @RequestMapping(value = "/updateFollow/{version:.+}", method = RequestMethod.POST)
    public ResultDO<String> updateFollow(
            @ApiParam(name = "reqJson",value = "请求参数") @RequestBody AttentionReqDTO reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){
        return customerAO.updateFollow(reqJson);

    }


    @ApiOperation(value = "预约看铺",notes = "签约租铺申请")
    @RequestMapping(value = "/applyVisit/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Long> applyVisit(
            @ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody CustomerSignDTO reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.applyVisit(reqJson);

    }

    @ApiOperation(value = "找服务首页-最近寻租旺铺,贷款信息",notes = "找服务首页-最近寻租旺铺,贷款信息")
    @RequestMapping(value = "/serviceInfo/{version:.+}", method = RequestMethod.POST)
    public ResultDO<CustomerServiceDTO> serviceInfo(
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.serviceInfo();

    }

    @ApiOperation(value = "用户约看列表",notes = "用户约看列表")
    @RequestMapping(value = "/shopVisitedList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<ShopVisitCustomerDTO>> shopVisitedList(
            @ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody ShopVisitQuery reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.shopVisitedList(reqJson);

    }

    @ApiOperation(value = "用户约看次数和排名",notes = "用户约看次数和排名")
    @RequestMapping(value = "/myVisitStatistic/{version:.+}", method = RequestMethod.POST)
    public ResultDO<StatisticDTO> myVisitStatistic(
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.myVisitStatistic();

    }

    @ApiOperation(value = "约看删除",notes = "约看删除")
    @RequestMapping(value = "/deleteAppointment/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Boolean> cancelVisit(
            @ApiParam(name = "reqJson",value = "请求参数",required = true)@RequestBody JSONObject reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.cancelVisit(reqJson.getLong("shopId"));
    }

    @UnLoginLimit
    @ApiOperation(value = "预约统计",notes = "预约统计")
    @RequestMapping(value = "/visitStatistics/{version:.+}",method = RequestMethod.POST)
    public ResultDO<JSONObject> visitStatistics(
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.visitStatistics();
    }

    @UnLoginLimit
    @ApiOperation(value = "贷款申请统计",notes = "贷款申请统计")
    @RequestMapping(value = "/loadStatistics/{version:.+}",method = RequestMethod.POST)
    public ResultDO<LoanStatistics> statistics(
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.statistics();
    }

    @ApiOperation(value = "我的业务参数统计",notes = "我的业务参数统计")
    @RequestMapping(value = "/indexStatistics/{version:.+}",method = RequestMethod.POST)
    public ResultDO<IndexStatisticsRespDTO> indexStatistics(
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.indexStatistics();
    }

    @UnLoginLimit
    @ApiOperation(value = "预约看铺",notes = "签约租铺申请")
    @RequestMapping(value = "/h5/applyVisit/{version:.+}", method = RequestMethod.POST)
    public ResultDO<JSONObject> applyVisitShop(
            @ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody CustomerVisitDTO reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.applyVisitShop(reqJson);

    }


    /**
     * @description
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/8/9 0009 16:52
     * @param reqJson
     * @return ResultDO<String>
     *
     * v1.2新增
     *     --修改昵称和签名
     */
    @ApiOperation(value = "我的信息修改--昵称和签名",notes = "我的信息修改--昵称和签名")
    @RequestMapping(value = "/updateNickNameAndSign/{version:.+}",method = RequestMethod.POST)
    public ResultDO<String> updateNickNameAndSign(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody CustomerInfoDTO reqJson,
            @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.updateNickNameAndSign(reqJson);
    }

    /**
     * @description
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/8/9 0009 16:53
     * @param updatePhoneVerifyReqDTO
     * @return ResultDO<String>
     *
     * v1.2新增
     *     --修改用户手机号
     */
    @ApiOperation(value = "我的信息修改--手机号码",notes = "我的信息修改--手机号码")
    @RequestMapping(value = "/updateCustomerPhone/{version:.+}",method = RequestMethod.POST)
    public ResultDO<String> updateCustomerPhone(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody UpdatePhoneVerifyReqDTO updatePhoneVerifyReqDTO,
                                       @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){

        return customerAO.updateCustomerPhone(updatePhoneVerifyReqDTO);
    }

    /**
     * @description
     * @package com.dongtong.app.controller
     * @author liaozm
     * @date 2017/8/9 0009 16:53
     * @param query
     * @return ResultDO<String>
     *
     * v1.2新增
     *     --修改用户手机号
     */
    @ApiOperation(value = "我的服务列表",notes = "我的服务列表")
    @RequestMapping(value = "/myServiceList/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Page<ServiceListDTO>> myServiceList(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody ServiceListListQuery query,
                                                @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){
        return customerAO.myServiceList(query);
    }
    /**
     * @description
     * @package com.dongtong.app.controller
     * @author liaozm
     * @date 2017/8/14 9:57
     * @params
     * @return
     */
    @ApiOperation(value = "我的服务详情",notes = "我的服务列表")
    @RequestMapping(value = "/queryServiceDetail/{version:.+}",method = RequestMethod.POST)
    public ResultDO<ServiceDetailDTO> queryServiceDetail(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody CustomerServiceQuery query,
                                                        @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){
        return customerAO.queryServiceDetail(query);
    }

    /**
     * @description
     * @package com.dongtong.app.controller
     * @author liaozm
     * @date 2017/8/14 9:57
     * @params
     * @return
     */
    @ApiOperation(value = "确认服务",notes = "确认服务")
    @RequestMapping(value = "/confirmService/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Boolean> confirmService(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody CustomerServiceQuery query,
                                                             @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){
        return customerAO.confirmService(query);
    }

    /**
     * @description
     * @package com.dongtong.app.controller
     * @author liaozm
     * @date 2017/8/14 9:57
     * @params
     * @return
     */
    @ApiOperation(value = "撤销服务",notes = "撤销服务")
    @RequestMapping(value = "/revokedService/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Boolean> revokedService(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody CustomerServiceQuery query,
                                                         @ApiParam(name = "version",value = "版本号",required = true,defaultValue = "v1.0.0")@PathVariable("version") String version){
        return customerAO.revokedService(query);
    }

}
