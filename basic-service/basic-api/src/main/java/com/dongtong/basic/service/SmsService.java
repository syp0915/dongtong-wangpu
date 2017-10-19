package com.dongtong.basic.service;

import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.dto.resp.PicVerifyRespDTO;
import com.dongtong.basic.dto.resp.SmsVerifyRespDTO;
import com.shfc.common.result.ResultDO;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 下午2:12.
 */
public interface SmsService {

    /**
     * 发送短信验证码
     * @param userPhone 手机号
     * @param useSence 0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
     * @return
     */
    public ResultDO<SmsVerifyRespDTO> sendSmsVerifyCode(String userPhone, Integer useSence);

    /**
     * 发送图片验证码
     * @param useScene 使用场景 0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
     * @return
     */
    public ResultDO<PicVerifyRespDTO> getVerifyPic(Integer useScene);

    /**
     * 验证短信验证码
     * @param messageId
     * @param verifyCode
     * @return
     */
    public ResultDO<Boolean> verifySmsVerifyCode(Long messageId, String verifyCode, String userPhone, Integer useScene);

    /**
     * 验证图片验证码
     * @param picVerifyId
     * @param verifyCode
     * @return
     */
    public ResultDO<Boolean> verifyPicVerify(Long picVerifyId, String verifyCode);


}
