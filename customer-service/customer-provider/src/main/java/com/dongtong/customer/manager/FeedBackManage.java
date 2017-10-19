package com.dongtong.customer.manager;

import com.dongtong.customer.dao.CustomerFeedbackMapper;
import com.dongtong.customer.domain.CustomerFeedback;
import com.dongtong.customer.dto.req.FeedBackReqDTO;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunyaping
 * @Package com.dongtong.customer.manager
 * @Description ：TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 15:52
 * version V1.0.0
 **/
@Service
public class FeedBackManage {

    @Autowired
    private CustomerFeedbackMapper customerFeedbackMapper;

    /**
     * 插入意见反馈
     * @param feedBackReqDTO
     * @return
     */
    public ResultDO<Long> insertFeedBack(FeedBackReqDTO feedBackReqDTO){
        ResultDO<Long> resultDO=new ResultDO<Long>();
        CustomerFeedback customerFeedback=new CustomerFeedback();
        BeanUtils.copyProperties(feedBackReqDTO,customerFeedback);
        customerFeedbackMapper.insertSelective(customerFeedback);
        Long customerFeedbackId=customerFeedback.getId();
        if(customerFeedbackId==null){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("插入失败");
            return resultDO;
        }
        resultDO.setData(customerFeedbackId);
        resultDO.setSuccess(true);
        return resultDO;
    }
}
