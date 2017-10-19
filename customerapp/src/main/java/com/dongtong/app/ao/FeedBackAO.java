package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.customer.dto.req.FeedBackReqDTO;
import com.dongtong.customer.service.FeedBackService;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunyaping
 * @Package com.dongtong.app.ao
 * @Description :意见反馈
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-12 18:18
 * version V1.0.0
 **/
@Service
public class FeedBackAO {

    @Autowired(required = false)
    private FeedBackService feedBackService;

    /**
     * 添加反馈意见
     * @param feedBackReqDTO
     * @return
     */
    public ResultDO<Long> addFeedBack(FeedBackReqDTO feedBackReqDTO){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        feedBackReqDTO.setCustomerId(userId);
        return feedBackService.addFeedBack(feedBackReqDTO);
    }
}
