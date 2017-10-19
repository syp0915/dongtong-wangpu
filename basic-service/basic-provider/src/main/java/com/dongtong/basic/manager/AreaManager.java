package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseBlockMapper;
import com.dongtong.basic.dao.BaseDistrictMapper;
import com.dongtong.basic.domain.BaseBlock;
import com.dongtong.basic.domain.BaseDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * AreaManager
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-04 17:17
 **/
@Service
public class AreaManager {
    @Autowired(required = false)
    private BaseDistrictMapper baseDistrictMapper;
    @Autowired(required = false)
    private BaseBlockMapper baseBlockMapper;

    public List<BaseDistrict> selectByCityId(String cityId) {
        return baseDistrictMapper.selectByCityId(cityId);
    }

    public List<BaseBlock>  selectByDistrictId(long districtId) {
        return baseBlockMapper.selectByDistrictId(districtId);
    }

}
