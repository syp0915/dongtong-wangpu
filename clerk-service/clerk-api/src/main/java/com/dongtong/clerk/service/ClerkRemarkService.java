package com.dongtong.clerk.service;

import com.dongtong.clerk.dto.ClerkRemarkDTO;
import com.shfc.common.result.ResultDO;

/**
 * @Package com.dongtong.clerk.service.ClerkRemarkService
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/8 9:48
 * version V1.0.0
 */
public interface ClerkRemarkService {

    /**
     * @description 新增备注
     * @package com.dongtong.clerk.service
     * @author liaozm
     * @date 2017/8/8 10:01
     * @params
     * @return
     */
    ResultDO<Boolean> addRemark(ClerkRemarkDTO clerkRemarkDTO);
}
