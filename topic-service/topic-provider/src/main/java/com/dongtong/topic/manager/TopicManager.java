package com.dongtong.topic.manager;

import com.dongtong.basic.domain.BaseArticleInformation;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.service.ArticleInformationService;
import com.dongtong.basic.service.NotificationService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.dto.CustomerInfoDTO;
import com.dongtong.customer.dto.req.AttentionVocationReqDTO;
import com.dongtong.customer.service.CustomerInfoService;
import com.dongtong.topic.dao.PretendUserMapper;
import com.dongtong.topic.dao.TopicImgRelMapper;
import com.dongtong.topic.dao.TopicMapper;
import com.dongtong.topic.domain.PretendUser;
import com.dongtong.topic.domain.Topic;
import com.dongtong.topic.domain.TopicImgRel;
import com.dongtong.topic.domain.TopicList;
import com.dongtong.topic.dto.*;
import com.dongtong.topic.enums.PubishType;
import com.dongtong.topic.enums.TopicErrorCode;
import com.dongtong.topic.enums.UserType;
import com.dongtong.topic.enums.YesNo;
import com.dongtong.topic.query.MyTopicListQuery;
import com.dongtong.topic.query.TopicDetailQuery;
import com.dongtong.topic.query.TopicListQuery;
import com.dongtong.topic.utils.HtmlUtils;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.*;

/**
 * @description 生意圈
 * @package com.dongtong.topic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/9 10:55
 * @version v1.0.0
 */
@Service
public class TopicManager {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicLikeManager topicLikeManager;

    @Autowired
    private TopicCommentManager topicCommentManager;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private TopicImgRelMapper topicImgRelMapper;
    @Autowired
    private ClerkService clerkService;

    @Autowired
    private ArticleInformationService articleInformationService;

    @Autowired
    private UserInfoManager userInfoManager;

    @Autowired
    private NotificationService notificationService;

    /**
     * @description 发布帖子
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:55
     * @params topicDTO
     * @return Long
     */
    public Long publishTopic(TopicDTO topicDTO){
        //组装数据库参数
        Topic topic = new Topic();
        topic.setPublisherId(topicDTO.getOperator());
        topic.setContent(topicDTO.getContent());
        topic.setHyperlinkUrl(topicDTO.getHyperlinkUrl());
        topic.setKind(YesNo.NO.getValue());
        topic.setPublisherType(topicDTO.getUserType());
        topic.setPublishType(topicDTO.getPublishType());
        topic.setTitle(topicDTO.getTitle());
        topic.setCreater(topicDTO.getOperator());
        topic.setIsDel(YesNo.NO.getValue());
        try {
            Integer count = topicMapper.insert(topic);
            return topic.getId();
        }catch ( Exception e ){
            Logger.error(this.getClass(), "publishTopic数据库帖子记录插入异常", e);
            return null;
        }
    }

