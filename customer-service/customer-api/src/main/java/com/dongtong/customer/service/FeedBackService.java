package com.dongtong.customer.service;

import com.dongtong.customer.dto.req.FeedBackReqDTO;
import com.shfc.common.result.ResultDO;

/**
 * @author sunyaping
 * @Package com.dongtong.customer.service
 * @Description：TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 15:48
 * version V1.0.0
 **/
public interface FeedBackService {

    /**
     * 添加意见反馈
     * @param feedBackReqDTO
     * @return
     */
    ResultDO<Long> addFeedBack(FeedBackReqDTO feedBackReqDTO);
}
