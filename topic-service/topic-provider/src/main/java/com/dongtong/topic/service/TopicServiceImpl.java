package com.dongtong.topic.service;

import com.dongtong.basic.dto.req.BussinessNoticeReqDTO;
import com.dongtong.basic.enums.BussinessNoticeType;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.service.CustomerService;
import com.dongtong.topic.domain.Topic;
import com.dongtong.topic.domain.TopicImgRel;
import com.dongtong.topic.dto.TopicDTO;
import com.dongtong.topic.dto.TopicDeleteDTO;
import com.dongtong.topic.dto.TopicDetailDTO;
import com.dongtong.topic.dto.TopicListDTO;
import com.dongtong.topic.enums.PubishType;
import com.dongtong.topic.enums.TopicErrorCode;
import com.dongtong.topic.enums.UserType;
import com.dongtong.topic.enums.YesNo;
import com.dongtong.topic.manager.TopicImgRelManager;
import com.dongtong.topic.manager.TopicManager;
import com.dongtong.topic.query.MyTopicListQuery;
import com.dongtong.topic.query.TopicDetailQuery;
import com.dongtong.topic.query.TopicListQuery;
import com.shfc.common.base.EmojiFilter;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 生意圈
 * @package com.dongtong.topic.service
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/9 10:54
 * @version v1.0.0
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicManager topicManager;
    @Autowired
    private TopicImgRelManager topicImgRelManager;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private NoticePushService noticePushService;

    /**
     * @description 发布帖子接口
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 10:52
     * @params topicDTO
     * @return ResultDO
     */
    public ResultDO publishTopic(TopicDTO topicDTO){
        ResultDO resultDO = new ResultDO();
        topicDTO.setContent(EmojiFilter.filterEmoji(topicDTO.getContent()));
        topicDTO.setTitle(EmojiFilter.filterEmoji(topicDTO.getTitle()));
        List<String> images = topicDTO.getImageList();

        String check = checkParams(topicDTO);
        if(check != null){
            resultDO.setErrMsg(check);
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        Integer userType = topicDTO.getUserType();
        Integer publishType = topicDTO.getPublishType();

        ResultDO<Customer> operatorResult = customerService.findCustomerInfoByCustomerId(topicDTO.getOperator());
        if(operatorResult !=null && operatorResult.isSuccess() && operatorResult.getData() != null){
            if(operatorResult.getData().getIsBanned() != null ) {
                if (operatorResult.getData().getIsBanned().equals(YesNo.YES.getValue())) {
                    resultDO.setErrMsg("您已被禁言");
                    resultDO.setErrCode(20007);
                    return resultDO;
                }
            }
        }

        //非运营角色，判断发布的是否为帖子，否则发布资讯为无权限
        if(publishType != PubishType.TOPIC.getValue()){
            resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
            resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
            return resultDO;
        }

        Long id = topicManager.publishTopic(topicDTO);
        if(id == null){
            resultDO.setErrMsg(TopicErrorCode.DB_OPERATE_ERROR.getMsg());
            resultDO.setErrCode(TopicErrorCode.DB_OPERATE_ERROR.getCode());
            return resultDO;
        }

        if(images != null && images.size() > 0){
            if(images.size() > 9){
                resultDO.setErrMsg(TopicErrorCode.PIC_COUNT_ERROR.getMsg());
                resultDO.setErrCode(TopicErrorCode.PIC_COUNT_ERROR.getCode());
                return resultDO;
            }
            for(int i = 0; i < images.size(); i++){
                String imageUrl = images.get(i);
                TopicImgRel topicImgRel = new TopicImgRel();
                topicImgRel.setImgIndex(i);
                topicImgRel.setImgUrl(imageUrl);
                topicImgRel.setTopicId(id);
                topicImgRel.setCreater(topicDTO.getOperator());
                topicImgRelManager.insertImages(topicImgRel);
            }
        }

        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * 获取帖子详情
     * @param topicDetailQuery
     * @return
     */
    @Override
    public ResultDO<TopicDetailDTO> getTopicDetailByID(TopicDetailQuery topicDetailQuery) {
        ResultDO<TopicDetailDTO> resultDO = new ResultDO<TopicDetailDTO>();
        Long topicId = topicDetailQuery.getTopicId();
        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            resultDO.setSuccess(false);
            return resultDO;
        }
        resultDO = topicManager.getTopicDetailByID(topicDetailQuery);
        return resultDO;
    }

    @Override
    public ResultDO<TopicDetailDTO> getTopicDetailByUserId(TopicDetailQuery topicDetailQuery) {
        ResultDO<TopicDetailDTO> resultDO = new ResultDO<TopicDetailDTO>();
        Long topicId = topicDetailQuery.getTopicId();
        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        resultDO = topicManager.getTopicDetailByUserId(topicDetailQuery);
        return resultDO;
    }

    /**
     * @description 帖子置顶
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 10:53
     * @params topicDTO
     * @return ResultDO
     */
    public ResultDO stickTopic(TopicDTO topicDTO){
        ResultDO resultDO = new ResultDO();
        Long topicId = topicDTO.getTopicId();
        Long operator = topicDTO.getOperator();
        Integer userType = topicDTO.getUserType();

        //判断非运营人员置顶
        if(userType == UserType.CLERK.getValue()){
            ResultDO<Clerk> loginClerk = clerkService.getClerkInfoById(topicDTO.getOperator());
            Integer role = null;
            if(loginClerk.isSuccess() && loginClerk.getData() != null){
                role = loginClerk.getData().getRoleType();
            }
            if(role == null || role != 1){
                resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
                resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
                return resultDO;
            }
        } else {
            resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
            resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        if(ValidateHelper.isEmpty(userType) || ValidateHelper.isEmpty(operator)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        if(userType == UserType.MERCHANT.getValue()){
            resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
            resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
            return resultDO;
        }

        Topic topic = topicManager.getTopicById(topicId);    //根据ID查询帖子
        if(topic == null || topic.getIsDel() == YesNo.YES.getValue()){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        topic.setKind(YesNo.YES.getValue());
        topic.setModifier(topicDTO.getOperator());
        Integer count = topicManager.stickTopic(topic);
        if(count == 0){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description 取消置顶帖子
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 15:28
     * @params topicDTO
     * @return ResultDO
     */
    public ResultDO unStickTopic(TopicDTO topicDTO){
        ResultDO resultDO = new ResultDO();
        Long topicId = topicDTO.getTopicId();
        Long operator = topicDTO.getOperator();
        Integer userType = topicDTO.getUserType();

        //非运营人员取消置顶帖子返回没有权限
        if(userType == UserType.CLERK.getValue()){
            ResultDO<Clerk> loginClerk = clerkService.getClerkInfoById(topicDTO.getOperator());
            Integer role = null;
            if(loginClerk.isSuccess() && loginClerk.getData() != null){
                role = loginClerk.getData().getRoleType();
            }

            if(role == null || role != 1){
                resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
                resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
                return resultDO;
            }
        } else {
            resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
            resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        if(ValidateHelper.isEmpty(userType) || ValidateHelper.isEmpty(operator)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        Topic topic = topicManager.getTopicById(topicId);    //根据ID查询帖子
        if(topic == null || topic.getIsDel() == YesNo.YES.getValue()){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        if(userType == UserType.MERCHANT.getValue()){
            resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
            resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
            return resultDO;
        }

        topic.setId(topicDTO.getTopicId());
        topic.setKind(YesNo.NO.getValue());
        topic.setModifier(topicDTO.getOperator());
        Integer count = topicManager.unStickTopic(topic);

        if(count == 0){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description 删除帖子接口
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 10:53
     * @params topicId
     * @return ResultDO
     */
    public ResultDO deleteTopic(TopicDeleteDTO topicDeleteDTO){
        ResultDO resultDO = new ResultDO();
        Long topicId = topicDeleteDTO.getTopicId();
        Integer userType = topicDeleteDTO.getUserType();
        Long operator = topicDeleteDTO.getOperator();

        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(operator)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        Topic topic = topicManager.getTopicById(topicId);    //根据ID查询帖子
        if(topic == null || topic.getIsDel() == YesNo.YES.getValue()){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        Long publisher = topic.getPublisherId();
        //商户用户，需要判断发帖人和删帖人是否为同一人，否则为无权限
        if(userType == null){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        /**
         * v1.2
         * 去掉业务员删任意帖子权限，业务员和用户具有相同删帖权限
         */
        if(operator.longValue() != topic.getPublisherId().longValue()){
            resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
            resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
            return resultDO;
        }

        Long userId = null;
        Integer receiveType = null;
        String phone = null;
        if(topic.getPublisherType() != null){
            if(userType == UserType.MERCHANT.getValue()){
                ResultDO<Customer> operatorResult = customerService.findCustomerInfoByCustomerId(operator);
                Logger.info(this.getClass(),"获取到的用户信息："+operatorResult);
                if(!operatorResult.isSuccess() || operatorResult.getData() == null){
                    resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
                    resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
                    return resultDO;
                }
                userId = operatorResult.getData().getId();
                receiveType = ReceiveType.CUSTOMER.getValue();
                phone = operatorResult.getData().getPhone();
            }
        }

        if(operator.longValue() != topic.getPublisherId().longValue()) {
            //删除帖子推送通知
            BussinessNoticeReqDTO bussinessNoticeReqDTO = new BussinessNoticeReqDTO();
            bussinessNoticeReqDTO.setBussinessId(topic.getId());
            bussinessNoticeReqDTO.setBussinessType(BussinessNoticeType.WITHDRAW_NOTICE.getValue());
            bussinessNoticeReqDTO.setPostName(topic.getTitle());
            bussinessNoticeReqDTO.setCommentOrOprationTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            bussinessNoticeReqDTO.setContentOrReason("");
            Logger.info(this.getClass(),"发帖人类型:" + topic.getPublisherType());
            if (topic.getPublisherType() != null) {
                if (topic.getPublisherType() == UserType.MERCHANT.getValue()) {
                    ResultDO<Customer> publisherResult = customerService.findCustomerInfoByCustomerId(topic.getPublisherId());
                    Logger.info(this.getClass(),"发帖人人信息:" + publisherResult);
                    if(publisherResult.isSuccess()){
                        if (publisherResult.getData() != null) {
                            bussinessNoticeReqDTO.setReceiveId(topic.getPublisherId());
                            bussinessNoticeReqDTO.setReceiveType(topic.getPublisherType());
                            bussinessNoticeReqDTO.setPostName(topic.getTitle());
                            bussinessNoticeReqDTO.setCommentTel(phone);
                            Logger.info(this.getClass(), "消息推送发送报文：" + bussinessNoticeReqDTO + ",设备ID:" + publisherResult.getData().getDeviceId() + ",设备类型：" + publisherResult.getData().getOsType());
                            ResultDO result = noticePushService.pushBusDelNotify(bussinessNoticeReqDTO, publisherResult.getData().getDeviceId(), publisherResult.getData().getOsType());
                            Logger.info(this.getClass(), "消息推送返回报文：" + result);
                        }
                    }
                }
            }
        }

        topic.setModifier(operator);
        topic.setIsDel(YesNo.YES.getValue());
        Integer count = topicManager.deleteTopicById(topic);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<Page<TopicListDTO>> getTopicList(TopicListQuery topicListQuery) {
        ResultDO<Page<TopicListDTO>> resultDO = new ResultDO<Page<TopicListDTO>>();
        if(ValidateHelper.isEmpty(topicListQuery.getType()) || ValidateHelper.isEmpty(topicListQuery.getPageNumber())){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        resultDO = topicManager.getTopicList(topicListQuery);
        return resultDO;
    }

    @Override
    public ResultDO<Page<TopicListDTO>> getTopicListByUserId(MyTopicListQuery myTopicListQuery) {
        ResultDO<Page<TopicListDTO>> resultDO = new ResultDO<Page<TopicListDTO>>();
        if(ValidateHelper.isEmpty(myTopicListQuery.getUserId()) || ValidateHelper.isEmpty(myTopicListQuery.getPageNumber())){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        resultDO = topicManager.getTopicListByUserId(myTopicListQuery);
        return resultDO;
    }

    public String checkParams(TopicDTO dto){
        if(ValidateHelper.isEmpty(dto.getPublishType())){
            return "帖子类型不能为空";
        }

        if(ValidateHelper.isEmpty(dto.getContent())){
            if(ValidateHelper.isEmpty(dto.getImageList()) || dto.getImageList().size() == 0){
                return "帖子内容和图片必填其中之一";
            }
        }

        if(ValidateHelper.isEmpty(dto.getUserType())){
            return "发帖人类型不能为空";
        }
        if(ValidateHelper.isEmpty(dto.getTitle())){
            return "帖子标题不能为空";
        }
        return null;
    }

    /**
     * @description
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/16 0016 10:28
     * @return ResultDO<List<TopicListDTO>>
     */
    public ResultDO<List<TopicListDTO>> getHomePageTopic(TopicListQuery topicListQuery) {
        return topicManager.getHomePageTopic(topicListQuery);
    }
}
