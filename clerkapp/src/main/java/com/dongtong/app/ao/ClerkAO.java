package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.clerk.dto.ClerkDetailDTO;
import com.dongtong.clerk.dto.UpdateClerkDetailDTO;
import com.dongtong.clerk.service.ClerkService;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.app.ao.ClerkAO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/16 10:27
 * version V1.0.0
 */
@Service
public class ClerkAO {
    @Autowired
    private ClerkService clerkService;

    /**
     * 我的--个人中心详情接口
     * @param
     * @return
     */
    public ResultDO<ClerkDetailDTO> getClerkDetailById(){
        Long clerkId = HttpSessionUtils.getCurrentAppUserId();
        return clerkService.getClerkDetailById(clerkId);
    }

    /**
     * 我的--基本信息添加接口
     * @param updateClerkDetailDTO
     * @return
     */
    public ResultDO updateClerkDetail(UpdateClerkDetailDTO updateClerkDetailDTO){
        Long clerkId = HttpSessionUtils.getCurrentAppUserId();
        updateClerkDetailDTO.setClerkId(clerkId);
        return clerkService.updateClerkDetail(updateClerkDetailDTO);
    }

    /**
     * 是否有新通知接口
     * @return
     */
    public ResultDO<Boolean> isNewNotification(){
        Long clerkId = HttpSessionUtils.getCurrentAppUserId();
        return clerkService.isNewNotification(clerkId);
    }

    /**
     *修改个人信息
     * add by xiehb
     * @return
     */
    public ResultDO<Boolean> updateClerkInfo(UpdateClerkDetailDTO updateClerkDetailDTO){
        Long clerkId = HttpSessionUtils.getCurrentAppUserId();
        updateClerkDetailDTO.setClerkId(clerkId);
        return clerkService.updateClerkInfo(updateClerkDetailDTO);
    }
}
