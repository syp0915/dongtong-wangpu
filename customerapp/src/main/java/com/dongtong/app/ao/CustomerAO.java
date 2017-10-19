package com.dongtong.app.ao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.SmsUseSceneType;
import com.dongtong.basic.enums.WorkServiceType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.basic.service.SmsService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.dto.*;
import com.dongtong.customer.dto.req.AttentionReqDTO;
import com.dongtong.customer.dto.req.UpdatePhoneVerifyReqDTO;
import com.dongtong.customer.dto.resp.IndexStatisticsRespDTO;
import com.dongtong.customer.dto.resp.LoginRespDTO;
import com.dongtong.customer.dto.resp.StatisticDTO;
import com.dongtong.customer.enums.ScheduleStatus;
import com.dongtong.customer.enums.ScheduleType;
import com.dongtong.customer.query.BaseQuery;
import com.dongtong.customer.query.CustomerServiceQuery;
import com.dongtong.customer.query.LoginReqQuery;
import com.dongtong.customer.query.ServiceListListQuery;
import com.dongtong.customer.service.*;
import com.dongtong.shop.dto.LatestShopDTO;
import com.dongtong.shop.dto.ShopDetailDTO;
import com.dongtong.shop.dto.ShopVisitCustomerDTO;
import com.dongtong.shop.query.ShopVisitQuery;
import com.dongtong.shop.service.ShopService;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-11 14:58
 **/
@Service
public class CustomerAO {
    @Autowired(required = false)
    private CustomerLoanService customerLoanService;

    @Autowired(required = false)
    private CustomerInfoService customerInfoService;

    @Autowired(required = false)
    private CustomerService customerService;

    @Autowired(required = false)
    private SmsService smsService;


    @Autowired(required = false)
    private ShopService shopService;

    @Autowired(required = false)
    private ClerkService clerkService;

    @Autowired(required = false)
    private NoticePushService noticePushService;

    @Autowired(required = false)
    private ScheduleService scheduleService;

    @Autowired(required = false)
    private LoginAO loginAO;

    @Autowired(required = false)
    private CustomerVisitShopService customerVisitShopService;

    public ResultDO<String> applyLoad(LoadDTO dto){
        ResultDO<String> resultDO=new ResultDO<>();
        Long customerId= HttpSessionUtils.getCurrentAppUserId();


        //预约时间不能再当前时间之前
        /*int left=DateUtils.string2Date(dto.getSubscribeTime()).compareTo(DateUtils.getCurrentDate());
        if (left<0){
            resultDO.setErrMsg("约见时间不能小于当前时间");
            return resultDO;
        }*/

        //手机号码正则校验
        String phone = dto.getContactMobile();
        if (!ValidateHelper.isEmptyString(phone)) {
            Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
            Matcher m = p.matcher(phone);
            if (!m.matches()) {
                resultDO.setErrMsg("手机格式错误");
                return resultDO;
            }
        }else {
            resultDO.setErrMsg("手机号不能为空");
            return resultDO;
        }

        //根据用户Id查询用户注册手机号
        ResultDO<CustomerDTO> result=customerInfoService.customerInfo(customerId);
        if(!result.isSuccess()){
            resultDO.setErrMsg(result.getErrMsg());
            return resultDO;
        }

        //手机号修改后需要通过短信验证
        String checkResult=verifyCheckCode(dto.getContactMobile(),result.getData().getPhone(),dto.getSmsVerifyCode(),
                dto.getMessageId(),dto.getPicVerifyCode(),dto.getPicVerifyId(), SmsUseSceneType.APPLY_LOAD.getValue());
        if(!ValidateHelper.isEmpty(checkResult)){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }


        dto.setCustomerId(customerId);
        return customerLoanService.applyLoad(dto);
    }


