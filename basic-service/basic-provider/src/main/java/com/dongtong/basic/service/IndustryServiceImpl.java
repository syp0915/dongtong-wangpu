package com.dongtong.basic.service;

import com.dongtong.basic.dto.IndustryInfoDTO;
import com.dongtong.basic.manager.IndustryManager;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 16:38
 **/
@Service
public class IndustryServiceImpl implements IndustryService {
    @Autowired
    private IndustryManager industryManager;
    @Override
    public ResultDO<List<IndustryInfoDTO>> queryIndustry() {
        ResultDO<List<IndustryInfoDTO>> resultDO=new ResultDO<List<IndustryInfoDTO>>();

        List<IndustryInfoDTO> list=industryManager.queryIndustry();
        resultDO.setData(list);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<List<IndustryInfoDTO>> industryList() {
        ResultDO<List<IndustryInfoDTO>> resultDO=new ResultDO<List<IndustryInfoDTO>>();
        List<IndustryInfoDTO> list=industryManager.industryList();
        resultDO.setData(list);
        resultDO.setSuccess(true);
        return resultDO;
    }
}
