package com.dongtong.topic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.dto.TopicCommentDTO;
import com.dongtong.topic.dto.TopicCommentDetailDTO;
import com.dongtong.topic.enums.UserType;
import com.dongtong.topic.query.TopicCommentDetailQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.service
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 16:09
 */
public class TopicCommentServiceTest extends JunitBaseTest {

    @Autowired
    private TopicCommentService topicCommentService;

    /**
     * @description 发布评论
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:16
     */
    @Test
    public void publishComment(){
        TopicCommentDTO topicCommentDTO = new TopicCommentDTO();
        topicCommentDTO.setTopicId(412L);
        topicCommentDTO.setContent("哇咔咔");
        topicCommentDTO.setOperator(105L);
        topicCommentDTO.setIsReply(2);
        topicCommentDTO.setParentId(1111300L);
        topicCommentDTO.setUserType(UserType.MERCHANT.getValue());
        ResultDO<TopicCommentDetailDTO> result = topicCommentService.publishComment(topicCommentDTO);
        Logger.info(TopicCommentServiceTest.class,"评论帖子返回结果："+result);
    }

    /**
     * @description 运营删除评论
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:16
     */
    @Test
    public void testDeleteComment0(){
        TopicCommentDTO topicCommentDTO = new TopicCommentDTO();
        topicCommentDTO.setCommentId(1111131L);
        topicCommentDTO.setUserType(UserType.MERCHANT.getValue());
        topicCommentDTO.setOperator(3L);
        ResultDO result = topicCommentService.deleteComment(topicCommentDTO);
        Logger.info(TopicCommentServiceTest.class,"删除评论返回结果："+result);
    }

    /**
     * @description 商户删除评论
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:16
     */
    @Test
    public void testDeleteComment1(){
        TopicCommentDTO topicCommentDTO = new TopicCommentDTO();
        topicCommentDTO.setCommentId(6L);
        topicCommentDTO.setUserType(UserType.MERCHANT.getValue());
        topicCommentDTO.setOperator(1L);
        ResultDO result = topicCommentService.deleteComment(topicCommentDTO);
        Logger.info(TopicCommentServiceTest.class,"删除评论返回结果："+result);
    }

    /**
     * @description 缺失必填参数删除评论
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:16
     */
    @Test
    public void testDeleteCommentNoParam(){
        TopicCommentDTO topicCommentDTO = new TopicCommentDTO();
        ResultDO result = topicCommentService.deleteComment(topicCommentDTO);
        Logger.info(TopicCommentServiceTest.class,"删除评论返回结果："+result);
    }

    /**
     * @description
     * @package com.dongtong.topic.service
     * @company dongtong
     * @copyright Copyright (c) 2016
     * @author chenxs
     * @date 2017/5/23 11:27
     * @version v1.0.0
     */
    @Test
    public void testGetCommentList(){
        TopicCommentDetailQuery topicCommentDetailQuery = new TopicCommentDetailQuery();
        topicCommentDetailQuery.setPageNumber(1);
        topicCommentDetailQuery.setPageSize(20);
        topicCommentDetailQuery.setTopicId(134L);
        ResultDO<Page<TopicCommentDetailDTO>> result = topicCommentService.getCommentList(topicCommentDetailQuery);
        Logger.info(TopicCommentServiceTest.class,"获取评论列表返回结果："+ JSON.toJSON(result.getData().getData()));
    }
}
