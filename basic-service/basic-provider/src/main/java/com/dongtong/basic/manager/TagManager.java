package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseTagMapper;
import com.dongtong.basic.dto.TagInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 19:10
 **/
@Service
public class TagManager {
    @Autowired
    private BaseTagMapper baseTagMapper;

    public List<TagInfoDTO> queryTagByType(Long type){
        return baseTagMapper.queryTagByType(type);
    }


    public List<String> queryTagById(List<Long> tagIds){
        return baseTagMapper.queryTagById(tagIds);
    }
}
