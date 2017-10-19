package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseNotificationMapper;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.BussinessNoticeReqDTO;
import com.dongtong.basic.enums.BussinessNoticeType;
import com.dongtong.basic.enums.NoticeType;
import com.dongtong.basic.enums.ReceiveType;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.manager
 * @Description :生意圈通知管理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 16:07
 * version V1.0.0
 **/
@Service
public class BussinessNoticeManager {

    @Autowired
    private BaseNotificationMapper baseNotificationMapper;

    /**
     * 生意圈通知添加
     * @param bussinessNoticeDTO
     * @return
     */
    public ResultDO<Long> addBussinessNotice(BussinessNoticeReqDTO bussinessNoticeDTO){
        ResultDO resultDO = new ResultDO();
        BaseNotification baseNotification=new BaseNotification();
        baseNotification.setBizId(bussinessNoticeDTO.getBussinessId());
        baseNotification.setNotifyType(NoticeType.BUSSINESS.getValue());   //通知类型：生意圈
        baseNotification.setReceiverId(bussinessNoticeDTO.getReceiveId());  //接收人Id
        baseNotification.setReceiverType(bussinessNoticeDTO.getReceiveType()); //接收人类型 0：客户 1:业务员
        baseNotification.setServiceType(bussinessNoticeDTO.getBussinessType()); //0-评论通知 1-帖子被撤通知 2-评论被删通知
        baseNotification.setStatus(1); //消息状态 默认未查看
        //生成消息通知简要
        Integer bussinessType=bussinessNoticeDTO.getBussinessType();
        String nickName=bussinessNoticeDTO.getCommentNickName();
        String digest=appendDigest(bussinessType,nickName);
        baseNotification.setDigest(digest);
        //生成消息通知内容
        String createContent=createContent(bussinessNoticeDTO);
        baseNotification.setContent(createContent);
        baseNotification.setCreateTime(new Date());
        int result=baseNotificationMapper.insert(baseNotification);
        if(result>0){
            resultDO.setSuccess(true);
            resultDO.setData(baseNotification.getId());
        }else{
            resultDO.setErrMsg("添加记录失败");
        }
        return  resultDO;
    }

    /**
     * 生成通知简要
     * @return
     */
    public String appendDigest(Integer bussinessType,String commentNickName){
        StringBuffer digest=new StringBuffer();
        //手机号处理
        if(bussinessType== BussinessNoticeType.COMMENT_NOTICE.getValue()){
            digest.append("【"+commentNickName+"】评论了您的帖子");
        }
        if(bussinessType== BussinessNoticeType.DELETE_NOTICE.getValue()){
            digest.append("您的评论不符合规范，已被小二删除");
        }
        if(bussinessType== BussinessNoticeType.WITHDRAW_NOTICE.getValue()){
            digest.append("您的帖子不符合规范，已被小二删除");
        }
        if(bussinessType== BussinessNoticeType.COMMENTED_ON.getValue()){
            digest.append("【"+commentNickName+"】回复了您的评论");
        }
        return digest.toString();
    }

    /**
     * 生成通知类型（json字符串）
     * @return
     */
    public String createContent(BussinessNoticeReqDTO bussinessNoticeDTO){
        Integer type=bussinessNoticeDTO.getReceiveType();
        String tel=bussinessNoticeDTO.getCommentTel();
        String telephone=tel.substring(0,3)+"****"+tel.substring(7);
        String content=null;
        if(type==ReceiveType.CUSTOMER.getValue()){
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"postName\":" + "\"" + bussinessNoticeDTO.getPostName() + "\",");
            json.append("\"commentOrOprationTime\":" + "\"" + bussinessNoticeDTO.getCommentOrOprationTime() + "\",");
            json.append("\"contentOrReason\":" + "\"" + bussinessNoticeDTO.getContentOrReason() + "\",");
            json.append("}");
            content=json.toString();
        }
        if(type==ReceiveType.CLERK.getValue()){          //业务端生意圈通知内容
            content=telephone+"评论说『"+bussinessNoticeDTO.getContentOrReason()+"』";
        }
        return content;
    }



}
