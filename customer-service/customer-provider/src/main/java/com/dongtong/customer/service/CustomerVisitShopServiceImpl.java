package com.dongtong.customer.service;

import com.dongtong.customer.constant.ErrorConstant;
import com.dongtong.customer.domain.CustomerVisitShop;
import com.dongtong.customer.dto.VisitDTO;
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.VisitShopRespDTO;
import com.dongtong.customer.manager.CustomerVisitShopManager;
import com.shfc.common.exception.AppException;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 10:58
 * @since 1.0
 */
@Service
public class CustomerVisitShopServiceImpl implements CustomerVisitShopService {

    @Resource
    private CustomerVisitShopManager customerVisitShopManager;
    @Override
    public ResultDO<Page<VisitShopRespDTO>> getVisitList(VisitShopReqDTO visitShopReqDTO, Page page) {
        ResultDO<Page<VisitShopRespDTO>> result=new ResultDO();
        Page<VisitShopRespDTO> listPage=customerVisitShopManager.getVisitList(visitShopReqDTO,page);
        result.setData(listPage);
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDO<VisitShopRespDTO> getVisitInfo(VisitShopReqDTO visitShopReqDTO) {
        ResultDO<VisitShopRespDTO> result=new ResultDO<>();
        VisitShopRespDTO visitShopRespDTO= customerVisitShopManager.getVisitInfo(visitShopReqDTO);
        result.setData(visitShopRespDTO);
        result.setSuccess(true);
        return result;
    }


    @Override
    public ResultDO updateVisitTime(VisitShopReqDTO visitShopReqDTO) throws ParseException {
        ResultDO resultDO = null;
        try{
            resultDO = customerVisitShopManager.updateVisitTime(visitShopReqDTO);
        }catch (AppException e){
            resultDO = new ResultDO();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UPDATE_VITIST_TIME_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.UPDATE_VITIST_TIME_ERROR.getMsg());
            return resultDO;
        }

        return resultDO;
    }

    @Override
    public ResultDO updateVisitStatus(VisitShopReqDTO visitShopReqDTO) {
        ResultDO resultDO = null;
        try{
        return customerVisitShopManager.updateVisitStatus(visitShopReqDTO);
        }catch (AppException e){
            resultDO = new ResultDO();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UPDATE_VITIST_TIME_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.UPDATE_VITIST_TIME_ERROR.getMsg());
            return resultDO;
        }
    }

    @Override
    public ResultDO<Integer> getDeadTimeNum(VisitShopReqDTO visitShopReqDTO) {
        return customerVisitShopManager.getDeadTimeNum(visitShopReqDTO);
    }

    @Override
    public ResultDO<List<VisitShopRespDTO>> pendingList() {
        ResultDO<List<VisitShopRespDTO>> vListResultDO=new ResultDO<>();
        List<VisitShopRespDTO> visitShopRespDTOList=customerVisitShopManager.pendingList();
        vListResultDO.setSuccess(true);
        vListResultDO.setData(visitShopRespDTOList);
        return vListResultDO;
    }

    @Override
    public ResultDO updateMeetStatus(VisitShopReqDTO visitShopReqDTO) {
        return customerVisitShopManager.updateMeetStatus(visitShopReqDTO);
    }

    @Override
    public ResultDO<Page<VisitShopRespDTO>> selectNeedByPage(VisitShopReqDTO visitShopReqDTO, Page page) {
        ResultDO<Page<VisitShopRespDTO>> result=new ResultDO();
        Page<VisitShopRespDTO> listPage=customerVisitShopManager.selectNeedByPage(visitShopReqDTO,page);
        result.setData(listPage);
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDO<CustomerVisitShop> getCustomerVisitInfoById(Long visitId) {
        ResultDO<CustomerVisitShop> resultDO = new ResultDO<>();
        if (null == visitId){
            resultDO.setErrMsg("约看id不能为空");
            return resultDO;
        }
        try {
            resultDO.setData(customerVisitShopManager.getCustomerVisitInfoById(visitId));
            resultDO.setSuccess(true);
        }catch (Exception e){
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<VisitDTO> queryLastVisit(Long customerId) {
        ResultDO<VisitDTO> resultDO = new ResultDO<>();
        if (null == customerId){
            resultDO.setErrMsg("用户id不能为空！");
            return resultDO;
        }
        try {
            resultDO.setData(customerVisitShopManager.queryLastVisit(customerId));
            resultDO.setSuccess(true);
        }catch (Exception e){
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

}
