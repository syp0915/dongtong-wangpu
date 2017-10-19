package com.dongtong.basic.service;


import com.alibaba.fastjson.JSON;
import com.dongtong.basic.StringUtils;
import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dao.PicVerifyCodeMapper;
import com.dongtong.basic.dao.SmsVerifyCodeMapper;
import com.dongtong.basic.domain.PicVerifyCode;
import com.dongtong.basic.domain.SmsVerifyCode;
import com.dongtong.basic.dto.resp.PicVerifyRespDTO;
import com.dongtong.basic.dto.resp.SmsVerifyRespDTO;
import com.dongtong.basic.manager.SmsManager;
import com.dongtong.basic.util.BasicProperties;
import com.dongtong.basic.util.CacheUtils;
import com.dongtong.basic.util.VerifyCodeUtils;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.encrypt.Base64Utils;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 下午2:36.
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired(required = false)
    private SmsManager smsManager;

    @Autowired(required = false)
    private SmsVerifyCodeMapper smsVerifyCodeMapper;

    @Autowired(required = false)
    private PicVerifyCodeMapper picVerifyCodeMapper;

    @Autowired
    private BasicProperties basicProperties;
    //开启线程池
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
    /**
     * 发送短信验证码
     *
     * @param userPhone   手机号
     * @param useSence    0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺  7-修改手机号码
     * @return
     */
    @Override
    public ResultDO<SmsVerifyRespDTO> sendSmsVerifyCode(final String userPhone, Integer useSence) {
        ResultDO<SmsVerifyRespDTO> resultDO = new ResultDO<SmsVerifyRespDTO>();
        if (ValidateHelper.isEmpty(userPhone) || ValidateHelper.isEmpty(useSence)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }

        //当前手机号发送次数放入缓存
        Integer count = 0;
        Object object = RedisUtil.get("SMS_VERIFY_SEND_COUNT_" + userPhone);
        if (object != null){
            count = Integer.parseInt(object.toString());
            if (count >= 10){
                resultDO.setSuccess(false);
                resultDO.setErrCode(ErrorConstant.EXCEED_SMS_VERIFY_CODE_MAX_COUNT.getCode());
                resultDO.setErrMsg(ErrorConstant.EXCEED_SMS_VERIFY_CODE_MAX_COUNT.getMsg());
                return resultDO;
            }
        }

        String verifyCode = StringUtils.generateVerifyCode();
        Object verifyCodeObject = RedisUtil.get("SMS_VERIFY_CODE_" + userPhone);
        if (verifyCodeObject != null){
            verifyCode = verifyCodeObject.toString();
        }
        //通过异步方式发送短信验证码
        final String finalVerifyCode = verifyCode;
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                ResultDO remoteResult = smsManager.sendSmsVerifyCode(userPhone, finalVerifyCode);
                Logger.info(SmsServiceImpl.class, "发送短信验证码返回结果:"+ JSON.toJSONString(remoteResult));
            }
        });
        SmsVerifyCode smsVerifyCode = new SmsVerifyCode();
        smsVerifyCode.setUserPhone(userPhone);
        smsVerifyCode.setVerifyCode(verifyCode);
        smsVerifyCode.setStatus(0);
        smsVerifyCode.setUseScene(useSence);
        smsVerifyCodeMapper.insert(smsVerifyCode);

        /**
         * 今日已使用次数存入redis
         */
        count++;
        Long expiresSeconds = CacheUtils.getExpireSeconds();
        RedisUtil.set("SMS_VERIFY_SEND_COUNT_" + userPhone, count, expiresSeconds);
        RedisUtil.set("SMS_VERIFY_CODE_" + userPhone, verifyCode, expiresSeconds);

        SmsVerifyRespDTO smsVerifyRespDTO = new SmsVerifyRespDTO();
        smsVerifyRespDTO.setMessageId(smsVerifyCode.getId() + "");
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(smsVerifyRespDTO);
        return resultDO;
    }




    /**
     * 生成图片验证码
     *
     * @param useScene    使用场景 0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺  7-修改手机号码
     * @return
     */
    @Override
    public ResultDO<PicVerifyRespDTO> getVerifyPic(Integer useScene) {
        ResultDO<PicVerifyRespDTO> resultDO = new ResultDO<PicVerifyRespDTO>();
        String verifyCode = StringUtils.generateVerifyCode();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            VerifyCodeUtils.outputImage(100, 50, baos, verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String base64String = Base64Utils.encrypt(baos.toByteArray());

        PicVerifyCode picVerifyCode = new PicVerifyCode();
        picVerifyCode.setVerifyCode(verifyCode);
        picVerifyCode.setStatus(0);
        picVerifyCodeMapper.insert(picVerifyCode);

        RedisUtil.set("PIC_VERIFY_CODE_" + picVerifyCode.getId(), verifyCode, 10 * 60);

        PicVerifyRespDTO picVerifyRespDTO = new PicVerifyRespDTO();
        picVerifyRespDTO.setPicVerifyId(picVerifyCode.getId() + "");
        picVerifyRespDTO.setPicVerifyCode(verifyCode);
        picVerifyRespDTO.setBase64Str(base64String);

        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(picVerifyRespDTO);
        return resultDO;
    }

    /**
     * 验证短信验证码
     *
     * @param messageId
     * @param verifyCode
     * @return
     */
    @Override
    public ResultDO<Boolean> verifySmsVerifyCode(Long messageId, String verifyCode, String userPhone, Integer useScene) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if (ValidateHelper.isEmpty(messageId) || ValidateHelper.isEmpty(verifyCode) || ValidateHelper.isEmpty(userPhone) || ValidateHelper.isEmpty(useScene)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        
        //万能验证码,读取配置文件万能验证码开关
        if(("1").equals(basicProperties.getUniversalSmsCodeSwitch())){
            Long universalMessageId = 8888888888888L;
            if ("2333".equals(verifyCode) && messageId.longValue() == universalMessageId.longValue()){
                resultDO.setSuccess(true);
                resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
                resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
                resultDO.setData(true);
                return resultDO;
            }
        }
        SmsVerifyCode smsVerifyCode = smsVerifyCodeMapper.selectByPrimaryKey(messageId);
        if (smsVerifyCode == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UNEXIST_SMS_VERIFY_CODE.getCode());
            resultDO.setErrMsg(ErrorConstant.UNEXIST_SMS_VERIFY_CODE.getMsg());
            return resultDO;
        }
        //十分钟过期
        if (System.currentTimeMillis() - smsVerifyCode.getCreateTime().getTime() > 10 * 60 * 1000){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.SMS_VERIFY_CODE_EXPIRE.getCode());
            resultDO.setErrMsg(ErrorConstant.SMS_VERIFY_CODE_EXPIRE.getMsg());
            return resultDO;
        }
        if (smsVerifyCode.getUseScene() != useScene){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.SMS_ERROR_USE_SCENE.getCode());
            resultDO.setErrMsg(ErrorConstant.SMS_ERROR_USE_SCENE.getMsg());
            return resultDO;
        }
        if (smsVerifyCode.getStatus() != 0){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CODE_HAS_VERIFIED.getCode());
            resultDO.setErrMsg(ErrorConstant.CODE_HAS_VERIFIED.getMsg());
            return resultDO;
        }
        if (!verifyCode.equals(smsVerifyCode.getVerifyCode())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.ERROR_SMS_VERIFY_CODE.getCode());
            resultDO.setErrMsg(ErrorConstant.ERROR_SMS_VERIFY_CODE.getMsg());
            return resultDO;
        }
        if (!userPhone.equals(smsVerifyCode.getUserPhone())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.VERIFY_NOT_MATCH_PHONE.getCode());
            resultDO.setErrMsg(ErrorConstant.VERIFY_NOT_MATCH_PHONE.getMsg());
            return resultDO;
        }
        smsVerifyCode.setStatus(1);
        smsVerifyCodeMapper.updateByPrimaryKeySelective(smsVerifyCode);
        RedisUtil.del("SMS_VERIFY_CODE_" + messageId);
        RedisUtil.del("SMS_VERIFY_CODE_" + userPhone);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(true);
        return resultDO;
    }

    /**
     * 验证图片验证码
     *
     * @param picVerifyId
     * @param verifyCode
     * @return
     */
    @Override
    public ResultDO<Boolean> verifyPicVerify(Long picVerifyId, String verifyCode) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if (ValidateHelper.isEmpty(picVerifyId) || ValidateHelper.isEmpty(verifyCode)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Object object = RedisUtil.get("PIC_VERIFY_CODE_" + picVerifyId);
        if (object != null && verifyCode.equals(object.toString())){
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }
        PicVerifyCode picVerifyCode = picVerifyCodeMapper.selectByPrimaryKey(picVerifyId);
        if (picVerifyCode == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UNEXIST_PIC_VERIFY_CODE.getCode());
            resultDO.setErrMsg(ErrorConstant.UNEXIST_PIC_VERIFY_CODE.getMsg());
            return resultDO;
        }
        //十分钟过期
        if (System.currentTimeMillis() - picVerifyCode.getCreateTime().getTime() > 10 * 60 * 1000){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.PIC_VERIFY_CODE_EXPIRE.getCode());
            resultDO.setErrMsg(ErrorConstant.PIC_VERIFY_CODE_EXPIRE.getMsg());
            return resultDO;
        }
        if (picVerifyCode.getStatus() == 1){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CODE_HAS_VERIFIED.getCode());
            resultDO.setErrMsg(ErrorConstant.CODE_HAS_VERIFIED.getMsg());
            return resultDO;
        }
        if (!verifyCode.equals(picVerifyCode.getVerifyCode())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.ERROR_PIC_VERIFY_CODE.getCode());
            resultDO.setErrMsg(ErrorConstant.ERROR_PIC_VERIFY_CODE.getMsg());
            return resultDO;
        }
        picVerifyCode.setStatus(1);
        picVerifyCodeMapper.updateByPrimaryKeySelective(picVerifyCode);
        RedisUtil.del("PIC_VERIFY_CODE_" + picVerifyId);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(true);
        return resultDO;
    }


}