    public ResultDO<Page<LoadDTO>> loanList(BaseQuery query){
        ResultDO<Page<LoadDTO>> resultDO=new ResultDO<>();

        Long customerId= HttpSessionUtils.getCurrentAppUserId();


        if(query.getPageNumber()==null||query.getPageSize()==null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        return customerLoanService.loanList(query,customerId);
    }

    public ResultDO<CustomerInfoDTO> queryCustomerInfo(){

        Long customerId=HttpSessionUtils.getCurrentAppUserId();

        return customerInfoService.queryCustomerInfo(customerId);
    }

    public ResultDO<String> updateHeadPortrait(String headPortrait){
        ResultDO<String> resultDO=new ResultDO<>();

        Long customerId=HttpSessionUtils.getCurrentAppUserId();

        String reg="(?i).+?\\.(jpg|gif|bmp|png|jpeg)";
        if(!headPortrait.startsWith("http://")||!headPortrait.matches(reg)){
            resultDO.setErrMsg("头像上传后地址格式异常！");
            return resultDO;
        }

        //v1.2修改--修改头像调用新修改个人信息接口
        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
        customerInfoDTO.setCustomerId(customerId);
        customerInfoDTO.setHeadPortrait(headPortrait);
        return customerInfoService.updateCustomerInfo(customerInfoDTO);
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/9 0009 15:34
     * @param dto
     * @return ResultDO<String>
     *
     * v1.2
     *   --修改入参
     *   --修改关注板块和关注行业可以为多选
     */
    public  ResultDO<String> updateFollow(AttentionReqDTO dto){
        ResultDO<String> resultDO=new ResultDO<>();

        Long customerId=HttpSessionUtils.getCurrentAppUserId();
        dto.setCustomerId(customerId);

        return customerInfoService.updateFollow(dto);
    }

    public ResultDO<Long> applySign(CustomerSignDTO dto){
        ResultDO<Long> resultDO=new ResultDO<>();
        Long customerId=HttpSessionUtils.getCurrentAppUserId();

        dto.setCustomerId(customerId);

        if(dto.getShopId()==null|| ValidateHelper.isEmpty(dto.getContactName())||
                ValidateHelper.isEmpty(dto.getContactMobile())){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        //预约时间不能再当前时间之前
        /*int left=DateUtils.string2Date(dto.getSubscribeTime()).compareTo(DateUtils.getCurrentDate());
        if (left<0){
            resultDO.setErrMsg("约见时间不能小于当前时间");
            return resultDO;
        }*/
        //手机号码正则校验
        String phone = dto.getContactMobile();
        if (!ValidateHelper.isEmptyString(phone)) {
            Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
            Matcher m = p.matcher(phone);
            if (!m.matches()) {
                resultDO.setErrMsg("手机格式错误");
                return resultDO;
            }
        }else {
            resultDO.setErrMsg("手机号不能为空");
            return resultDO;
        }

        //根据用户Id查询用户注册手机号
        ResultDO<CustomerDTO> customerDTOResultDO=customerInfoService.customerInfo(customerId);
        if(!customerDTOResultDO.isSuccess()){
            resultDO.setErrMsg(customerDTOResultDO.getErrMsg());
            return resultDO;
        }

        //手机号修改后需要通过短信验证
        String checkResult=verifyCheckCode(dto.getContactMobile(),customerDTOResultDO.getData().getPhone(),dto.getSmsVerifyCode(),
                dto.getMessageId(),dto.getPicVerifyCode(),dto.getPicVerifyId(), SmsUseSceneType.SHOP_SIGN.getValue());
        if(!ValidateHelper.isEmpty(checkResult)){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }

        //根据shopId获取商铺详情
        ResultDO<ShopDetailDTO> shopDetailDTOResultDO=shopService.queryShopDetail(dto.getShopId());
        if(!shopDetailDTOResultDO.isSuccess()){
            resultDO.setErrMsg(shopDetailDTOResultDO.getErrMsg());
            return resultDO;
        }
        ShopDetailDTO shopDetailDTO=shopDetailDTOResultDO.getData();

        //添加签约租铺申请
        ResultDO<Long> applyResult=customerService.applySign(dto,shopDetailDTO.getClerkId(),shopDetailDTO.getDistrictName(),shopDetailDTO.getAddress());
        if(!applyResult.isSuccess()){
            resultDO.setErrMsg(applyResult.getErrMsg());
            return resultDO;
        }

        //用户添加日程
        /*ResultDO<Long> resultDO1=addSchedule(dto,applyResult.getData(),shopDetailDTO.getClerkId(),shopDetailDTO.getAddress(),ScheduleType.SIGN_TENANT.getValue());
        if(!resultDO1.isSuccess()){
            resultDO.setErrMsg(resultDO1.getErrMsg());
            return resultDO;
        }*/

        //发送push消息
        ResultDO result=pushWorkNotice(shopDetailDTO.getClerkId(),applyResult.getData(),WorkServiceType.SIGN_CONTRACT.getValue(),shopDetailDTO.getAddress());

        Logger.info(CustomerAO.class,result.getErrMsg());

        resultDO.setData(applyResult.getData());
        resultDO.setSuccess(true);
        return resultDO;

    }


    public ResultDO<Long> applyVisit(CustomerSignDTO dto){
        ResultDO<Long> resultDO=new ResultDO<>();
        Long customerId=HttpSessionUtils.getCurrentAppUserId();

        dto.setCustomerId(customerId);

        if(dto.getShopId()==null|| ValidateHelper.isEmpty(dto.getContactName())||
                ValidateHelper.isEmpty(dto.getContactMobile())){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }

        //预约时间不能再当前时间之前
        /*int left=DateUtils.string2Date(dto.getSubscribeTime()).compareTo(DateUtils.getCurrentDate());
        if (left<0){
            resultDO.setErrMsg("约见时间不能小于当前时间");
            return resultDO;
        }*/

        //手机号码正则校验
        String phone = dto.getContactMobile();
        if (!ValidateHelper.isEmptyString(phone)) {
            Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
            Matcher m = p.matcher(phone);
            if (!m.matches()) {
                resultDO.setErrMsg("手机格式错误");
                return resultDO;
            }
        }else {
            resultDO.setErrMsg("手机号不能为空");
            return resultDO;
        }

        //根据用户Id查询用户注册手机号
        ResultDO<CustomerDTO> customerDTOResultDO=customerInfoService.customerInfo(customerId);
        if(!customerDTOResultDO.isSuccess()){
            resultDO.setErrMsg(customerDTOResultDO.getErrMsg());
            return resultDO;
        }

        //手机号修改后需要通过短信验证
        String checkResult=verifyCheckCode(dto.getContactMobile(),customerDTOResultDO.getData().getPhone(),dto.getSmsVerifyCode(),
                dto.getMessageId(),dto.getPicVerifyCode(),dto.getPicVerifyId(), SmsUseSceneType.SEE_SHOP.getValue());
        if(!ValidateHelper.isEmpty(checkResult)){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }

        //根据shopId获取商铺详情
        ResultDO<ShopDetailDTO> shopDetailDTOResultDO=shopService.queryShopDetail(dto.getShopId());
        if(!shopDetailDTOResultDO.isSuccess()){
            resultDO.setErrMsg(shopDetailDTOResultDO.getErrMsg());
            return resultDO;
        }
        ShopDetailDTO shopDetailDTO=shopDetailDTOResultDO.getData();

        //提交预约申请
        ResultDO<Long> applyResult=customerService.applyVisit(dto,shopDetailDTO.getClerkId(),shopDetailDTO.getDistrictName(),shopDetailDTO.getAddress());
        if(!applyResult.isSuccess()){
            resultDO.setErrMsg(applyResult.getErrMsg());
            return resultDO;
        }

        //用户添加日程
       /* ResultDO<Long> resultDO1=addSchedule(dto,applyResult.getData(),shopDetailDTO.getClerkId(),shopDetailDTO.getAddress(), ScheduleType.VISIT_TENANT.getValue());
        if(!resultDO1.isSuccess()){
            resultDO.setErrMsg(resultDO1.getErrMsg());
            return resultDO;
        }*/

        //发送push消息
        ResultDO result=pushWorkNotice(shopDetailDTO.getClerkId(),applyResult.getData(),WorkServiceType.ORDER_SEE.getValue(),shopDetailDTO.getAddress());

        Logger.info(CustomerAO.class, JSON.toJSONString(result));

        resultDO.setData(applyResult.getData());
        resultDO.setSuccess(true);
        return resultDO;

    }

    public ResultDO<CustomerServiceDTO> serviceInfo(){
        ResultDO<CustomerServiceDTO> resultDO=new ResultDO<>();
        Long customerId=HttpSessionUtils.getCurrentAppUserId();
        CustomerServiceDTO customerServiceDTO=new CustomerServiceDTO();

        //获取最近贷款信息
        LoadDTO loadDTO=customerLoanService.queryLastLoan(customerId);
        if(loadDTO!=null){
            customerServiceDTO.setLoadObject(loadDTO);
        }
        //最近续租商铺
        ResultDO<Customer> customerResultDO = customerService.findCustomerInfoByCustomerId(customerId);
        if (!customerResultDO.isSuccess()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }

        ResultDO<LatestShopDTO> result=shopService.getLatestShopByPhone(customerResultDO.getData().getPhone());
        if(!result.isSuccess()){
            resultDO.setErrMsg(result.getErrMsg());
            return resultDO;
        }
        LatestShopDTO latestShopDTO=result.getData();
        if(latestShopDTO!=null){

            customerServiceDTO.setShopObject(latestShopDTO);
        }

        //是否新用户
        customerServiceDTO.setIsNew(customerService.isNew(customerId));

        //设置最近约看旺铺
        ResultDO<VisitDTO> visitDTOResultDO = customerVisitShopService.queryLastVisit(customerId);
        if(!visitDTOResultDO.isSuccess()){
            resultDO.setErrMsg(result.getErrMsg());
            return resultDO;
        }
        VisitDTO visitDTO = visitDTOResultDO.getData();
        if (null != visitDTO){
            customerServiceDTO.setVisitShopObject(visitDTO);
        }

        resultDO.setData(customerServiceDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }

    public ResultDO<JSONObject> applyVisitShop(CustomerVisitDTO dto){
        ResultDO<JSONObject> resultDO=new ResultDO<>();
        //根据shopId获取商铺信息
        ResultDO<ShopDetailDTO> shopDetailDTOResultDO=shopService.queryShopDetail(dto.getShopId());
        if(!shopDetailDTOResultDO.isSuccess()){
            resultDO.setErrMsg(shopDetailDTOResultDO.getErrMsg());
            return resultDO;
        }
        ShopDetailDTO shopDetailDTO=shopDetailDTOResultDO.getData();
        //根据业务员Id获取业务员邀请码
        ResultDO<Clerk> result=clerkService.getClerkInfoById(shopDetailDTO.getCreater());
        if(!result.isSuccess()){
            resultDO.setErrMsg(result.getErrMsg());
            return resultDO;
        }

        //通过手机号验证码登录
        LoginReqQuery loginReqQuery =new LoginReqQuery();
        loginReqQuery.setUserPhone(dto.getContactMobile());
        loginReqQuery.setSmsVerifyCode(dto.getSmsVerifyCode());
        loginReqQuery.setMessageId(dto.getMessageId());
        loginReqQuery.setPicVerifyCode(dto.getPicVerifyCode());
        loginReqQuery.setPicVerifyId(dto.getPicVerifyId());
        loginReqQuery.setDeviceId(dto.getDeviceId());
        loginReqQuery.setOsType(dto.getOsType());
        loginReqQuery.setInviteCode(result.getData().getInvitationCode());
        ResultDO<LoginRespDTO> loginResultDO=loginAO.customerLogin(loginReqQuery);
        if(!loginResultDO.isSuccess()){
            resultDO.setErrCode(loginResultDO.getErrCode());
            resultDO.setErrMsg(loginResultDO.getErrMsg());
            return resultDO;
        }

        LoginRespDTO loginRespDTO=loginResultDO.getData();

        //约看
        CustomerSignDTO customerSignDTO=new CustomerSignDTO();
        customerSignDTO.setCustomerId(loginRespDTO.getCustomerId());
        customerSignDTO.setContactMobile(dto.getContactMobile());
        customerSignDTO.setContactName(dto.getContactName());
        customerSignDTO.setShopId(dto.getShopId());
//        customerSignDTO.setSubscribeTime(dto.getSubscribeTime());

        ResultDO<Long> visitResultDO=addVisitRecord(customerSignDTO);
        if(!visitResultDO.isSuccess()){
            resultDO.setErrCode(visitResultDO.getErrCode());
            resultDO.setErrMsg(visitResultDO.getErrMsg());
            return resultDO;
        }

        JSONObject object=new JSONObject();
        object.put("customerId",loginRespDTO.getCustomerId());
        object.put("accessToken",loginRespDTO.getAccessToken());
        object.put("scheduleId",visitResultDO.getData());

        resultDO.setData(object);
        resultDO.setSuccess(true);
        return resultDO;
    }

    public ResultDO<Long> addVisitRecord(CustomerSignDTO dto){
        ResultDO<Long> resultDO=new ResultDO<>();

        if(dto.getShopId()==null|| ValidateHelper.isEmpty(dto.getContactName())||
                ValidateHelper.isEmpty(dto.getContactMobile())){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }

        String phone = dto.getContactMobile();
        if (!ValidateHelper.isEmptyString(phone)) {
            Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
            Matcher m = p.matcher(phone);
            if (!m.matches()) {
                resultDO.setErrMsg("手机格式错误");
                return resultDO;
            }
        }else {
            resultDO.setErrMsg("手机号不能为空");
            return resultDO;
        }

        //预约时间不能再当前时间之前
        /*int left=DateUtils.string2Date(dto.getSubscribeTime()).compareTo(DateUtils.getCurrentDate());
        if (left<0){
            resultDO.setErrMsg("约见时间不能小于当前时间");
            return resultDO;
        }*/

//        //根据用户Id查询用户注册手机号
//        ResultDO<CustomerDTO> customerDTOResultDO=customerInfoService.customerInfo(dto.getCustomerId());
//        if(!customerDTOResultDO.isSuccess()){
//            resultDO.setErrMsg(customerDTOResultDO.getErrMsg());
//            return resultDO;
//        }
//
//        //手机号修改后需要通过短信验证
//        String checkResult=verifyCheckCode(dto.getContactMobile(),customerDTOResultDO.getData().getPhone(),dto.getSmsVerifyCode(),
//                dto.getMessageId(),dto.getPicVerifyCode(),dto.getPicVerifyId());
//        if(!ValidateHelper.isEmpty(checkResult)){
//            resultDO.setErrMsg(checkResult);
//            return resultDO;
//        }

        //根据shopId获取商铺详情
        ResultDO<ShopDetailDTO> shopDetailDTOResultDO=shopService.queryShopDetail(dto.getShopId());
        if(!shopDetailDTOResultDO.isSuccess()){
            resultDO.setErrMsg(shopDetailDTOResultDO.getErrMsg());
            return resultDO;
        }
        ShopDetailDTO shopDetailDTO=shopDetailDTOResultDO.getData();

        //提交预约申请
        ResultDO<Long> applyResult=customerService.applyVisit(dto,shopDetailDTO.getClerkId(),shopDetailDTO.getDistrictName(),shopDetailDTO.getAddress());
        if(!applyResult.isSuccess()){
            resultDO.setErrMsg(applyResult.getErrMsg());
            return resultDO;
        }

        //用户添加日程
        /*ResultDO<Long> resultDO1=addSchedule(dto,applyResult.getData(),shopDetailDTO.getClerkId(),shopDetailDTO.getAddress(), ScheduleType.VISIT_TENANT.getValue());
        if(!resultDO1.isSuccess()){
            resultDO.setErrMsg(resultDO1.getErrMsg());
            return resultDO;
        }*/

        //发送push消息
        ResultDO result=pushWorkNotice(shopDetailDTO.getClerkId(),applyResult.getData(),WorkServiceType.ORDER_SEE.getValue(),shopDetailDTO.getAddress());

        Logger.info("push消息返回结果:"+CustomerAO.class,JSON.toJSONString(result));

        resultDO.setData(applyResult.getData());
        resultDO.setSuccess(true);
        return resultDO;

    }

    public ResultDO<Page<ShopVisitCustomerDTO>> shopVisitedList(ShopVisitQuery query){
        ResultDO<Page<ShopVisitCustomerDTO>> resultDO=new ResultDO<>();

        Long customerId=HttpSessionUtils.getCurrentAppUserId();

        if (query.getPageNumber()==0||query.getPageSize()==0){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        query.setCustomerId(customerId);
        return shopService.shopVisitedList(query);

    }

    public ResultDO<StatisticDTO> myVisitStatistic(){
        Long customerId=HttpSessionUtils.getCurrentAppUserId();

        return customerInfoService.myVisitStatistic(customerId);

    }

    public ResultDO<Boolean> cancelVisit(Long shopId){
        ResultDO<Boolean> resultDO=new ResultDO<>();
        if(shopId==null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }

        Long customerId=HttpSessionUtils.getCurrentAppUserId();

        return customerService.cancelVisit(shopId,customerId);

    }

    public ResultDO<JSONObject> visitStatistics(){
        ResultDO<JSONObject> resultDO=new ResultDO<>();
        JSONObject jsonObject=new JSONObject();
        //商铺数量
        Integer amount=shopService.countShops();
        //预约人数
        Integer visit=customerService.visitStatistics();
        jsonObject.put("amount",amount);
        jsonObject.put("visit",visit);
        resultDO.setData(jsonObject);
        resultDO.setSuccess(true);

        return resultDO;
    }

    public ResultDO<LoanStatistics> statistics(){
        return customerLoanService.statistics();
    }

    public String verifyCheckCode(String oldPhone,String newPhone,String smsVerifyCode,Long messageId,String picVerifyCode,Long picVerifyId, Integer useScene){

        if(!oldPhone.equals(newPhone)){
            if(!ValidateHelper.isEmpty(picVerifyCode)&&picVerifyId!=null){
                ResultDO<Boolean> result=smsService.verifyPicVerify(picVerifyId,picVerifyCode);
                if(!result.isSuccess()){
                    return result.getErrMsg();
                }
            }
            //短信验证码不可为空
            if(ValidateHelper.isEmpty(smsVerifyCode)||messageId==null){
                return "短信验证码不可为空";
            }
            ResultDO<Boolean> result=smsService.verifySmsVerifyCode(messageId,smsVerifyCode, oldPhone, useScene);
            if(!result.isSuccess()){
                return result.getErrMsg();
            }
        }

        return null;
    }

    public ResultDO<IndexStatisticsRespDTO> indexStatistics(){
        ResultDO<IndexStatisticsRespDTO>  resultDO = new ResultDO<>();
        Long customerId=HttpSessionUtils.getCurrentAppUserId();
        IndexStatisticsRespDTO record =  customerService.indexStatistics(customerId);
        resultDO.setSuccess(true);
        resultDO.setData(record);
        return resultDO;
    }

    /**
     * @Description: 推送消息
     * @Title pushWorkNotice
     * @Author wuky
     * @Date 2017/05/18 17:01
     * @param clerkId
     * @param bizId
     * @param address
     * @param serviceType
     * @return
     */
    public ResultDO pushWorkNotice(Long clerkId,Long bizId,Integer serviceType,String address){
        final ResultDO resultDO=new ResultDO();
        if(clerkId==null){
            resultDO.setErrMsg("业务员Id不能为空");
            return resultDO;
        }
        //根据业务员Id获取设备Id
        ResultDO<Clerk> clerkResultDO=clerkService.getClerkInfoById(clerkId);
        if(!clerkResultDO.isSuccess()){
            resultDO.setErrMsg(clerkResultDO.getErrMsg());
            return resultDO;
        }

        final Clerk clerk=clerkResultDO.getData();

        final WorkNoticeReqDTO workNoticeReqDTO=new WorkNoticeReqDTO();
        workNoticeReqDTO.setServiceType(serviceType);
        workNoticeReqDTO.setShopAddress(address);
        workNoticeReqDTO.setReceiveType(ReceiveType.CLERK.getValue());
        workNoticeReqDTO.setReceiveId(clerkId);
        workNoticeReqDTO.setBussinessId(bizId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ResultDO resultDO2=noticePushService.pushWorkNotice(workNoticeReqDTO,clerk.getDeviceId(),clerk.getOsType());
                Logger.info(CustomerAO.class,"消息推送:"+JSON.toJSONString(resultDO2));
            }
        }).start();


        resultDO.setSuccess(true);
        return resultDO;
    }
    /**
     * @Description: 添加日程
     * @Title addSchedule
     * @Author wuky
     * @Date 2017/05/18 17:01
     * @param dto
     * @param bizId
     * @param clerkId
     * @param shopAddress
     * @param type
     * @return
     */

    public ResultDO<Long> addSchedule(CustomerSignDTO dto,Long bizId,Long clerkId,String shopAddress,Integer type){

        CustomerScheduleDTO customerScheduleDTO=new CustomerScheduleDTO();
        customerScheduleDTO.setCustomerId(dto.getCustomerId());
        customerScheduleDTO.setBizId(bizId);
        customerScheduleDTO.setClerkId(clerkId);
        customerScheduleDTO.setType(type);
        customerScheduleDTO.setStatus(ScheduleStatus.ACCEPTING.getValue());
        customerScheduleDTO.setMeetTime(DateUtils.string2Date(dto.getSubscribeTime()));
        customerScheduleDTO.setApplicationTime(new Date());
        customerScheduleDTO.setAddress(shopAddress);

        return scheduleService.addSchedule(customerScheduleDTO);
    }


    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/9 0009 16:13
     * @param dto
     * @return ResultDO<String>
     *
     * v1.2新增
     *     --修改昵称和签名接口
     */
    public ResultDO<String> updateNickNameAndSign(CustomerInfoDTO dto){
        ResultDO<String> resultDO = new ResultDO<>();
        Long customerId=HttpSessionUtils.getCurrentAppUserId();
        dto.setCustomerId(customerId);
        resultDO = customerInfoService.updateCustomerInfo(dto);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/9 0009 16:49
     * @param updatePhoneVerifyReqDTO
     * @return ResultDO<String>
     *
     * v1.2新增
     *     --修改个人手机号码
     */
    public ResultDO<String> updateCustomerPhone(UpdatePhoneVerifyReqDTO updatePhoneVerifyReqDTO){
        ResultDO<String> resultDO = new ResultDO<>();
        String newPhone = updatePhoneVerifyReqDTO.getNewPhone();
        String oldPhone = updatePhoneVerifyReqDTO.getOldPhone();
        String smsVerifyCode = updatePhoneVerifyReqDTO.getSmsVerifyCode();//短信验证码
        Long messageId = updatePhoneVerifyReqDTO.getMessageId();
        String picVerifyCode = updatePhoneVerifyReqDTO.getPicVerifyCode();//图片验证码
        Long picVerifyId = updatePhoneVerifyReqDTO.getPicVerifyId();

        //验证手机号
        String checkResult = verifyCheckCode(newPhone,smsVerifyCode,messageId,picVerifyCode,picVerifyId, SmsUseSceneType.UPDATE_PHONE.getValue());
        if(!ValidateHelper.isEmpty(checkResult)){
            resultDO.setErrMsg(checkResult);
            resultDO.setData(checkResult);
            return resultDO;
        }

        ResultDO<Customer> customerInfoDO = customerService.getCustomerInfoByUserPhone(newPhone);
        if(customerInfoDO != null && customerInfoDO.isSuccess()
                && customerInfoDO.getData() != null){
            Customer customer = customerInfoDO.getData();
            if(customer.getId() != null){
                resultDO.setErrMsg("手机号码已经存在");
                resultDO.setData("手机号码已经存在");
                return resultDO;
            }
        }

        Long customerId=HttpSessionUtils.getCurrentAppUserId();
        //更新个人信息
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.setCustomerId(customerId);
        dto.setPhone(newPhone);
        resultDO = customerInfoService.updateCustomerInfo(dto);

        return resultDO;
    }

    /**
     * @description 验证手机号码---个人信息修改手机号码
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/15 0015 9:29
     * @param newPhone
     * @return String
     */
    public String verifyCheckCode(String newPhone,String smsVerifyCode,Long messageId,String picVerifyCode,Long picVerifyId, Integer useScene) {
        if (!ValidateHelper.isEmpty(picVerifyCode) && picVerifyId != null) {
            ResultDO<Boolean> result = smsService.verifyPicVerify(picVerifyId, picVerifyCode);
            if (!result.isSuccess()) {
                return result.getErrMsg();
            }
        }
        //短信验证码不可为空
        if (ValidateHelper.isEmpty(smsVerifyCode) || messageId == null) {
            return "短信验证码不可为空";
        }
        ResultDO<Boolean> result = smsService.verifySmsVerifyCode(messageId, smsVerifyCode, newPhone, useScene);
        if (!result.isSuccess()) {
            return result.getErrMsg();
        }
        return null;
    }
    /**
     * @description 我的服务列表功能
     * @package com.dongtong.app.ao
     * @author liaozm
     * @date 2017/8/14 9:55
     * @params
     * @return
     */
    public ResultDO<Page<ServiceListDTO>> myServiceList(ServiceListListQuery query){
        ResultDO<Page<ServiceListDTO>> resultDO = new ResultDO<Page<ServiceListDTO>>();
        Long customerId=HttpSessionUtils.getCurrentAppUserId();
        query.setUserId(customerId);
        if(ValidateHelper.isEmpty(query.getType())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        resultDO = customerService.myServiceList(query);
        return resultDO;
    }

    /**
     * @description 我的服务详情功能
     * @package com.dongtong.app.ao
     * @author liaozm
     * @date 2017/8/14 9:55
     * @params
     * @return
     */
    public ResultDO<ServiceDetailDTO> queryServiceDetail(CustomerServiceQuery query){
        ResultDO<ServiceDetailDTO> resultDO = new ResultDO<ServiceDetailDTO>();
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        query.setUserId(customerId);
        if(ValidateHelper.isEmpty(query.getType()) || ValidateHelper.isEmpty(query.getId())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        resultDO = customerService.queryServiceDetail(query);
        return resultDO;
    }

    /**
     * @description 我的服务详情功能
     * @package com.dongtong.app.ao
     * @author liaozm
     * @date 2017/8/14 9:55
     * @params
     * @return
     */
    public ResultDO<Boolean> confirmService(CustomerServiceQuery query){
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if(ValidateHelper.isEmpty(query.getType()) || ValidateHelper.isEmpty(query.getId())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        resultDO = customerService.confirmService(query);
        return resultDO;
    }

    /**
     * @description 我的服务详情功能
     * @package com.dongtong.app.ao
     * @author liaozm
     * @date 2017/8/14 9:55
     * @params
     * @return
     */
    public ResultDO<Boolean> revokedService(CustomerServiceQuery query){
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if(ValidateHelper.isEmpty(query.getType()) || ValidateHelper.isEmpty(query.getId())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        resultDO = customerService.revokedService(query);
        return resultDO;
    }
}
