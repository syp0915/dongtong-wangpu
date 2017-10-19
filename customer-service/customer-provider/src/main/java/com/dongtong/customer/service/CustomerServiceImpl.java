package com.dongtong.customer.service;

import com.dongtong.basic.StringUtils;
import com.dongtong.basic.enums.SmsUseSceneType;
import com.dongtong.basic.service.SmsService;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.constant.CustomerProperties;
import com.dongtong.customer.constant.ErrorConstant;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.domain.CustomerVisitShop;
import com.dongtong.customer.dto.CustomerSignDTO;
import com.dongtong.customer.dto.LoadDTO;
import com.dongtong.customer.dto.ServiceDetailDTO;
import com.dongtong.customer.dto.ServiceListDTO;
import com.dongtong.customer.dto.resp.IndexStatisticsRespDTO;
import com.dongtong.customer.dto.resp.LoginRespDTO;
import com.dongtong.customer.enums.YesNo;
import com.dongtong.customer.manager.CustomerLoanManager;
import com.dongtong.customer.manager.CustomerManager;
import com.dongtong.customer.manager.ScheduleManager;
import com.dongtong.customer.query.CustomerServiceQuery;
import com.dongtong.customer.query.ServiceListListQuery;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 下午2:36.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired(required = false)
    private SmsService smsService;

    @Autowired(required = false)
    private CustomerManager customerManager;

    @Autowired(required = false)
    private ClerkService clerkService;

    @Autowired(required = false)
    private ScheduleManager scheduleManager;

    @Autowired(required = false)
    private CustomerLoanManager customerLoanManager;

    @Autowired
    private CustomerProperties customerProperties;

    /**
     * 用户登录
     *
     * @param userPhone     登录手机号
     * @param smsVerifyCode 短信验证码
     * @param messageId     短信验证码消息id
     * @param picVerifyCode 图片验证码
     * @param picVerifyId
     * @return
     */
    @Transactional
    @Override
    public ResultDO<LoginRespDTO> customerLogin(String userPhone, String smsVerifyCode, Long messageId, String picVerifyCode, Long picVerifyId, String inviteCode, Integer osType,String deviceId) {
        ResultDO<LoginRespDTO> resultDO = new ResultDO<LoginRespDTO>();
        if (ValidateHelper.isEmpty(userPhone)
                || ValidateHelper.isEmpty(smsVerifyCode)
                || ValidateHelper.isEmpty(messageId)
                || ValidateHelper.isEmpty(osType)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        ResultDO<Boolean> smsResult = smsService.verifySmsVerifyCode(messageId, smsVerifyCode, userPhone, SmsUseSceneType.LOGIN.getValue());
        if (!smsResult.isSuccess()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(smsResult.getErrCode());
            resultDO.setErrMsg(smsResult.getErrMsg());
            return resultDO;
        }
        if (!ValidateHelper.isEmpty(picVerifyCode) && !ValidateHelper.isEmpty(picVerifyId)){
            ResultDO picResult = smsService.verifyPicVerify(picVerifyId, picVerifyCode);
            if (!picResult.isSuccess()){
                resultDO.setSuccess(false);
                resultDO.setErrCode(picResult.getErrCode());
                resultDO.setErrMsg(picResult.getErrMsg());
                return resultDO;
            }
        }
        Customer customer = customerManager.findCustomerInfoByUserPhone(userPhone);
        if (customer == null){
            //用户表没找到，到业务员表查找
           /* ResultDO<Clerk> clerkResultDO= clerkService.getClerkInfoByUserPhone(userPhone);
            if (!clerkResultDO.isSuccess()){
                if (ErrorConstant.OBJECT_NOT_EXIST.getCode() != clerkResultDO.getErrCode()){
                    resultDO.setSuccess(false);
                    resultDO.setErrCode(clerkResultDO.getErrCode());
                    resultDO.setErrMsg(clerkResultDO.getErrMsg());
                    return resultDO;
                }
            }
            if (clerkResultDO.getData() != null){
                resultDO.setSuccess(false);
                resultDO.setErrCode(ErrorConstant.PHONE_USED_BY_CLERK.getCode());
                resultDO.setErrMsg(ErrorConstant.PHONE_USED_BY_CLERK.getMsg());
                return resultDO;
            }*/
            //走注册流程
            customer = new Customer();
            customer.setPhone(userPhone);
            customer.setOsType(osType);
            if (!ValidateHelper.isEmpty(deviceId)){
                customer.setDeviceId(deviceId);
            }
            customer.setStatus(0);
            if (!ValidateHelper.isEmpty(inviteCode)){
                customer.setInvitationCode(inviteCode);
            }
            customer.setIsBanned(0);
            customer.setSignature("老板很懒，暂未设置签名");
            customer.setNickName("老板" + userPhone.substring(userPhone.length()-4,userPhone.length()));
            customerManager.registerCustomer(customer);
        }
        if (customer.getStatus() != 0){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.ACCOUNT_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.ACCOUNT_EXCEPTION.getMsg());
            return resultDO;
        }
        Customer tmpCustomer = new Customer();
        tmpCustomer.setId(customer.getId());
        tmpCustomer.setVersion(customer.getVersion());
        //Integer needUpdate = 0;
        if (!ValidateHelper.isEmpty(deviceId)){
            tmpCustomer.setDeviceId(deviceId);
            //needUpdate++;
        }
        if (osType != customer.getOsType()){
            tmpCustomer.setOsType(osType);
           // needUpdate++;
        }
        tmpCustomer.setLastLoginTime(DateUtils.getCurrentDate());
        customerManager.updateByPrimaryKeySelective(tmpCustomer);
        //排他登录控制
        Object otherToken = RedisUtil.get("CUSTOMER_UNIQ_" + userPhone);
        if (otherToken != null){
            RedisUtil.del(otherToken.toString());
        }
        String token = StringUtils.generateToken();
        //获取不失效手机号集合
        Logger.info(CustomerServiceImpl.class,"result---------->"+customerProperties.getSpecialPhone());
        List usList=new ArrayList();
        //获取不失效的手机号
        if(null!=customerProperties.getSpecialPhone() &&
                !customerProperties.getSpecialPhone().equals("")){
            String[] userphones=customerProperties.getSpecialPhone().split(",");
            usList=java.util.Arrays.asList(userphones);
        }
        //设置配置的手机号不失效
        if(usList!=null && usList.size()>0 && usList.contains(userPhone)==true){
            token="3C59EA1FEF108487406A643F8DF3F640";
            RedisUtil.set("CUSTOMER_" + token, customer.getId());
            RedisUtil.set("CUSTOMER_UNIQ_" + userPhone, "CUSTOMER_" + token);
        }else{
            RedisUtil.set("CUSTOMER_" + token, customer.getId(), 30 * 24 * 60 * 60);
            RedisUtil.set("CUSTOMER_UNIQ_" + userPhone, "CUSTOMER_" + token, 30 * 24 * 60 * 60);
        }
        LoginRespDTO loginRespDTO = new LoginRespDTO();
        loginRespDTO.setAccessToken(token);
        loginRespDTO.setCustomerId(customer.getId());
        loginRespDTO.setIsBanned(customer.getIsBanned());     //v1.2新增
        loginRespDTO.setIsNew(isNew(customer.getId()));       //v1.2新增
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(loginRespDTO);
        return resultDO;
    }

    /**
     * 用户退出登录
     *
     * @param customerId
     * @return
     */
    @Override
    public ResultDO customerLogout(Long customerId, String token) {
        ResultDO resultDO = new ResultDO();
        if (customerId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Customer customer = customerManager.findCustomerInfoByCustomerId(customerId);
        if (customer == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        Customer t_customer = new Customer();
        t_customer.setId(customer.getId());
        t_customer.setDeviceId(null);
        t_customer.setVersion(customer.getVersion());
        customerManager.updateByPrimaryKeySelective(t_customer);
        RedisUtil.del("CUSTOMER_" + token);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        return resultDO;
    }

    /**
     * 更新用户deviceId
     *
     * @param customerId
     * @param deviceId
     * @return
     */
    @Override
    public ResultDO updateDeviceId(Long customerId, String deviceId) {
        ResultDO resultDO = new ResultDO();
        if (ValidateHelper.isEmpty(customerId) || ValidateHelper.isEmpty(deviceId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Customer customer = customerManager.findCustomerInfoByCustomerId(customerId);
        if (customer == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        Customer t_customer = new Customer();
        t_customer.setId(customer.getId());
        t_customer.setDeviceId(deviceId);
        t_customer.setVersion(customer.getVersion());
        customerManager.updateByPrimaryKeySelective(t_customer);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        return resultDO;
    }

    /**
     * 根据手机号获取用户信息
     *
     * @param userPhone
     * @return
     */
    @Override
    public ResultDO<Customer> getCustomerInfoByUserPhone(String userPhone) {
        ResultDO<Customer> resultDO = new ResultDO<Customer>();
        if (ValidateHelper.isEmpty(userPhone)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Customer customer = customerManager.getCustomerInfoByUserPhone(userPhone);
        if (customer == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(customer);
        return resultDO;
    }

    @Override
    public ResultDO<Long> applySign(CustomerSignDTO dto,Long clerkId,String district,String shopAddress) {
        ResultDO<Long> resultDO=new ResultDO<>();

        //添加签约申请
        Long bizId=customerManager.addSign(dto,clerkId,district,shopAddress);

//        //用户添加日程
//        ResultDO<Long> result=addSchedule(dto,bizId,clerkId,shopAddress,ScheduleType.SIGN.getValue());
//        if(!result.isSuccess()){
//            resultDO.setErrMsg(result.getErrMsg());
//            return resultDO;
//        }

        resultDO.setData(bizId);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<Long> applyVisit(CustomerSignDTO dto, Long clerkId, String district, String shopAddress) {
        ResultDO<Long> resultDO=new ResultDO<>();

        //添加约看
        Long bizId=customerManager.addAppointment(dto,clerkId,district,shopAddress);

//        //用户添加日程
//        ResultDO<Long> result=addSchedule(dto,bizId,clerkId,shopAddress,ScheduleType.VISIT.getValue());
//        if(!result.isSuccess()){
//            resultDO.setErrMsg(result.getErrMsg());
//            return resultDO;
//        }
        //
        //每当有一人约看时，约看添加数1
        if (null == RedisUtil.get("CUSTOMER_VISIT_STATISTICS")){
            RedisUtil.set("CUSTOMER_VISIT_STATISTICS",1);
        }else {
            RedisUtil.set("CUSTOMER_VISIT_STATISTICS",
                    Integer.parseInt(RedisUtil.get("CUSTOMER_VISIT_STATISTICS").toString())+1);
        }
        resultDO.setData(bizId);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> cancelVisit(Long shopId, Long customerId) {
        ResultDO<Boolean> resultDO=new ResultDO<>();
        List<CustomerVisitShop> list=customerManager.queryShopVisitById(shopId,customerId);
        if(list==null || list.isEmpty()){
            resultDO.setErrMsg("约看记录不存在");
            return resultDO;
        }

        CustomerVisitShop record = new CustomerVisitShop();
        record.setIsDelete(YesNo.YES.getValue());
        record.setModifier(customerId);
        int result  = customerManager.cannelShopVisitById(shopId,customerId);
        if(result>0){
            resultDO.setSuccess(true);
            resultDO.setData(true);
            return resultDO;
        }

//        //根据约看表的主键Id和约看类型type查询日程
//        CustomerSchedule customerSchedule=scheduleManager.queryScheduleByBizId(visitId,ScheduleType.VISIT.getValue());
//        if(customerSchedule==null){
//            resultDO.setErrMsg("约看记录对应的日程不存在");
//            return resultDO;
//        }
//        //只能撤销自己的日程
//        if(customerSchedule.getCustomerId()!=null&&customerSchedule.getCustomerId().longValue()!=customerId.longValue()){
//            resultDO.setErrMsg("只能删除自己的日程");
//            return resultDO;
//
//        }
//
//        //如果日程处于服务受理中的,修改状态
//        if(customerSchedule.getStatus()==ScheduleStatus.ACCEPTING.getValue()){
//            customerSchedule.setStatus(ScheduleStatus.CANCEL.getValue());
//            scheduleManager.updatScheduleStatus(customerSchedule);
//        }
        return resultDO;
    }

    @Override
    public Integer visitStatistics() {
        //获取约看默认人数
        if(RedisUtil.get("CUSTOMER_VISIT_STATISTICS")==null){
            RedisUtil.set("CUSTOMER_VISIT_STATISTICS",12);//默认是十二
        }
        return Integer.parseInt(RedisUtil.get("CUSTOMER_VISIT_STATISTICS").toString());
    }

    @Override
    public IndexStatisticsRespDTO indexStatistics(Long customerId) {
        return customerManager.indexStatistics(customerId);
    }

    @Override
    public Integer isNew(Long customerId) {
        Integer result= scheduleManager.queryScheduleByCustomerId(customerId);
        LoadDTO loadDTO=customerLoanManager.queryLastLoad(customerId);
        if(result==0&&loadDTO==null){
            return YesNo.YES.getValue();
        }else {
            return YesNo.NO.getValue();
        }
    }

    @Override
    public ResultDO<Page<ServiceListDTO>> myServiceList(ServiceListListQuery query) {
        ResultDO<Page<ServiceListDTO>> resultDO = new ResultDO<Page<ServiceListDTO>>();
        if (ValidateHelper.isEmpty(query.getUserId())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Page<ServiceListDTO> page = new Page<ServiceListDTO>(query.getPageNumber(),query.getPageSize());
        page.setQuery(query);
        resultDO.setData(customerManager.selectServiceList(page));
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<ServiceDetailDTO> queryServiceDetail(CustomerServiceQuery query) {
        ResultDO<ServiceDetailDTO> resultDO = new ResultDO<ServiceDetailDTO>();
        if (ValidateHelper.isEmpty(query.getId()) || ValidateHelper.isEmpty(query.getType())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return customerManager.queryServiceDetail(query);
    }

    @Override
    public ResultDO<Boolean> confirmService(CustomerServiceQuery query) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if (ValidateHelper.isEmpty(query.getId()) || ValidateHelper.isEmpty(query.getType())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return customerManager.confirmService(query);
    }

    @Override
    public ResultDO<Boolean> revokedService(CustomerServiceQuery query) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if (ValidateHelper.isEmpty(query.getId()) || ValidateHelper.isEmpty(query.getType())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return customerManager.revokedService(query);
    }


//
//    public ResultDO<Long> addSchedule(CustomerSignDTO dto,Long bizId,Long clerkId,String shopAddress,Integer type){
//
//        CustomerScheduleDTO customerScheduleDTO=new CustomerScheduleDTO();
//        customerScheduleDTO.setCustomerId(dto.getCustomerId());
//        customerScheduleDTO.setBizId(bizId);
//        customerScheduleDTO.setClerkId(clerkId);
//        customerScheduleDTO.setType(type);
//        customerScheduleDTO.setStatus(ScheduleStatus.ACCEPTING.getValue());
//        customerScheduleDTO.setMeetTime(DateUtils.string2Date(dto.getSubscribeTime()));
//        customerScheduleDTO.setApplicationTime(new Date());
//        customerScheduleDTO.setAddress(shopAddress);
//
//        return scheduleService.addSchedule(customerScheduleDTO);
//    }

    @Override
    public ResultDO<Customer> findCustomerInfoByCustomerId(Long customerId) {
        ResultDO<Customer> customerResultDO=new ResultDO<>();
        Customer customer=customerManager.findCustomerInfoByCustomerId(customerId);
        customerResultDO.setSuccess(true);
        customerResultDO.setData(customer);
        return customerResultDO;
    }
}
