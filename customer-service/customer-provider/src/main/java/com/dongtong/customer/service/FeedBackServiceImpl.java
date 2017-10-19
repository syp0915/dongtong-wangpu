package com.dongtong.customer.service;

import com.dongtong.customer.dto.req.FeedBackReqDTO;
import com.dongtong.customer.manager.FeedBackManage;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sunyaping
 * @Package com.dongtong.customer.Service
 * @Description :TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 16:39
 * version V1.0.0
 **/
@Service
public class FeedBackServiceImpl implements FeedBackService{

    @Autowired
    private FeedBackManage feedBackManage;

    /**
     * 添加意见反馈
     * @param feedBackReqDTO
     * @return
     */
    @Override
    public ResultDO<Long> addFeedBack(FeedBackReqDTO feedBackReqDTO) {
        ResultDO resultDO=new ResultDO();
        String result=checkParam(feedBackReqDTO);
        if(!ValidateHelper.isEmptyString(result)){
            resultDO.setSuccess(false);
            resultDO.setErrMsg(result);
            return resultDO;
        }
        feedBackReqDTO.setCreateTime(new Date());
        resultDO=feedBackManage.insertFeedBack(feedBackReqDTO);
        return resultDO;
    }

    /**
     * 参数校验
     * @param feedBackReqDTO
     * @return
     */
    public String checkParam(FeedBackReqDTO feedBackReqDTO){
        Long customerId=feedBackReqDTO.getCustomerId();
        if(customerId==null){
            return "建议人Id不能为空";
        }
        if(ValidateHelper.isEmptyString(feedBackReqDTO.getFeedback())){
            return "建议内容不能为空";
        }
        return null;
    }
}
