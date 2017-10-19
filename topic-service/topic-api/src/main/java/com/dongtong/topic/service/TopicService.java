package com.dongtong.topic.service;

import com.dongtong.topic.dto.TopicDTO;
import com.dongtong.topic.dto.TopicDeleteDTO;
import com.dongtong.topic.dto.TopicDetailDTO;
import com.dongtong.topic.dto.TopicListDTO;
import com.dongtong.topic.query.MyTopicListQuery;
import com.dongtong.topic.query.TopicDetailQuery;
import com.dongtong.topic.query.TopicListQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.service
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 18:14
 */
@Service
public interface TopicService {

    /**
     * 发布帖子接口
     * @param topicDTO
     * @return
     */
    ResultDO publishTopic(TopicDTO topicDTO);

    /**
     * 根据topic获取详情
     * @param topicDetailQuery
     * @return
     */
    ResultDO<TopicDetailDTO> getTopicDetailByID(TopicDetailQuery topicDetailQuery);

    /**
     * 帖子置顶
     * @param topicDTO
     * @return
     */
    ResultDO stickTopic(TopicDTO topicDTO);

    /**
     * 取消帖子置顶
     * @param topicDTO
     * @return
     */
    ResultDO unStickTopic(TopicDTO topicDTO);

    /**
     * 删除帖子
     * @param topicDeleteDTO
     * @return
     */
    ResultDO deleteTopic(TopicDeleteDTO topicDeleteDTO);

    /**
     * 商业圈-生意圈列表接口
     * @param topicListQuery
     * @return
     */
    ResultDO<Page<TopicListDTO>> getTopicList(TopicListQuery topicListQuery);

    /**
     * 我的-帖子列表
     * @param myTopicListQuery
     * @return
     */
    ResultDO<Page<TopicListDTO>> getTopicListByUserId(MyTopicListQuery myTopicListQuery);

    /**
     * @description
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/16 0016 10:27
     * @return ResultDO<List<TopicListDTO>>
     */
    ResultDO<List<TopicListDTO>> getHomePageTopic(TopicListQuery topicListQuery);

    ResultDO<TopicDetailDTO> getTopicDetailByUserId(TopicDetailQuery topicDetailQuery);
}
