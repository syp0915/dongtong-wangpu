package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.exception.AppWebException;
import com.dongtong.app.utils.AuthSessionUtils;
import com.dongtong.app.utils.DateValidate;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.app.utils.Validate;
import com.dongtong.clerk.bo.ClerkAgreementBO;
import com.dongtong.clerk.dto.req.ClerkAgreementDTO;
import com.dongtong.clerk.service.ClerkAgreementService;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.service.CustomerSignSerivce;
import com.shfc.common.result.ResultDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 合同
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 10:12
 * @since 1.0
 */
@Service
public class ClerkAgreementAO {
    @Resource
    private ClerkAgreementService clerkAgreementService;
    @Resource
    private CustomerSignSerivce customerSignSerivce;
    public ResultDO insertContract(ClerkAgreementDTO clerkAgreementDTO) throws ParseException, AppWebException {
        ResultDO res=new ResultDO();
        ResultDO resp=new ResultDO();
        Validate.validateObject(clerkAgreementDTO);
        if(!DateValidate.isValidDate(clerkAgreementDTO.getStartTime(),"yyyy-MM-dd")){
            res.setErrMsg(ErrorConstant.ERROR_DATE_FORMAT.getMsg());
            res.setErrCode(ErrorConstant.ERROR_DATE_FORMAT.getCode());
            return res;
        }
        if(!DateValidate.isValidDate(clerkAgreementDTO.getEndTime(),"yyyy-MM-dd")){
            res.setErrMsg(ErrorConstant.ERROR_DATE_FORMAT.getMsg());
            res.setErrCode(ErrorConstant.ERROR_DATE_FORMAT.getCode());
            return res;
        }
        clerkAgreementDTO.setClerkId((Long) HttpSessionUtils.getObject(AuthSessionUtils.APP_CURRENT_USER_ID));
        res= clerkAgreementService.insertContract(clerkAgreementDTO);
        return res;
    }

    public ResultDO<ClerkAgreementBO> getAgreementInfo(ClerkAgreementDTO clerkAgreementDTO) {
        ResultDO res=new ResultDO();
        if(clerkAgreementDTO.getSignId()==null){
            res.setErrCode(ErrorConstant.NULL_SIGNID.getCode());
            res.setErrMsg(ErrorConstant.NULL_SIGNID.getMsg());
            return res;
        }
        return clerkAgreementService.getAgreementInfo(clerkAgreementDTO);
    }
}
