package com.dongtong.clerk.service;

import com.dongtong.clerk.bo.ClerkAgreementBO;
import com.dongtong.clerk.dto.req.ClerkAgreementDTO;
import com.dongtong.clerk.manager.ClerkAgreementManager;
import com.shfc.common.result.ResultDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 合同
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/11 19:32
 * @since 1.0
 */
@Service
public class ClerkAgreementServiceImpl implements ClerkAgreementService {
    @Resource
    private ClerkAgreementManager clerkAgreementManager;


    @Override
    public ResultDO insertContract(ClerkAgreementDTO clerkAgreementDTO) throws ParseException {
        return clerkAgreementManager.insertContract(clerkAgreementDTO);
    }

    @Override
    public ResultDO<ClerkAgreementBO> getAgreementInfo(ClerkAgreementDTO clerkAgreementDTO) {
        return clerkAgreementManager.getAgreementInfo(clerkAgreementDTO);
    }
}