    /**
     * @description 根据帖子ID获取帖子
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 15:08
     * @params id
     * @return
     */
    public Topic getTopicById(Long id){
        try {
            Topic topic = topicMapper.selectByPrimaryKey(id);
            return topic;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "getTopicById数据库数据获取异常", e);
            return null;
        }
    }

    /**
     * @description 根据帖子ID获取帖子
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 15:08
     * @params id
     * @return
     */
    public Topic getTopicByIdNoDel(Long id){
        try {
            Topic topic = topicMapper.selectByPrimaryKeyNoDel(id);
            return topic;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "getTopicById数据库数据获取异常", e);
            return null;
        }
    }

    /**
     * @description 置顶帖子
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:56
     * @params topicId
     * @return Integer
     */
    public Integer stickTopic(Topic topic){
        try {
            Integer count = topicMapper.updateTopicKindById(topic);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "stickTopic数据库数据更新异常", e);
            return -1;
        }
    }

    /**
     * @description 取消置顶
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:56
     * @params topicId
     * @return Integer
     */
    public Integer unStickTopic(Topic topic){
        try {
            Integer count = topicMapper.updateTopicKindById(topic);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "unStickTopic数据库数据更新异常", e);
            return -1;
        }
    }
    /**
     * @description 根据ID删除帖子
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:56
     * @params topic
     * @return Integer
     */
    public Integer deleteTopicById(Topic topic){
        try {
            Integer count = topicMapper.deleteTopicById(topic);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "deleteTopicById数据库数据更新异常", e);
            return -1;
        }
    }

    public ResultDO<TopicDetailDTO> getTopicDetailByID(TopicDetailQuery topicDetailQuery){
        ResultDO<TopicDetailDTO> resultDO = new ResultDO<TopicDetailDTO>();
        Long topicId = topicDetailQuery.getTopicId();
        Long userId = topicDetailQuery.getUserId();
        Integer userType = topicDetailQuery.getUserType();
        Topic topic = getTopicByIdNoDel(topicId);
        if(ValidateHelper.isEmpty(topic) || ValidateHelper.isEmpty(topic.getId()) ){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }
        if(topic.getIsDel() == YesNo.YES.getValue()){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_DELETE.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_DELETE.getCode());
            return resultDO;
        }

        TopicDetailDTO topicDetailDTO = topicMapper.selectTopicDetail(topicDetailQuery);
        if(topicDetailDTO == null){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        //v1.2修改--获取用户相关信息调用公共方法
        UserInfoManager.UserInfo userInfo = userInfoManager.getUserInfo(topic.getPublisherType(), topic.getPublisherId());
        topicDetailDTO.setSignature(userInfo.getSignature());
        topicDetailDTO.setHeadPicture(userInfo.getHeadPicture());
        topicDetailDTO.setNickName(userInfo.getNickName());

        //v1.2修改--生成H5对象修改为调用公共方法
        Long thirdArticleId = topic.getThirdArticleId();
        String url = topic.getHyperlinkUrl();
        HfivesObject h5 = genH5Object(thirdArticleId , url);
        if(!ValidateHelper.isEmpty(h5)){
            topicDetailDTO.setH5obj(h5);
        }

        //v1.2新增--如果帖子是长图文，image的样式增加宽度限制
        if(topicDetailDTO.getContent() != null && topicDetailDTO.getPublishType() == (PubishType.PICCONTENT.getValue())){
            String newContent = HtmlUtils.replaceImageStyle(topicDetailDTO.getContent());
            topicDetailDTO.setContent(newContent);
            Logger.info(this.getClass(),"image样式替换后的结果" + newContent);
        }

        //更新通知
        updateNotifyRecord(topicId , 0 , userId);

        resultDO.setData(topicDetailDTO);
        resultDO.setSuccess(true);
        resultDO.setErrMsg("");
        resultDO.setErrCode(0);
        return resultDO;
    }

    public ResultDO<TopicDetailDTO> getTopicDetailByUserId(TopicDetailQuery topicDetailQuery){
        ResultDO<TopicDetailDTO> resultDO = new ResultDO<TopicDetailDTO>();
        Long topicId = topicDetailQuery.getTopicId();
        Long userId = topicDetailQuery.getUserId();
        Integer userType = topicDetailQuery.getUserType();
        Topic topic = getTopicByIdNoDel(topicId);
        if(ValidateHelper.isEmpty(topic) || ValidateHelper.isEmpty(topic.getId()) ){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }
        if(topic.getIsDel() == YesNo.YES.getValue()){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_DELETE.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_DELETE.getCode());
            return resultDO;
        }

        TopicDetailDTO topicDetailDTO = topicMapper.selectTopicDetailByUserId(topicDetailQuery);
        if(topicDetailDTO == null){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        //v1.2修改--获取用户相关信息调用公共方法
        UserInfoManager.UserInfo userInfo = userInfoManager.getUserInfo(topic.getPublisherType(), topic.getPublisherId());
        topicDetailDTO.setSignature(userInfo.getSignature());
        topicDetailDTO.setHeadPicture(userInfo.getHeadPicture());
        topicDetailDTO.setNickName(userInfo.getNickName());

        //v1.2修改--生成H5对象修改为调用公共方法
        Long thirdArticleId = topic.getThirdArticleId();
        String url = topic.getHyperlinkUrl();
        HfivesObject h5 = genH5Object(thirdArticleId , url);
        if(!ValidateHelper.isEmpty(h5)){
            topicDetailDTO.setH5obj(h5);
        }

        if(topicDetailDTO.getContent() != null && topicDetailDTO.getPublishType() == (PubishType.PICCONTENT.getValue())){
            String newContent = HtmlUtils.replaceImageStyle(topicDetailDTO.getContent());
            topicDetailDTO.setContent(newContent);
            Logger.info(this.getClass(),"image样式替换后的结果" + newContent);
        }

        //更新通知
        updateNotifyRecord(topicId , 0 , userId);

        resultDO.setData(topicDetailDTO);
        resultDO.setSuccess(true);
        resultDO.setErrMsg("");
        resultDO.setErrCode(0);
        return resultDO;
    }

    /**
     * 根据用户查询我的帖子
     * @param myTopicListQuery
     * @return
     */
    public ResultDO<Page<TopicListDTO>> getTopicListByUserId(MyTopicListQuery myTopicListQuery){
        ResultDO<Page<TopicListDTO>> resultDO = new ResultDO<Page<TopicListDTO>>();
        Page<TopicListDTO> page = new Page<TopicListDTO>();
        page.setPageNumber(myTopicListQuery.getPageNumber());
        page.setPageSize(myTopicListQuery.getPageSize());
        page.setQuery(myTopicListQuery);
        List<TopicList> list = topicMapper.selectTopicListByUserId(page);
        List<TopicListDTO> newListData = new ArrayList<TopicListDTO>();
        if(!ValidateHelper.isEmpty(list) && list.size()>0){
            for (int i = 0;i<page.getData().size();i++){
                TopicList topic = list.get(i);
                TopicListDTO topicListDTO = new TopicListDTO();
                BeanUtils.copyProperties(topic,topicListDTO);

                //清除HTML标签
                if(topicListDTO.getContent() != null && topicListDTO.getPublishType().equals(String.valueOf(PubishType.PICCONTENT.getValue()))){
                    String newContent = HtmlUtils.deleteAllHTMLTag(topicListDTO.getContent());
                    topicListDTO.setContent(newContent);
                    Logger.info(this.getClass(),"清除标签后的结果：" + newContent);
                }

                //v1.2修改--获取用户相关信息调用公共方法
                UserInfoManager.UserInfo userInfo = userInfoManager.getUserInfo(topic.getPublisherType(), topic.getPublisherId());
                topicListDTO.setSignature(userInfo.getSignature());
                topicListDTO.setHeadPicture(userInfo.getHeadPicture());
                topicListDTO.setNickName(userInfo.getNickName());

                //v1.2修改--生成H5对象修改为调用公共方法
                Long thirdArticleId = topic.getThirdArticleId();
                String url = topic.getUrl();
                HfivesObject h5 = genH5Object(thirdArticleId , url);
                if(!ValidateHelper.isEmpty(h5)){
                    topicListDTO.setH5obj(h5);
                }

                newListData.add(topicListDTO);
            }
        }
        page.setData(newListData);
        resultDO.setData(page);
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * 根据所有用户的帖子列表 根据类型查询 0:最新发布，1：近期最火
     * @param topicListQuery
     * @return
     */
    public ResultDO<Page<TopicListDTO>> getTopicList(TopicListQuery topicListQuery){
        ResultDO<Page<TopicListDTO>> resultDO = new ResultDO<Page<TopicListDTO>>();
        Page<TopicListDTO> page = new Page<TopicListDTO>();
        page.setPageNumber(topicListQuery.getPageNumber());
        page.setPageSize(topicListQuery.getPageSize());
        page.setQuery(topicListQuery);
        List<TopicList> list = topicMapper.selectTopicList(page);
        List<TopicListDTO> newListData = new ArrayList<TopicListDTO>();
        if(!ValidateHelper.isEmpty(list) && list.size()>0){
            for (int i = 0;i<page.getData().size();i++){
                TopicList topic = list.get(i);
                TopicListDTO topicListDTO = new TopicListDTO();
                BeanUtils.copyProperties(topic,topicListDTO);

                //v1.2修改--获取用户相关信息调用公共方法
                UserInfoManager.UserInfo userInfo = userInfoManager.getUserInfo(topic.getPublisherType(), topic.getPublisherId());
                topicListDTO.setSignature(userInfo.getSignature());
                topicListDTO.setHeadPicture(userInfo.getHeadPicture());
                topicListDTO.setNickName(userInfo.getNickName());

                //v1.2修改--生成H5对象修改为调用公共方法
                Long thirdArticleId = topic.getThirdArticleId();
                String url = topic.getUrl();
                HfivesObject h5 = genH5Object(thirdArticleId , url);
                if(!ValidateHelper.isEmpty(h5)){
                    topicListDTO.setH5obj(h5);
                }

                //清除HTML标签
                if(topicListDTO.getContent() != null && topicListDTO.getPublishType().equals(String.valueOf(PubishType.PICCONTENT.getValue()))){
                    String newContent = HtmlUtils.deleteAllHTMLTag(topicListDTO.getContent());
                    topicListDTO.setContent(newContent);
                    Logger.info(this.getClass(),"清除标签后的结果：" + newContent);
                }

                newListData.add(topicListDTO);
            }
        }
        page.setData(newListData);
        resultDO.setData(page);
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/16 0016 11:18
     * @param
     * @return
     */
    public ResultDO<List<TopicListDTO>> getHomePageTopic(TopicListQuery topicListQuery) {
        ResultDO<List<TopicListDTO>> resultDO = new ResultDO<>();
        List<TopicListDTO> resultList = new ArrayList<>();
        List<TopicList> topList = new ArrayList<>();
        List<TopicList> popularList=new ArrayList<>();
        List<TopicList> newList = new ArrayList<>();
        topList = topicMapper.selectTopTopicList(topicListQuery);
        if(topList.size() < 4){
            popularList= topicMapper.selectPopularTopicList(topicListQuery);
            for(TopicList item : popularList){
                if(!topList.contains(item) && topList.size() < 4){
                    topList.add(item);
                }
            }
            if(topList.size() < 4){
                newList = topicMapper.selectNewTopicList(topicListQuery);
                for(TopicList item : newList){
                    if(!topList.contains(item) && topList.size() < 4){
                        topList.add(item);
                    }
                }
            }
        }

        for (TopicList item : topList) {
            TopicListDTO topicListDTO = new TopicListDTO();
            BeanUtils.copyProperties(item, topicListDTO);

            //v1.2修改--获取用户相关信息调用公共方法
            UserInfoManager.UserInfo userInfo = userInfoManager.getUserInfo(item.getPublisherType(), item.getPublisherId());
            topicListDTO.setSignature(userInfo.getSignature());
            topicListDTO.setHeadPicture(userInfo.getHeadPicture());
            topicListDTO.setNickName(userInfo.getNickName());

            //清除HTML标签
            if(topicListDTO.getContent() != null && topicListDTO.getPublishType().equals(String.valueOf(PubishType.PICCONTENT.getValue()))){
                String newContent = HtmlUtils.deleteAllHTMLTag(topicListDTO.getContent());
                topicListDTO.setContent(newContent);
                Logger.info(this.getClass(),"清除标签后的结果：" + newContent);
            }

            //v1.2修改--生成H5对象修改为调用公共方法
            String url = item.getUrl();
            Long thirdId = item.getThirdArticleId();
            HfivesObject h5 = genH5Object(thirdId,url);
            if(!ValidateHelper.isEmpty(h5)){
                topicListDTO.setH5obj(h5);
            }

            resultList.add(topicListDTO);
        }

        resultDO.setSuccess(true);
        resultDO.setData(resultList);
        return resultDO;
    }

    //v1.2新增-将生成H5对象代码抽象成公共方法
    private HfivesObject genH5Object(Long thirdArticleId , String url){
        if(!ValidateHelper.isEmpty(thirdArticleId)){
            ResultDO<BaseArticleInformation> resultArticleInformation = articleInformationService.getArticleById(thirdArticleId);
            if(resultArticleInformation.isSuccess()){
                BaseArticleInformation baseArticleInformation = resultArticleInformation.getData();
                if(!ValidateHelper.isEmpty(baseArticleInformation)){
                    HfivesObject hfivesObject = new HfivesObject();
                    hfivesObject.setTitle(baseArticleInformation.getTitle());
                    hfivesObject.setContent(HtmlUtils.deleteAllHTMLTag(baseArticleInformation.getContent()));
                    hfivesObject.setImageUrl(baseArticleInformation.getImage());
                    hfivesObject.setUrl(url);
                    return hfivesObject;
                }
            }
        }
        return null;
    }

    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }

            return params;
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/22 0022 16:32
     * @param
     * @return
     */
    private void updateNotifyRecord(Long bizId , Integer serviceType , Long userId){
        BaseNotification baseNotification = new BaseNotification();
        baseNotification.setBizId(bizId);
        baseNotification.setServiceType(serviceType);
        baseNotification.setNotifyType(2);
        baseNotification.setReceiverId(userId);
        baseNotification.setReceiverType(UserType.MERCHANT.getValue());
        notificationService.updateByBusId(baseNotification);
    }
}
