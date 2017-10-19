package com.dongtong.topic.service;

import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.dto.req.ContentDetailReqDTO;
import com.dongtong.topic.dto.req.ContentGuideDetailReqDTO;
import com.dongtong.topic.dto.resp.ContentDetailRespDTO;
import com.dongtong.topic.dto.resp.ContentGuideDetailRespDTO;
import com.dongtong.topic.dto.resp.ContentListRespDTO;
import com.dongtong.topic.query.ContentListQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @package com.dongtong.topic.service
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/4 17:05
 * @version v1.0.0
 */
public class ContentServiceTest extends JunitBaseTest {

    @Autowired
    private ContentService contentService;

    @Test
    public void testGetContentList(){
        ContentListQuery contentListQuery = new ContentListQuery();
        contentListQuery.setContentType(1);
        contentListQuery.setPageNumber(1);
        contentListQuery.setPageSize(10);
        ResultDO<Page<ContentListRespDTO>> resultDO = contentService.getContentList(contentListQuery);

        Logger.info(this.getClass(),"接口返回的内容：" + resultDO.getData().getData());
    }

    @Test
    public void testGetContentDetail(){
        ContentDetailReqDTO contentDetailReqDTO = new ContentDetailReqDTO();
        contentDetailReqDTO.setContentId(1L);
        ResultDO<ContentDetailRespDTO> resultDO = contentService.getContentDetail(contentDetailReqDTO);

        Logger.info(this.getClass(),"接口返回的内容：" + resultDO.getData());
    }

    @Test
    public void testGetContentGuideDetail(){
        ContentDetailReqDTO contentDetailReqDTO = new ContentDetailReqDTO();
        contentDetailReqDTO.setContentId(1L);

        ContentGuideDetailReqDTO contentGuideDetailReqDTO = new ContentGuideDetailReqDTO();
        contentGuideDetailReqDTO.setContentId(42L);
        ResultDO<ContentGuideDetailRespDTO> resultDO = contentService.getContentGuideDetail(contentGuideDetailReqDTO);

        Logger.info(this.getClass(),"接口返回的内容：" + resultDO.getData());
    }
}
