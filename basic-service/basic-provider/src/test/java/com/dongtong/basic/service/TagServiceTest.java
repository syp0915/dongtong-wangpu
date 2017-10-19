package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.TagInfoDTO;
import com.dongtong.basic.query.TagQuery;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 19:21
 **/
public class TagServiceTest extends JunitBaseTest {
    @Autowired
    private TagService tagService;

    @Test
    public void testQueryTag(){
        TagQuery query=new TagQuery();
        query.setTagType(1L);
        ResultDO<List<TagInfoDTO>> resultDO=tagService.queryTag(query);
        System.out.println(JSON.toJSONString(resultDO));

    }

    @Test
    public void testQueryTagById(){
        List<Long> tagIds =new ArrayList<>();
        tagIds.add(1L);
        tagIds.add(2L);
        tagIds.add(10L);
        ResultDO<List<String>> resultDO=tagService.queryTagById(tagIds);
        System.out.println(resultDO);

    }

}
