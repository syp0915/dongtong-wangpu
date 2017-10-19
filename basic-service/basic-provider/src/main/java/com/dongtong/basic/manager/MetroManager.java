package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseMetroMapper;
import com.dongtong.basic.dao.BaseMetroStationMapper;
import com.dongtong.basic.domain.BaseMetro;
import com.dongtong.basic.dto.MetroStationInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 13:33
 **/
@Service
public class MetroManager {
    @Autowired
    private BaseMetroMapper baseMetroMapper;
    @Autowired
    private BaseMetroStationMapper baseMetroStationMapper;


    public List<BaseMetro> selectByCityId(String cityId) {
        return baseMetroMapper.selectByCityId(cityId);
    }

    public List<MetroStationInfoDTO> selectByMetroId(long metroId) {
        return baseMetroStationMapper.selectByMetroId(metroId);
    }


}
