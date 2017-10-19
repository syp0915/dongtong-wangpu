package com.dongtong.clerk.service;

import com.dongtong.clerk.bo.ClerkAgreementBO;
import com.dongtong.clerk.dto.req.ClerkAgreementDTO;
import com.shfc.common.result.ResultDO;

import java.text.ParseException;

/**
 * 合同
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/11 19:30
 * @since 1.0
 */
public interface ClerkAgreementService {
    ResultDO insertContract(ClerkAgreementDTO clerkAgreementDTO) throws ParseException;

    ResultDO <ClerkAgreementBO>getAgreementInfo(ClerkAgreementDTO clerkAgreementDTO);
}
