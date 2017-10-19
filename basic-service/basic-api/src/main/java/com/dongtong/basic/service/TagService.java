package com.dongtong.basic.service;

import com.dongtong.basic.dto.TagInfoDTO;
import com.dongtong.basic.query.TagQuery;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 18:47
 **/
public interface TagService {
    /**
     * 根据类型查询标签
     *
     * @param query
     * @return
     */
    ResultDO<List<TagInfoDTO>> queryTag(TagQuery query);
    /**
     * 根据标签Id查询标签
     *
     * @param tagIds
     * @return
     */
    ResultDO<List<String>> queryTagById(List<Long> tagIds);
}
