package com.dongtong.clerk.service;

import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.dto.ClerkDetailDTO;
import com.dongtong.clerk.dto.UpdateClerkDetailDTO;
import com.dongtong.clerk.dto.resp.LoginRespDTO;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 下午3:19.
 */
public interface ClerkService {

    /**
     * 根据id查询业务员信息
     * @param clerkId
     * @return
     */
    public ResultDO<Clerk> getClerkInfoById(Long clerkId);

    /**
     * 我的--个人中心详情接口
     * @param clerkId
     * @return
     */
    ResultDO<ClerkDetailDTO> getClerkDetailById(Long clerkId);


    /**
     * 我的--基本信息添加接口
     * @param updateClerkDetailDTO
     * @return
     */
    ResultDO updateClerkDetail(UpdateClerkDetailDTO updateClerkDetailDTO);

    /**
     * 个人信息修改
     * add xiehb
     * @return
     */
    ResultDO<Boolean> updateClerkInfo(UpdateClerkDetailDTO updateClerkDetailDTO);
    /**
     * 是否有新通知接口
     * @return
     */
    ResultDO<Boolean> isNewNotification(Long clerkId);

    /**
     * 通过手机号查找业务员
     * @param userPhone
     * @return
     */
    ResultDO<Clerk> getClerkInfoByUserPhone(String userPhone);

    /**
     * 用户登录
     * @param userPhone 登录手机号
     * @param smsVerifyCode 短信验证码
     * @param picVerifyCode 图片验证码
     * @param messageId 短信验证码消息id
     * @param inviteCode 邀请码
     * @return
     */
    ResultDO<LoginRespDTO> clerkLogin(String userPhone, String smsVerifyCode, Long messageId, String picVerifyCode, Long picVerifyId, String inviteCode, Integer osType, String deviceId);

    /**
     * 查询所有业务员Id
     * @return
     */
    ResultDO<List<Long>> getAllClerkIds();

    /**
     * 获取所有业务员信息
     * @return
     */
    ResultDO<List<Clerk>> getAllClerkInfo();

    /**
     * 更新设备id
     * @param clerkId
     * @param deviceId
     * @return
     */
    ResultDO updateDeviceId(Long clerkId, String deviceId);

    /**
     * 业务员退出登录
     * @param clerkId
     * @return
     */
    ResultDO clerkLogout(Long clerkId, String token);
}
