package com.dongtong.customer.service;

import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.dto.req.FeedBackReqDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sunyaping
 * @Package com.dongtong.customer.service
 * @Description :建议反馈
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 17:41
 * version V1.0.0
 **/
public class FeedBackServiceTest extends JunitBaseTest{

    @Autowired
    private FeedBackService feedBackService;

    @Test
    public void addFeedBack(){
        FeedBackReqDTO feedBackReqDTO=new FeedBackReqDTO();
        feedBackReqDTO.setCustomerId(1L);
        feedBackReqDTO.setFeedback("和jfk费用分担分享下大自然带给try一天");
        ResultDO resultDO=feedBackService.addFeedBack(feedBackReqDTO);

    }
}
