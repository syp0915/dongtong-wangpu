package com.dongtong.basic.service;

import com.dongtong.basic.dto.RegionInfoDTO;
import com.dongtong.basic.query.AreaQuery;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-04 16:56
 **/
public interface AreaService {
    /**
     * @Description: 区域板块信息
     * @Title queryArea
     * @Author  wuky
     * @Date 2017/5/4 17:17
     * @param  query
     * @return ResultDO<List<AreaDTO>>
     * @throws
     */
    ResultDO<List<RegionInfoDTO>> qyeryArea(AreaQuery query);
}
