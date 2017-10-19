package com.dongtong.clerk.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.StringUtils;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.SmsUseSceneType;
import com.dongtong.basic.service.NotificationService;
import com.dongtong.basic.service.SmsService;
import com.dongtong.clerk.constant.ClerkProviderEnvProperties;
import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.dto.ClerkDetailDTO;
import com.dongtong.clerk.dto.UpdateClerkDetailDTO;
import com.dongtong.clerk.dto.resp.LoginRespDTO;
import com.dongtong.clerk.manager.ClerkManager;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
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
 * @date 2017/5/9 下午3:21.
 */
@Service
public class ClerkServiceImpl implements ClerkService {

    @Autowired(required = false)
    private ClerkManager clerkManager;

    @Autowired(required = false)
    private SmsService smsService;

    @Autowired(required = false)
    private NotificationService notificationService;

    @Autowired
    private ClerkProviderEnvProperties clerkProviderEnvProperties;


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
    @Override
    public ResultDO<LoginRespDTO> clerkLogin(String userPhone, String smsVerifyCode, Long messageId, String picVerifyCode, Long picVerifyId, String inviteCode, Integer osType, String deviceId) {
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
       /* if (!ValidateHelper.isEmpty(picVerifyCode) && !ValidateHelper.isEmpty(picVerifyId)){
            ResultDO picResult = smsService.verifyPicVerify(picVerifyId, picVerifyCode);
            if (!picResult.isSuccess()){
                resultDO.setSuccess(false);
                resultDO.setErrCode(picResult.getErrCode());
                resultDO.setErrMsg(picResult.getErrMsg());
                return resultDO;
            }
        }*/

        Clerk clerk = clerkManager.getClerkInfoByUserPhone(userPhone);
        if (clerk == null){
            //用户表没找到，到业务员表查找
//            ResultDO<Customer> customerResultDO= customerService.getCustomerInfoByUserPhone(userPhone);
//            if (!customerResultDO.isSuccess()){
//                if (ErrorConstant.OBJECT_NOT_EXIST.getCode() != customerResultDO.getErrCode()){
//                    resultDO.setSuccess(false);
//                    resultDO.setErrCode(customerResultDO.getErrCode());
//                    resultDO.setErrMsg(customerResultDO.getErrMsg());
//                    return resultDO;
//                }
//            }
//            if (customerResultDO.getData() != null){
//                resultDO.setSuccess(false);
//                resultDO.setErrCode(ErrorConstant.PHONE_USED_BY_CUSTOMER.getCode());
//                resultDO.setErrMsg(ErrorConstant.PHONE_USED_BY_CUSTOMER.getMsg());
//                return resultDO;
//            }
//            //走注册流程
//            clerk = new Clerk();
//            clerk.setPhone(userPhone);
//            clerk.setOsType(osType);
//            clerk.setInvitationCode(userPhone);//设置业务员邀请码，手机号即为邀请码
//            if (!ValidateHelper.isEmpty(deviceId)){
//                clerk.setDeviceId(deviceId);
//            }
//            clerk.setStatus(0);
//            clerkManager.registerClerk(clerk);
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.ERROR_NO_REGISTER.getCode());
            resultDO.setErrMsg(ErrorConstant.ERROR_NO_REGISTER.getMsg());
            return resultDO;
        }
        if (clerk.getStatus() != 0){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.ACCOUNT_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.ACCOUNT_EXCEPTION.getMsg());
            return resultDO;
        }

        Clerk tmpClerk = new Clerk();
        tmpClerk.setId(clerk.getId());
        tmpClerk.setVersion(clerk.getVersion());
        Integer needUpdate = 0;
        if (!ValidateHelper.isEmpty(deviceId)){//deviceId
            tmpClerk.setDeviceId(deviceId);
            needUpdate++;
        }
        if (osType != clerk.getOsType()){
            tmpClerk.setOsType(osType);
            needUpdate++;
        }
        if (needUpdate > 0){
            clerkManager.updateByPrimaryKeySelective(tmpClerk);
        }

        //排他登录控制
        Object otherToken = RedisUtil.get("CLERK_UNIQ_" + userPhone);
        if (otherToken != null){
            RedisUtil.del(otherToken.toString());
        }

        String token = StringUtils.generateToken();
        List usList=new ArrayList();
        Logger.info(ClerkServiceImpl.class,"result---------->"+clerkProviderEnvProperties.getSpecialPhone());
        //获取不失效的手机号
        if(null!=clerkProviderEnvProperties.getSpecialPhone() &&
                !clerkProviderEnvProperties.getSpecialPhone().equals("")){
            String[] userphones=clerkProviderEnvProperties.getSpecialPhone().split(",");
            usList=java.util.Arrays.asList(userphones);
        }
        //设置配置的手机号不失效
        if(usList!=null && usList.size()>0 && usList.contains(userPhone)==true){
            token="3C59EA1FEF108487406A643F8DF3F640";
            RedisUtil.set("CLERK_" + token, clerk.getId());
            RedisUtil.set("CLERK_UNIQ_" + userPhone, "CLERK_" + token);
        }else {
            RedisUtil.set("CLERK_" + token, clerk.getId(), 30 * 24 * 60 * 60);
            RedisUtil.set("CLERK_UNIQ_" + userPhone, "CLERK_" + token, 30 * 24 * 60 * 60);
        }
        LoginRespDTO loginRespDTO = new LoginRespDTO();
        loginRespDTO.setAccessToken(token);
        loginRespDTO.setClerkId(clerk.getId());
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(loginRespDTO);
        return resultDO;
    }

    /**
     * 根据id查询业务员信息
     *
     * @param clerkId
     * @return
     */
    @Override
    public ResultDO<Clerk> getClerkInfoById(Long clerkId) {
        ResultDO<Clerk> resultDO = new ResultDO<Clerk>();
        if (ValidateHelper.isEmpty(clerkId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Clerk clerk = clerkManager.getClerkInfoById(clerkId);
        if (clerk == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(clerk);
        return resultDO;
    }

    /**
     * 根据id查询业务员信息
     *
     * @param userPhone
     * @return
     */
    @Override
    public ResultDO<Clerk> getClerkInfoByUserPhone(String userPhone) {
        ResultDO<Clerk> resultDO = new ResultDO<Clerk>();
        if (ValidateHelper.isEmpty(userPhone)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Clerk clerk = clerkManager.getClerkInfoByUserPhone(userPhone);
        if (clerk == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(clerk);
        return resultDO;
    }

    @Override
    public ResultDO<ClerkDetailDTO> getClerkDetailById(Long clerkId) {
        ResultDO<ClerkDetailDTO> resultDO = new ResultDO<ClerkDetailDTO>();
        if (ValidateHelper.isEmpty(clerkId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Clerk clerk = clerkManager.getClerkInfoById(clerkId);
        if (clerk == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        ClerkDetailDTO clerkDetailDTO  = new ClerkDetailDTO();
        clerkDetailDTO.setHeadPortrait(com.shfc.common.base.StringUtils.toString(clerk.getHeadPortrait(),""));
        clerkDetailDTO.setPhone(com.shfc.common.base.StringUtils.toString(clerk.getPhone(),""));
        clerkDetailDTO.setInvitationCode(com.shfc.common.base.StringUtils.toString(clerk.getInvitationCode(),""));
        clerkDetailDTO.setRealName(com.shfc.common.base.StringUtils.toString(clerk.getRealName(),""));
        clerkDetailDTO.setRoleType(clerk.getRoleType());
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(clerkDetailDTO);
        return resultDO;
    }

    @Override
    @Transactional
    public ResultDO updateClerkDetail(UpdateClerkDetailDTO updateClerkDetailDTO) {
        ResultDO resultDO = new ResultDO();
        if (ValidateHelper.isEmpty(updateClerkDetailDTO.getClerkId())|| ValidateHelper.isEmpty(updateClerkDetailDTO.getRealName()) ||ValidateHelper.isEmpty(updateClerkDetailDTO.getUrl())){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            Clerk clerk = clerkManager.getClerkInfoById(updateClerkDetailDTO.getClerkId());
            if (clerk == null){
                resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
                resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
                return resultDO;
            }
            clerk.setRealName(updateClerkDetailDTO.getRealName());
            clerk.setHeadPortrait(updateClerkDetailDTO.getUrl());
            clerkManager.updateClerkInfo(clerk);
        }catch (Exception ex){
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> updateClerkInfo(UpdateClerkDetailDTO updateClerkDetailDTO) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        Long clerkId =  updateClerkDetailDTO.getClerkId();
        String url = updateClerkDetailDTO.getUrl();//头像URL
        if(ValidateHelper.isEmptyString(url)){
            resultDO.setErrMsg("头像不能为空！");
            return resultDO;
        }
        Clerk clerk = clerkManager.getClerkInfoById(clerkId);
        if (clerk == null){
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        clerk.setHeadPortrait(url);
        boolean updateResult = clerkManager.updateByPrimaryKeySelective(clerk);
        if(updateResult){
            resultDO.setSuccess(true);
            resultDO.setData(true);
            return resultDO;
        }
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> isNewNotification(Long clerkId) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if (ValidateHelper.isEmpty(clerkId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Clerk clerk = clerkManager.getClerkInfoById(clerkId);
        if (clerk == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        resultDO = notificationService.checkNotification(clerkId.toString(),ReceiveType.CLERK.getValue());
        System.out.println(JSON.toJSONString(resultDO));
        return resultDO;
    }

    @Override
    public ResultDO<List<Long>> getAllClerkIds() {
        ResultDO<List<Long>> resultDO=new ResultDO<>();
        List<Long> longList=clerkManager.getClerkId();
        resultDO.setSuccess(true);
        resultDO.setData(longList);
        return resultDO;
    }

    /**
     * 获取所有业务员信息
     *
     * @return
     */
    @Override
    public ResultDO<List<Clerk>> getAllClerkInfo() {
        ResultDO<List<Clerk>> resultDO = new ResultDO<List<Clerk>>();
        try {
            resultDO.setData(clerkManager.getAllClerkInfo());
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.DB_QUERY_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.DB_QUERY_EXCEPTION.getMsg());
        }
        return resultDO;
    }

    /**
     * 更新设备id
     *
     * @param clerkId
     * @param deviceId
     * @return
     */
    @Override
    public ResultDO updateDeviceId(Long clerkId, String deviceId) {
        ResultDO resultDO = new ResultDO();
        if (ValidateHelper.isEmpty(clerkId) || ValidateHelper.isEmpty(deviceId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Clerk clerk = clerkManager.getClerkInfoById(clerkId);
        if (clerk == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        Clerk t_clerk = new Clerk();
        t_clerk.setId(clerk.getId());
        t_clerk.setDeviceId(deviceId);
        t_clerk.setVersion(clerk.getVersion());
        clerkManager.updateByPrimaryKeySelective(t_clerk);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        return resultDO;
    }

    /**
     * 业务员退出登录
     *
     * @param clerkId
     * @return
     */
    @Override
    public ResultDO clerkLogout(Long clerkId, String token) {
        ResultDO resultDO = new ResultDO();
        if (clerkId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Clerk clerk = clerkManager.getClerkInfoById(clerkId);
        if (clerk == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        Clerk t_clerk = new Clerk();
        t_clerk.setId(clerk.getId());
        t_clerk.setDeviceId(null);
        t_clerk.setVersion(clerk.getVersion());
        clerkManager.updateByPrimaryKeySelective(t_clerk);
        RedisUtil.del("CLERK_" + token);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        return resultDO;
    }
}
