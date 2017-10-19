package com.dongtong.topic.service;

import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.dto.TopicDTO;
import com.dongtong.topic.dto.TopicDeleteDTO;
import com.dongtong.topic.dto.TopicDetailDTO;
import com.dongtong.topic.dto.TopicListDTO;
import com.dongtong.topic.enums.PubishType;
import com.dongtong.topic.enums.UserType;
import com.dongtong.topic.query.MyTopicListQuery;
import com.dongtong.topic.query.TopicDetailQuery;
import com.dongtong.topic.query.TopicListQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.service
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/10 18:50
 */
public class TopicServiceTest extends JunitBaseTest {

    @Autowired
    private TopicService topicService;

    /**
     * @description 运营发布咨询
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:23
     */
    @Test
    public void testPublishTopic0(){
        TopicDTO topic = new TopicDTO();
        List<String> images = new ArrayList<String>();
        images.add("http://alyenc-docker.oss-cn-hongkong.aliyuncs.com/wordpress/wp-content/themes/owl-master/images/emoji/1f601.png");
        topic.setPublishType(PubishType.TOPIC.getValue());
        topic.setTitle("66");
        topic.setUserType(UserType.MERCHANT.getValue());
//        topic.setImageList(images);
        topic.setOperator(1000L);
        topic.setHyperlinkUrl("sdasdasdasd");
        ResultDO result = topicService.publishTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }

    /**
     * @description 运营发布帖子
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:29
     */
    @Test
    public void testPublishTopic1(){
        TopicDTO topic = new TopicDTO();
        List<String> images = new ArrayList<String>();
        images.add("http://alyenc-docker.oss-cn-hongkong.aliyuncs.com/wordpress/wp-content/themes/owl-master/images/emoji/1f601.png");
        topic.setContent("老铁双击6666");
        topic.setUserType(UserType.MERCHANT.getValue());
        topic.setPublishType(PubishType.TOPIC.getValue());
        topic.setTitle("上海美食");
        topic.setImageList(images);
        topic.setOperator(1000L);
        ResultDO result = topicService.publishTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }

    /**
     * @description 商户发布资讯
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:30
     */
    @Test
    public void testPublishTopic2(){
        TopicDTO topic = new TopicDTO();
        List<String> images = new ArrayList<String>();
        images.add("http://alyenc-docker.oss-cn-hongkong.aliyuncs.com/wordpress/wp-content/themes/owl-master/images/emoji/1f601.png");
        topic.setContent("老铁双击6666");
        topic.setUserType(UserType.MERCHANT.getValue());
        topic.setPublishType(PubishType.PICCONTENT.getValue());
        topic.setTitle("上海美食");
        topic.setImageList(images);
        topic.setOperator(1000L);
        ResultDO result = topicService.publishTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }

    /**
     * @description 商户发布帖子
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:30
     */
    @Test
    public void testPublishTopic3(){
        TopicDTO topic = new TopicDTO();
        List<String> images = new ArrayList<String>();
        images.add("http://alyenc-docker.oss-cn-hongkong.aliyuncs.com/wordpress/wp-content/themes/owl-master/images/emoji/1f601.png");
        topic.setUserType(UserType.MERCHANT.getValue());
        topic.setPublishType(PubishType.TOPIC.getValue());
        topic.setTitle("上海美食");
        topic.setImageList(images);
        topic.setOperator(1000L);
        ResultDO result = topicService.publishTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }

    /**
     * @description 发布没有图片的帖子
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:30
     */
    @Test
    public void testPublishTopicNoImage(){
        TopicDTO topic = new TopicDTO();
        topic.setContent("这个是没有图片的帖子");
        topic.setUserType(UserType.MERCHANT.getValue());
        topic.setPublishType(PubishType.TOPIC.getValue());
        topic.setTitle("这个是业务员发的帖子");
        topic.setOperator(1000L);
        ResultDO result = topicService.publishTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }

    /**
     * @description 商户别人帖子删除帖子--失败
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:31
     */
    @Test
    public void testDeleteTopic0(){
        TopicDeleteDTO topicDeleteDTO = new TopicDeleteDTO();
        topicDeleteDTO.setUserType(UserType.MERCHANT.getValue());
        topicDeleteDTO.setTopicId(389L);
        topicDeleteDTO.setOperator(1000L);
        ResultDO result = topicService.deleteTopic(topicDeleteDTO);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }

    /**
     * @description 运营删除帖子
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:31
     */
    @Test
    public void testDeleteTopic1() {
        TopicDeleteDTO topicDeleteDTO = new TopicDeleteDTO();
        topicDeleteDTO.setUserType(UserType.CLERK.getValue());
        topicDeleteDTO.setTopicId(150L);
        topicDeleteDTO.setOperator(12L);
        ResultDO result = topicService.deleteTopic(topicDeleteDTO);
        Logger.info(this.getClass(), "接口返回的报文：" + result);
    }

