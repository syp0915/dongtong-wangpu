package com.dongtong.clerk.service;

import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.domain.ClerkRemark;
import com.dongtong.clerk.dto.ClerkRemarkDTO;
import com.dongtong.clerk.manager.ClerkRemarkManager;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.clerk.service.ClerkRemarkServiceImpl
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/8 9:51
 * version V1.0.0
 */
@Service
@Slf4j
public class ClerkRemarkServiceImpl implements ClerkRemarkService {

    @Autowired
    private ClerkRemarkManager clerkRemarkManager;

    /**
     * @description 新增备注
     * @package com.dongtong.clerk.service
     * @author liaozm
     * @date 2017/8/8 10:06
     * @params
     * @return
     */
    @Override
    public ResultDO<Boolean> addRemark(ClerkRemarkDTO clerkRemarkDTO) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();

        if (ValidateHelper.isEmpty(clerkRemarkDTO.getId())
                || ValidateHelper.isEmpty(clerkRemarkDTO.getType())
                || ValidateHelper.isEmpty(clerkRemarkDTO.getContent())
                || ValidateHelper.isEmpty(clerkRemarkDTO.getClerkId())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        ClerkRemark clerkRemark = new ClerkRemark();
        clerkRemark.setType(clerkRemarkDTO.getType());
        clerkRemark.setContent(clerkRemarkDTO.getContent());
        clerkRemark.setDataId(clerkRemarkDTO.getId()+"");
        clerkRemark.setClerkId(clerkRemarkDTO.getClerkId());
        Boolean isSuccess = clerkRemarkManager.addRemark(clerkRemark);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(isSuccess);
        return resultDO;
    }
}
