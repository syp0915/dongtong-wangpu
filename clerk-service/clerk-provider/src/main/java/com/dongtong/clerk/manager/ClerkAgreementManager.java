package com.dongtong.clerk.manager;

import com.dongtong.clerk.bo.ClerkAgreementBO;
import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.dao.ClerkAgreementImgMapper;
import com.dongtong.clerk.dao.ClerkAgreementMapper;
import com.dongtong.clerk.domain.ClerkAgreement;
import com.dongtong.clerk.domain.ClerkAgreementImg;
import com.dongtong.clerk.dto.req.ClerkAgreementDTO;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.service.CustomerSignSerivce;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 合同
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/11 19:34
 * @since 1.0
 */
@Service
public class ClerkAgreementManager {
    @Resource
    private ClerkAgreementMapper clerkAgreementMapper;
    @Resource
    private ClerkAgreementImgMapper clerkAgreementImgMapper;
    @Resource
    private CustomerSignSerivce customerSignSerivce;
    @Transactional
    public ResultDO insertContract(ClerkAgreementDTO clerkAgreementDTO) throws ParseException {
        ResultDO res=new ResultDO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ClerkAgreement clerkAgreement=new ClerkAgreement();
        clerkAgreement.setShopAddress(clerkAgreementDTO.getShopAddress());
        clerkAgreement.setStartTime(sdf.parse(clerkAgreementDTO.getStartTime()));
        clerkAgreement.setEndTime(sdf.parse(clerkAgreementDTO.getEndTime()));
        clerkAgreement.setRent(BigDecimal.valueOf(clerkAgreementDTO.getRent()));
        clerkAgreement.setTransferFee(BigDecimal.valueOf(clerkAgreementDTO.getTransferFee()));
        clerkAgreement.setRentName(clerkAgreementDTO.getRentName());
        clerkAgreement.setRentMoblie(clerkAgreementDTO.getRentMobile());
        clerkAgreement.setLesseeMoblie(clerkAgreementDTO.getLesseeMobile());
        clerkAgreement.setLesseeName(clerkAgreementDTO.getLesseeName());
        clerkAgreement.setRentWay(clerkAgreementDTO.getRentWay());
        clerkAgreement.setSignId(clerkAgreementDTO.getSignId());
        clerkAgreement.setDistrictId(clerkAgreementDTO.getDistrictId());
        clerkAgreement.setDistrictName(clerkAgreementDTO.getDistrictName());
        clerkAgreement.setBlockName(clerkAgreementDTO.getBlockName());
        clerkAgreement.setBlockId(clerkAgreementDTO.getBlockId());
        clerkAgreement.setCreater(clerkAgreementDTO.getClerkId());
        try{
            clerkAgreementMapper.insert(clerkAgreement);
            List<String> list=clerkAgreementDTO.getAgreementUrl();
            ClerkAgreementImg img=new ClerkAgreementImg();
            int i=0;
            for (String agreementrl:list) {
                i++;
                img.setAgreementId(clerkAgreement.getId());
                img.setImgUrl(agreementrl);
                img.setIsCover(0);
                img.setImgIndex(i);
                clerkAgreementImgMapper.insert(img);
            };
                SignReqDTO signReqDTO=new SignReqDTO();
            /*录入合同已签约*/
                signReqDTO.setStatus(2);
                signReqDTO.setId(clerkAgreementDTO.getSignId());
                signReqDTO.setAgreementId(clerkAgreement.getId());
                res.setSuccess(true);
                res=customerSignSerivce.updateSignedStatus(signReqDTO);
                if(!res.isSuccess()){
                    Logger.error(ClerkAgreementManager.class,res.getErrMsg());
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    res.setErrCode(ErrorConstant.UPDATE_FAIL.getCode());
                    res.setErrMsg(ErrorConstant.UPDATE_FAIL.getMsg());
                }

        }catch(Exception e){
            Logger.error(ClerkAgreementManager.class,e.getMessage());
            e.printStackTrace();
            res.setSuccess(false);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            res.setErrCode(ErrorConstant.UPDATE_FAIL.getCode());
            res.setErrMsg(ErrorConstant.UPDATE_FAIL.getMsg());
        }
        return res;
    }

    public ResultDO<ClerkAgreementBO> getAgreementInfo(ClerkAgreementDTO clerkAgreementDTO) {
        ResultDO res=new ResultDO();
        Long signId=clerkAgreementDTO.getSignId();
        ClerkAgreementBO clerkAgreementBO= clerkAgreementMapper.selectBySignId(signId);
        res.setData(clerkAgreementBO);
        res.setSuccess(true);
        return res;
    }
}