    /**
     * @description 运营帖子置顶
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:34
     */
    @Test
    public void testStickTopic0(){
        TopicDTO topic  = new TopicDTO();
        topic.setTopicId(10L);
        topic.setOperator(1L);
        topic.setUserType(UserType.CLERK.getValue());
        ResultDO resultDO = topicService.stickTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+resultDO);
    }

    /**
     * @description 商户帖子置顶--失败
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:34
     */
    @Test
    public void testStickTopic1(){
        TopicDTO topic  = new TopicDTO();
        topic.setTopicId(10L);
        topic.setOperator(1L);
        topic.setUserType(UserType.MERCHANT.getValue());
        ResultDO resultDO = topicService.stickTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+resultDO);
    }

    /**
     * @description 运营取消帖子置顶
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:34
     */
    @Test
    public void testUnStickTopic0(){
        TopicDTO topic  = new TopicDTO();
        topic.setTopicId(217L);
        topic.setOperator(13L);
        topic.setUserType(UserType.MERCHANT.getValue());
        ResultDO resultDO = topicService.unStickTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+resultDO);
    }

    /**
     * @description 商户取消帖子置顶--失败
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:34
     */
    @Test
    public void testUnStickTopic1(){
        TopicDTO topic  = new TopicDTO();
        topic.setTopicId(10L);
        topic.setOperator(1L);
        topic.setUserType(UserType.MERCHANT.getValue());
        ResultDO resultDO = topicService.unStickTopic(topic);
        Logger.info(this.getClass(),"接口返回的报文："+resultDO);
    }

    @Test
    public void testGetTopicDetailByID(){
        TopicDetailQuery topicDetailQuery = new TopicDetailQuery();

        topicDetailQuery.setTopicId(387L);
        topicDetailQuery.setUserId(3L);
        topicDetailQuery.setUserType(UserType.MERCHANT.getValue());
        ResultDO<TopicDetailDTO> result = topicService.getTopicDetailByID(topicDetailQuery);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }

    @Test
    public void testGetTopicDetailByID2(){
        TopicDetailQuery topicDetailQuery = new TopicDetailQuery();
        topicDetailQuery.setTopicId(304L);
        topicDetailQuery.setUserId(3L);
        topicDetailQuery.setUserType(UserType.CLERK.getValue());
        ResultDO<TopicDetailDTO> result = topicService.getTopicDetailByID(topicDetailQuery);
        Logger.info(this.getClass(),"接口返回的报文："+result.getData());
    }

    @Test
    public void testGetTopicDetailByID3(){//测试是商户发的帖子
        TopicDetailQuery topicDetailQuery = new TopicDetailQuery();
        topicDetailQuery.setTopicId(6L);
        topicDetailQuery.setUserId(1L);
        topicDetailQuery.setUserType(UserType.CLERK.getValue());
        ResultDO<TopicDetailDTO> result = topicService.getTopicDetailByID(topicDetailQuery);
        Logger.info(this.getClass(),"接口返回的报文："+result.getData().getTitle());
    }

    @Test
    public void testGetTopicListByUserId(){//测试我的帖子列表
        MyTopicListQuery myTopicListQuery = new MyTopicListQuery();
        myTopicListQuery.setPageNumber(1);
        myTopicListQuery.setPageSize(20);
        myTopicListQuery.setUserId(32L);
        ResultDO<Page<TopicListDTO>> result = topicService.getTopicListByUserId(myTopicListQuery);
        Logger.info(this.getClass(),"接口返回的报文："+result.getData().getData());
    }

    @Test
    public void testGetTopicListByUserId2(){//测试我的帖子列表
        MyTopicListQuery myTopicListQuery = new MyTopicListQuery();
        myTopicListQuery.setPageNumber(1);
        myTopicListQuery.setPageSize(20);
        ResultDO<Page<TopicListDTO>> result = topicService.getTopicListByUserId(myTopicListQuery);
        Logger.info(this.getClass(),"接口返回的报文："+ result.isSuccess()+" --------"+result.getErrMsg());
    }

    @Test
    public void testGetTopicList(){//测试我的帖子列表
        TopicListQuery topicListQuery = new TopicListQuery();
        topicListQuery.setPageNumber(2);
        topicListQuery.setPageSize(10);
        topicListQuery.setType(0);
        topicListQuery.setUserType(UserType.MERCHANT.getValue());
        topicListQuery.setUserId(3L);
        ResultDO<Page<TopicListDTO>> result = topicService.getTopicList(topicListQuery);
        Logger.info(this.getClass(),"接口返回的报文："+ result.getData().getData());
    }

    @Test
    public void testGetTopicList2(){//测试我的帖子列表
        TopicListQuery topicListQuery = new TopicListQuery();
        topicListQuery.setPageNumber(1);
        topicListQuery.setPageSize(20);
        topicListQuery.setType(1);
        ResultDO<Page<TopicListDTO>> result = topicService.getTopicList(topicListQuery);
        Logger.info(this.getClass(),"接口返回的报文："+ result.isSuccess()+" --------"+result.getErrMsg());
    }
}
