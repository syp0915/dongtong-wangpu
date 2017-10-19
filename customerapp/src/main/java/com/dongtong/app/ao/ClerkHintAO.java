package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.enums.SmsUseSceneType;
import com.dongtong.basic.service.SmsService;
import com.dongtong.clerk.dto.ClerkHintDTO;
import com.dongtong.clerk.dto.resp.ReleaseTotalRespDTO;
import com.dongtong.clerk.service.ClerkHintService;
import com.dongtong.customer.dto.CustomerDTO;
import com.dongtong.customer.dto.CustomerScheduleDTO;
import com.dongtong.customer.enums.ScheduleStatus;
import com.dongtong.customer.enums.ScheduleType;
import com.dongtong.customer.service.CustomerInfoService;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sunyaping
 * @Package com.dongtong.app.ao
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-12 17:42
 * version V1.0.0
 **/
@Service
public class ClerkHintAO {

    @Autowired(required = false)
    private ClerkHintService clerkHintService;

    @Autowired(required = false)
    private SmsService smsService;

    @Autowired(required = false)
    private CustomerInfoService customerInfoService;

    @Autowired(required = false)
    private ScheduleService scheduleService;

    /**
     * 金铺寻租（添加一条线索）
     * @param clerkHintDTO
     * @return
     */
    public ResultDO<Long> issueClue(ClerkHintDTO clerkHintDTO){
        ResultDO resultDO=new ResultDO();
        Long customerId= HttpSessionUtils.getCurrentAppUserId();
        if(clerkHintDTO==null){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        //手机号码正则校验
        String phone = clerkHintDTO.getLinkmanPhone();
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
        String checkResult=verifyCheckCode(clerkHintDTO.getLinkmanPhone(),customerDTOResultDO.getData().getPhone(),
                clerkHintDTO.getSmsVerifyCode(),clerkHintDTO.getMessageId(),clerkHintDTO.getPicVerifyCode(),
                clerkHintDTO.getPicVerifyId(),SmsUseSceneType.APPLY_FIND_RECENT.getValue());
        if(!ValidateHelper.isEmpty(checkResult)){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        clerkHintDTO.setIssuerPhone(customerDTOResultDO.getData().getPhone());
        clerkHintDTO.setIssuerId(customerId);
//        clerkHintDTO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        clerkHintDTO.setCreateTime(new Date());
        //添加一条线索
        ResultDO<Long> longResultDO=clerkHintService.issueClue(clerkHintDTO);
        if(!longResultDO.isSuccess()){
            resultDO.setErrMsg(longResultDO.getErrMsg());
            return resultDO;
        }
        //在我的日程中产生一条日程
        /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        CustomerScheduleDTO customerScheduleDTO = new CustomerScheduleDTO();
        customerScheduleDTO.setCreater(clerkHintDTO.getIssuerId());
        customerScheduleDTO.setCreateTime(new Date());
        customerScheduleDTO.setClerkId(clerkHintDTO.getOwnerId());
        customerScheduleDTO.setDistrict(clerkHintDTO.getDistrictName());
        customerScheduleDTO.setAddress(clerkHintDTO.getShopAddress());
        customerScheduleDTO.setCustomerId(customerId);
        customerScheduleDTO.setBizId(longResultDO.getData());
//        customerScheduleDTO.setType(ScheduleType.SHOP_FOR.getValue());  //旺铺寻租
        customerScheduleDTO.setStatus(ScheduleStatus.ACCEPTING.getValue());
       try {
           customerScheduleDTO.setApplicationTime(sdf.parse(clerkHintDTO.getCreateTime()));
       }catch (ParseException e){
           resultDO.setErrMsg("通知时间日期格式转化错误");
           return resultDO;
       }
        try {
          customerScheduleDTO.setMeetTime(sdf.parse(clerkHintDTO.getSubscribeTime()));
        }catch (ParseException e){
            resultDO.setErrMsg("约见时间日期格式转化错误");
            return resultDO;
        }

        ResultDO<Long> schedule=scheduleService.addSchedule(customerScheduleDTO);
        if(!longResultDO.isSuccess()){
            resultDO.setErrMsg(schedule.getErrMsg());
            return resultDO;
        }*/
        resultDO.setData(longResultDO.getData());
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * 旺铺寻租统计人数（即使用此项服务发布线索的人数）
     * @return
     */
    public ResultDO<ReleaseTotalRespDTO> getReleaseTotal(){
        return clerkHintService.releaseTotal();
    }

    /**
     * 验证图片和短信验证码
     * @param
     * @param newPhone
     * @return
     */
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

}
