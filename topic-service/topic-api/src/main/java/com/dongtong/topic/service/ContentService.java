package com.dongtong.topic.service;

import com.dongtong.topic.dto.req.ContentDetailReqDTO;
import com.dongtong.topic.dto.req.ContentGuideDetailReqDTO;
import com.dongtong.topic.dto.req.ContentReqDTO;
import com.dongtong.topic.dto.req.HotTagsReqDTO;
import com.dongtong.topic.dto.resp.*;
import com.dongtong.topic.query.ContentGuideListQuery;
import com.dongtong.topic.query.ContentListQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 内容服务
 * @package com.dongtong.topic.service
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/4 16:04
 * @version v1.2.0
 */
@Service
public interface ContentService {

    /**
     * @description 获取内容列表
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/4 16:11
     * @param contentListQuery
     * @return ResultDO<List<ContentListRespDTO>>
     */
    ResultDO<Page<ContentListRespDTO>> getContentList(ContentListQuery contentListQuery);

    /**
     * @description 获取内容详情
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/4 16:12
     * @param contentDetailReqDTO
     * @return ResultDO<ContentDetailRespDTO>
     */
    ResultDO<ContentDetailRespDTO> getContentDetail(ContentDetailReqDTO contentDetailReqDTO);

    /**
     * @description 获取热门标签
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/4 16:12
     * @param hotTagsReqDTO
     * @return ResultDO<List<String>>
     */
    ResultDO<List<HotTagsRespDTO>> getTagsList(HotTagsReqDTO hotTagsReqDTO);

    /**
     * @description 根据热门标签查询内容列表
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/7 0007 11:13
     * @param contentListQuery
     * @return ResultDO<Page<ContentListRespDTO>>
     */
    ResultDO<Page<ContentListRespDTO>> getContentListByTags(ContentListQuery contentListQuery);

    /**
     * @description 赞
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/7 0007 14:56
     * @param content
     * @return ResultDO
     */
    ResultDO contentGood(ContentReqDTO content);

    /**
     * @description 踩
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/7 0007 14:56
     * @param content
     * @return ResultDO
     */
    ResultDO contentBad(ContentReqDTO content);

    /**
     * @description 找旺铺首页发现旺铺导购接口
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/8 0008 10:30
     * @return ResultDO<HomePageGuideListRespDTO>
     */
    ResultDO<List<HomePageGuideListRespDTO>> getHomePageGuideData(ContentGuideListQuery contentGuideListQuery);

    /**
     * @description 
     * @package com.dongtong.topic.service
     * @company dongtong
     * @copyright Copyright (c) 2016
     * @author chenxs
     * @date 2017/8/8 0008 10:35
     * @version v1.0.0
     */
    ResultDO<Page<ContentGuideListRespDTO>> getContentGuideList(ContentGuideListQuery contentGuideListQuery);

    /**
     * @description 
     * @package com.dongtong.topic.service
     * @company dongtong
     * @copyright Copyright (c) 2016
     * @author chenxs
     * @date 2017/8/8 0008 10:36
     * @version v1.0.0
     */
    ResultDO<ContentGuideDetailRespDTO> getContentGuideDetail(ContentGuideDetailReqDTO contentGuideDetailReqDTO);
}
