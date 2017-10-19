package com.dongtong.basic.service;

import com.dongtong.basic.dto.IndustryInfoDTO;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 16:36
 **/
public interface IndustryService {
    /**
     * @Description: 业态经营范围查询
     * @Title queryIndustry
     * @Author  wuky
     * @Date 2017/5/5 17:17
     * @param
     * @return ResultDO<List<IndustryInfoDTO>>
     * @throws
     */
    ResultDO<List<IndustryInfoDTO>> queryIndustry();


    /**
     * @Description: 业态经营范围查询
     * @Title queryIndustry
     * @Author  wuky
     * @Date 2017/5/5 17:17
     * @param
     * @return ResultDO<List<IndustryInfoDTO>>
     * @throws
     */
    ResultDO<List<IndustryInfoDTO>> industryList();
}
