package com.dongtong.topic.service;

import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.BussinessNoticeReqDTO;
import com.dongtong.basic.enums.BussinessNoticeType;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.basic.service.NotificationService;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.service.CustomerService;
import com.dongtong.topic.domain.Topic;
import com.dongtong.topic.domain.TopicComment;
import com.dongtong.topic.dto.TopicCommentDTO;
import com.dongtong.topic.dto.TopicCommentDetailDTO;
import com.dongtong.topic.enums.CommentTypeEnums;
import com.dongtong.topic.enums.TopicErrorCode;
import com.dongtong.topic.enums.UserType;
import com.dongtong.topic.enums.YesNo;
import com.dongtong.topic.manager.TopicCommentManager;
import com.dongtong.topic.manager.TopicManager;
import com.dongtong.topic.query.TopicCommentDetailQuery;
import com.shfc.common.base.EmojiFilter;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @version V1.0.0
 * @Package com.dongtong.topic.service
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 15:49
 */
@Service
public class TopicCommentServiceImpl implements TopicCommentService {

    @Autowired
    private TopicManager topicManager;
    @Autowired
    private TopicCommentManager topicCommentManager;
    @Autowired
    private NoticePushService noticePushService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private NotificationService notificationService;

    /**
     * @description 发布评论
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 10:50
     * @params topicCommentDTO
     * @return ResultDO
     */
    public ResultDO<TopicCommentDetailDTO> publishComment(TopicCommentDTO topicCommentDTO){
        ResultDO<TopicCommentDetailDTO> resultDO = new ResultDO<>();

        Long topicId = topicCommentDTO.getTopicId();
        String content = topicCommentDTO.getContent();
        Long operator = topicCommentDTO.getOperator();
        Integer userType = topicCommentDTO.getUserType();
        topicCommentDTO.setContent(EmojiFilter.filterEmoji(content));     //内容过滤表情

        //v1.2新增字段
        Integer isReply = topicCommentDTO.getIsReply();
        Long parentId = topicCommentDTO.getParentId();

        //判断输入参数
        if(ValidateHelper.isEmpty(topicId)) {
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        if(ValidateHelper.isEmpty(content)) {
            resultDO.setErrMsg("评论内容不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        if(ValidateHelper.isEmpty(operator) || ValidateHelper.isEmpty(userType)) {
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(isReply)) {
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        ResultDO<Customer> operatorResult = customerService.findCustomerInfoByCustomerId(operator);

        if(operatorResult !=null && operatorResult.isSuccess() && operatorResult.getData() != null){
            if(operatorResult.getData().getIsBanned().equals(YesNo.YES.getValue())){
                resultDO.setErrMsg("您已被禁言");
                resultDO.setErrCode(20007);
                return resultDO;
            }
        }

        //v1.2 如果评论类型为回复，回复父ID不可为空
        TopicComment topicComment =  null;
        if(isReply == CommentTypeEnums.REPLAY.getValue()){
            if(ValidateHelper.isEmpty(parentId)){
                resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
                resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
                return resultDO;
            }

            topicComment = topicCommentManager.selectCommentById(parentId);
        }

        //判断帖子是否存在
        Topic topic = topicManager.getTopicById(topicId);
        if(topic == null  || topic.getIsDel() == YesNo.YES.getValue()){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        String deviceId = null;
        Integer osType = null;
        Long userId = null;
        Integer receiveType = null;
        if(topic.getPublisherType() != null){
            if(topic.getPublisherType() == UserType.MERCHANT.getValue()){
                if(isReply == CommentTypeEnums.REPLAY.getValue()){
                    ResultDO<Customer> commentorResult = customerService.findCustomerInfoByCustomerId(topicComment.getCommentatorId());
                    Logger.info(this.getClass(),"获取到的用户信息："+commentorResult);
                    if(commentorResult !=null && commentorResult.isSuccess() && commentorResult.getData() != null){
                        deviceId = commentorResult.getData().getDeviceId();
                        osType = commentorResult.getData().getOsType();
                        userId = commentorResult.getData().getId();
                        receiveType = ReceiveType.CUSTOMER.getValue();
                    }
                }
                if(isReply == CommentTypeEnums.COMMENT.getValue()){
                    ResultDO<Customer> publisherResult = customerService.findCustomerInfoByCustomerId(topic.getPublisherId());
                    Logger.info(this.getClass(),"获取到的用户信息："+publisherResult);
                    if(publisherResult !=null && publisherResult.isSuccess() && publisherResult.getData() != null){
                        deviceId = publisherResult.getData().getDeviceId();
                        osType = publisherResult.getData().getOsType();
                        userId = publisherResult.getData().getId();
                        receiveType = ReceiveType.CUSTOMER.getValue();
                    }
                }
            }
        }

        if(deviceId != null && osType != null && userId != null){
            BussinessNoticeReqDTO bussinessNoticeReqDTO = new BussinessNoticeReqDTO();
            if((isReply == CommentTypeEnums.COMMENT.getValue() && topic.getPublisherId().longValue() != operator.longValue())
                    || isReply == CommentTypeEnums.REPLAY.getValue() && topicComment != null && topicComment.getCommentatorId().longValue() != operator.longValue()) {       //当前用户不是帖子发布人，推送消息
                if (operatorResult != null && operatorResult.isSuccess() && operatorResult.getData() != null) {
                    bussinessNoticeReqDTO.setReceiveId(userId);
                    bussinessNoticeReqDTO.setCommentTel(operatorResult.getData().getPhone());
                    bussinessNoticeReqDTO.setCommentNickName(operatorResult.getData().getNickName());
                    bussinessNoticeReqDTO.setReceiveType(receiveType);
                    bussinessNoticeReqDTO.setContentOrReason(content);
                    bussinessNoticeReqDTO.setCommentOrOprationTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                }
                if(isReply == CommentTypeEnums.REPLAY.getValue()){
                    bussinessNoticeReqDTO.setBussinessId(topicId);
                    bussinessNoticeReqDTO.setPostName(topicComment.getContent());
                    bussinessNoticeReqDTO.setBussinessType(BussinessNoticeType.COMMENTED_ON.getValue());
                }
                if(isReply == CommentTypeEnums.COMMENT.getValue()){
                    bussinessNoticeReqDTO.setBussinessId(topicId);
                    bussinessNoticeReqDTO.setPostName(topic.getTitle());
                    bussinessNoticeReqDTO.setBussinessType(BussinessNoticeType.COMMENT_NOTICE.getValue());
                }
                Logger.info(this.getClass(),"消息发送报文："+bussinessNoticeReqDTO+",设备ID:"+deviceId+",设备类型："+osType);
                ResultDO result = noticePushService.pushPostCommentNotify(bussinessNoticeReqDTO, deviceId,osType);
                Logger.info(this.getClass(),"消息发送结果："+result);
            }
        }
        TopicCommentDetailDTO topicCommentDetailDTO = topicCommentManager.publishComment(topicCommentDTO);
        if(topicCommentDetailDTO != null){
            resultDO.setData(topicCommentDetailDTO);
        }
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description 根据评论ID删除评论
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 10:50
     * @params commentId
     * @return ResultDO
     */
    public ResultDO deleteComment(TopicCommentDTO topicCommentDTO){
        ResultDO resultDO = new ResultDO();
        Long commentId = topicCommentDTO.getCommentId();
        Long operator = topicCommentDTO.getOperator();
        Integer userType = topicCommentDTO.getUserType();

        //判断必填参数
        if(ValidateHelper.isEmpty(commentId)){
            resultDO.setErrMsg("评论ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(userType) || ValidateHelper.isEmpty(operator)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        TopicComment topicComment = topicCommentManager.getTopicCommentById(commentId);    //根据ID查询帖子
        if(topicComment == null){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        Long commentatorId = topicComment.getCommentatorId();

        //商户用户，需要判断发帖人和删帖人是否为同一人，否则为无权限
        if(operator.longValue() != commentatorId.longValue()){
            resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
            resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
            return resultDO;
        }

        Topic topic  = topicManager.getTopicById(topicComment.getBusinessId());
        if(topic == null){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
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

        if(operator.longValue() != commentatorId.longValue()) {
            if (topic.getPublisherType() != null) {
                BussinessNoticeReqDTO bussinessNoticeReqDTO = new BussinessNoticeReqDTO();
                bussinessNoticeReqDTO.setBussinessId(topic.getId());
                bussinessNoticeReqDTO.setBussinessType(BussinessNoticeType.DELETE_NOTICE.getValue());
                bussinessNoticeReqDTO.setPostName(topic.getTitle());
                bussinessNoticeReqDTO.setCommentOrOprationTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                bussinessNoticeReqDTO.setContentOrReason("");
                //获取评论内容
                TopicComment topicComm =topicCommentManager.getTopicCommentById(commentId);
                if(topicComm !=null) {
                    //评论人为用户则推送消息
                    if (topicComm.getCommentatorType() == UserType.MERCHANT.getValue()) {
                        ResultDO<Customer> publisherResult = customerService.findCustomerInfoByCustomerId(topicComm.getCommentatorId());
                        if (publisherResult.isSuccess()) {
                            if (publisherResult.getData() != null) {
                                bussinessNoticeReqDTO.setReceiveId(topicComm.getCommentatorId());
                                bussinessNoticeReqDTO.setCommentTel(phone);
                                bussinessNoticeReqDTO.setPostName(topic.getTitle());
                                bussinessNoticeReqDTO.setReceiveType(topic.getPublisherType());
                                Logger.info(this.getClass(), "消息推送发送报文：" + bussinessNoticeReqDTO + ",设备ID:" + publisherResult.getData().getDeviceId() + ",设备类型：" + publisherResult.getData().getOsType());
                                ResultDO msgResult = noticePushService.pushBusDelNotify(bussinessNoticeReqDTO, publisherResult.getData().getDeviceId(), publisherResult.getData().getOsType());
                                Logger.info(this.getClass(), "消息推送返回报文：" + msgResult);
                            }
                        }
                    }
                }
            }
        }

        //删除评论
        Integer count = topicCommentManager.deleteComment(topicCommentDTO);
        if(count == 0){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        resultDO.setSuccess(true);
        return resultDO;
    }


    public ResultDO<Page<TopicCommentDetailDTO>> getCommentList(TopicCommentDetailQuery topicCommentDetailQuery){
        ResultDO<Page<TopicCommentDetailDTO>> resultDO = new ResultDO<>();
        Long topicId = topicCommentDetailQuery.getTopicId();
        Long userId = topicCommentDetailQuery.getUserId();
        Integer pageNumber = topicCommentDetailQuery.getPageNumber();
        //判断必填参数
        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        if(ValidateHelper.isEmpty(pageNumber)){
            resultDO.setErrMsg("查询页数不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        Page<TopicCommentDetailDTO> commentList = topicCommentManager.getCommentByBusinessId(topicCommentDetailQuery);
        for(TopicCommentDetailDTO item : commentList.getData()){
            updateNotifyRecord(Long.valueOf(item.getId()) , 3 , userId);
        }
        resultDO.setData(commentList);
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/22 0022 16:31
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
