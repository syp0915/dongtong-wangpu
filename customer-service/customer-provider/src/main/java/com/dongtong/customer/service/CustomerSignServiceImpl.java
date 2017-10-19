package com.dongtong.customer.service;

import com.dongtong.customer.domain.CustomerSign;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.resp.SignRespDTO;
import com.dongtong.customer.manager.CustomerSignManager;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * 商铺签约
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 15:35
 * @since 1.0
 */
@Service
public class CustomerSignServiceImpl implements CustomerSignSerivce {

    @Resource
    private CustomerSignManager customerSignManager;
    @Override
    public ResultDO<Page<SignRespDTO>> getSignList(SignReqDTO signReqDTO, Page page) {
        ResultDO<Page<SignRespDTO>> result=new ResultDO();
        Page<SignRespDTO> listPage=customerSignManager.getSignList(signReqDTO,page);
        result.setData(listPage);
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDO<SignRespDTO> getSignInfo(SignReqDTO signReqDTO) {
        ResultDO<SignRespDTO> result=new ResultDO<>();
        SignRespDTO signRespDTO= customerSignManager.getSignInfo(signReqDTO);
        result.setData(signRespDTO);
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDO updateSignTime(SignReqDTO signReqDTO) throws ParseException {
        return customerSignManager.updateSignTime(signReqDTO);
    }

    @Override
    public ResultDO updateSignStatus(SignReqDTO signReqDTO) {
        return customerSignManager.updateSignStatus(signReqDTO);
    }

    @Override
    public ResultDO<Integer> getDeadTimeNum(SignReqDTO signReqDTO) {
        return customerSignManager.getDeadTimeNum(signReqDTO);
    }

    @Override
    public ResultDO<Page<SignRespDTO>> selectNeedByPage(SignReqDTO signReqDTO, Page page) {
        ResultDO<Page<SignRespDTO>> result=new ResultDO();
        Page<SignRespDTO> listPage=customerSignManager.selectNeedByPage(signReqDTO,page);
        result.setData(listPage);
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDO<List<CustomerSign>> pendingList() {
        ResultDO<List<CustomerSign>> resultDO=new ResultDO<>();
        List<CustomerSign> customerSignList=customerSignManager.pendingList();
        resultDO.setSuccess(true);
        resultDO.setData(customerSignList);
        return resultDO;
    }

    @Override
    public ResultDO updateSignedStatus(SignReqDTO signReqDTO) {
        return customerSignManager.updateSignedStatus(signReqDTO);
    }

    @Override
    public ResultDO<CustomerSign> getCustomerSignInfoById(Long signId) {
        ResultDO<CustomerSign> resultDO = new ResultDO<>();
        if (null == signId){
            resultDO.setErrMsg("签约id不能为空");
            return resultDO;
        }
        try {
            resultDO.setData(customerSignManager.getCustomerVisitInfoById(signId));
            resultDO.setSuccess(true);
        }catch (Exception e){
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

}
