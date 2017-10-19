package com.dongtong.basic.service;

import com.dongtong.basic.dto.resp.AppUpdateRespDTO;
import com.shfc.common.result.ResultDO;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/6/22 下午3:45.
 */
public interface AppService {

    /**
     * 检查app更新
     * @param osType
     * @param version
     * @return
     */
    public ResultDO<AppUpdateRespDTO> queryAppUpdate(Integer type, Integer osType, String version);
}
