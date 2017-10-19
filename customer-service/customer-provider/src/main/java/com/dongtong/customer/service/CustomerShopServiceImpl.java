package com.dongtong.customer.service;

import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.domain.CustomerLiaison;
import com.dongtong.customer.domain.CustomerShopCorrect;
import com.dongtong.customer.dto.req.VisitInfoReqDTO;
import com.dongtong.customer.dto.resp.ShopInfoDTO;
import com.dongtong.customer.manager.ShopManager;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.customer.service
 * @Description: 商铺模块接口
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/7 下午2:52
 * version V1.0.0
 */
@Service
public class CustomerShopServiceImpl implements CustomerShopService {
    @Autowired
    private ShopManager shopManager;

    @Override
    public ResultDO<Boolean> createCorrect(CustomerShopCorrect shopCorrect) {
        ResultDO<Boolean> resultDO=new ResultDO<>();
        Long customerId = shopCorrect.getCustomerId();
        if (null == customerId) {
            resultDO.setErrMsg("商户ID不能为空");
            return resultDO;
        }
        String checkResult  = checkCorrectInfo(shopCorrect);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        try {

            shopManager.createCorrect(shopCorrect);
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "纠错数据保存异常");
            resultDO.setErrMsg("系统异常，请联系管理员");

        }
        return resultDO;
    }

    @Override
    public ResultDO<Long> createVisit(VisitInfoReqDTO visitInfoReqDTO) {
        ResultDO<Long> resultDO=new ResultDO<>();
        Long customerId = visitInfoReqDTO.getCustomerId();
        if (null == customerId) {
            resultDO.setErrMsg("商户ID不能为空");
            return resultDO;
        }
        String checkResult  = checkCustomerVisitShop(visitInfoReqDTO);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        try {
            //判断商铺是否已经被约看
            ShopInfoDTO shopInfoDTO = shopManager.queryShopInfo(visitInfoReqDTO.getShopId() , customerId);
            if (null != shopInfoDTO.getVisitId() && shopInfoDTO.getVisitId() > 0) {
                resultDO.setErrMsg("该商铺已经被约看");
                return resultDO;
            }
            resultDO.setData(shopManager.createVisit(visitInfoReqDTO));
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "预约踩盘数据保存异常");
            resultDO.setErrMsg("系统异常，请联系管理员");

        }
        return resultDO;
    }

    @Override
    public ResultDO<Long> createCollected(CustomerCollectedShop customerCollectedShop) {
        ResultDO<Long> resultDO=new ResultDO<>();
        Long customerId = customerCollectedShop.getCustomerId();
        if (null == customerId) {
            resultDO.setErrMsg("商户ID不能为空");
            return resultDO;
        }
        Long shopId = customerCollectedShop.getShopId();
        if (null == shopId) {
            resultDO.setErrMsg("商铺ID不能为空");
            return resultDO;
        }
        try {
            //查看商铺是否已经被收藏
            Long collected = shopManager.selectCollected(shopId , customerId);
            if (null != collected) {
                resultDO.setErrMsg("店铺已经被收藏");
                return resultDO;
            }
            resultDO.setData(shopManager.createCollected(customerCollectedShop));
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "店铺收藏数据保存异常");
            resultDO.setErrMsg("系统异常，请联系管理员");

        }
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> createLiaisonRecord(CustomerLiaison customerLiaison) {
        ResultDO<Boolean> resultDO=new ResultDO<>();
        Long customerId = customerLiaison.getCustomerId();
        if (null == customerId) {
            resultDO.setErrMsg("商户ID不能为空");
            return resultDO;
        }
        String checkResult  = checkCustomerLiaison(customerLiaison);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        try {
            shopManager.createLiaisonRecord(customerLiaison);
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "拨打电话记录数据保存异常");
            resultDO.setErrMsg("系统异常，请联系管理员");

        }
        return resultDO;
    }

    @Override
    public ResultDO<ShopInfoDTO> queryShopInfo(Long shopId, Long customerId) {
        ResultDO<ShopInfoDTO> resultDO = new ResultDO<>();
        try {
            resultDO.setData(shopManager.queryShopInfo(shopId , customerId));
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "查询商铺收藏、约看信息异常");
            resultDO.setErrMsg("系统异常，请联系管理员");

        }
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> createBrowseShop(Long shopId, Long customerId) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        if (null == shopId) {
            resultDO.setErrMsg("商铺ID不能为空");
            return resultDO;
        }
        try {
            shopManager.createBrowseShop(shopId, customerId);
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "浏览记录保存异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    /**
     *check 纠错信息
     * @param shopCorrect
     * @return
     */
    private String checkCorrectInfo(CustomerShopCorrect shopCorrect) {
        if (null == shopCorrect.getShopId()) {
            return "商铺ID不能为空";
        }
        if (null == shopCorrect.getTagId()) {
            return "标签ID不能为空";
        }
        if (ValidateHelper.isEmpty(shopCorrect.getContacter())) {
            return "联系人不能为空";
        }
        if (ValidateHelper.isEmpty(shopCorrect.getContactMobile())) {
            return "联系人电话不能为空";
        }
        return null;
    }

    /**
     * check 客户约看信息
     * @param visitInfoReqDTO
     * @return
     */
    private String checkCustomerVisitShop(VisitInfoReqDTO visitInfoReqDTO) {
        if (null == visitInfoReqDTO.getShopId()) {
            return "商铺ID不能为空";
        }
        if (null == visitInfoReqDTO.getVisitTime()) {
            return "约看时间不能为空";
        }
        if (ValidateHelper.isEmpty(visitInfoReqDTO.getLinkmanName())) {
            return "联系人不能为空";
        }
        if (ValidateHelper.isEmpty(visitInfoReqDTO.getLinkmanPhone())) {
            return "联系人电话不能为空";
        }
        return null;
    }

    /**
     * check 客户联络信息
     * @param customerLiaison
     * @return
     */
    private String checkCustomerLiaison(CustomerLiaison customerLiaison) {
        if (null == customerLiaison.getShopId()) {
            return "商铺ID不能为空";
        }
        if (ValidateHelper.isEmpty(customerLiaison.getPhone())) {
            return "手机号不能为空";
        }
        return null;
    }
}
