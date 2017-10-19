package com.dongtong.basic.service;

import com.dongtong.basic.dto.resp.AliyunSTSRespDTO;
import com.shfc.common.result.ResultDO;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/25 下午4:32.
 */
public interface AliyunService {


    /**
     * 获取阿里云oss以sts方式访问的token
     * @return
     */
    ResultDO<AliyunSTSRespDTO> getTmpAccessInfo(String platRoleName, String osType, Long userId);
}
