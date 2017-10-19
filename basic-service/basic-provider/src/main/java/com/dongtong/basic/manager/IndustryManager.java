package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseIndustryMapper;
import com.dongtong.basic.dto.IndustryInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 17:08
 **/
@Service
public class IndustryManager {
    @Autowired
    private BaseIndustryMapper baseIndustryMapper;

    public List<IndustryInfoDTO> queryIndustry(){
        return baseIndustryMapper.queryIndustry();
    }

    public List<IndustryInfoDTO> industryList(){
        return baseIndustryMapper.industryList();
    }
}
