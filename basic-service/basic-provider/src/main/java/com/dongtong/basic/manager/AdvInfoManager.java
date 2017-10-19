package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseAdvertisementMapper;
import com.dongtong.basic.dto.AdvInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 19:42
 **/
@Service
public class AdvInfoManager {
    @Autowired
    private BaseAdvertisementMapper baseAdvertisementMapper;

    public List<AdvInfoDTO> advList(String position){
        return baseAdvertisementMapper.advList(position);
    }

    public List<AdvInfoDTO> adList(String position,Integer type){
        return baseAdvertisementMapper.adList(position,type);
    }
}
