package com.dongtong.customer.service;

import com.dongtong.customer.dto.LoadDTO;
import com.dongtong.customer.dto.LoanStatistics;
import com.dongtong.customer.manager.CustomerLoanManager;
import com.dongtong.customer.query.BaseQuery;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-09 18:46
 **/
@Service
public class CustomerLoanServiceImpl implements CustomerLoanService{
    @Autowired
    private CustomerLoanManager customerLoanManager;

    /**
     * @Description: 贷款申请列表
     * @Title loanList
     * @Author  wuky
     * @Date 2017/5/9 17:17
     * @param
     * @return ResultDO<LoanRespInfoDTO>
     * @throws
     */
    @Override
    public ResultDO<Page<LoadDTO>> loanList(BaseQuery query,Long customerId) {
        ResultDO<Page<LoadDTO>> resultDO=new ResultDO<>();

        if(customerId==null){
            resultDO.setErrMsg("用户Id不能为空");
            return resultDO;
        }

        Page<LoadDTO> page=new Page<>(query.getPageNumber(),query.getPageSize());
        page.setQuery(customerId);


        resultDO.setData(customerLoanManager.loanList(page));
        resultDO.setSuccess(true);
        return resultDO;
    }


    /**
     * @Description: 贷款申请
     * @Title applyLoad
     * @Author  wuky
     * @Date 2017/5/10 9:17
     * @param
     * @return ResultDO<LoanRespInfoDTO>
     * @throws
     */
    @Override
    public ResultDO<String> applyLoad(LoadDTO dto) {
        ResultDO<String> resultDO=new ResultDO<String>();

        if(dto.getCustomerId()==null){
            resultDO.setErrMsg("用户Id不能为空");
            return resultDO;
        }

        if(ValidateHelper.isEmpty(dto.getLoanLimit())){
            resultDO.setData("贷款额度不能为空");
            return resultDO;
        }

        if(ValidateHelper.isEmpty(dto.getLoanMaturity())){
            resultDO.setData("贷款期限不能为空");
            return resultDO;
        }

        if(ValidateHelper.isEmpty(dto.getContactName())){
            resultDO.setErrMsg("联系人不能为空");
            return resultDO;
        }

        if(ValidateHelper.isEmpty(dto.getContactMobile())){
            resultDO.setErrMsg("联系电话不能为空");
            return resultDO;
        }

        /*if(ValidateHelper.isEmpty(dto.getSubscribeTime())){
            resultDO.setErrMsg("约见时间不能为空");
            return resultDO;
        }*/

        customerLoanManager.applyLoan(dto);

        //约看统计增加,每当有一人发布找贷款服务，则增加1/2/3（随机）
        RedisUtil.set("CUSTOMER_LOAN_STATISTICS",Integer.parseInt(RedisUtil.get("CUSTOMER_LOAN_STATISTICS").toString())+ (Integer)new Random().nextInt(3));

        resultDO.setSuccess(true);
        return resultDO;

    }

    @Override
    public LoadDTO queryLastLoan(Long customerId) {

        return customerLoanManager.queryLastLoad(customerId);


    }

    @Override
    public ResultDO<LoanStatistics> statistics() {
        ResultDO<LoanStatistics> resultDO=new ResultDO<>();
        LoanStatistics loanStatistics=new LoanStatistics();
        //获取redis中贷款人数
        if(RedisUtil.get("CUSTOMER_LOAN_STATISTICS")==null){
            RedisUtil.set("CUSTOMER_LOAN_STATISTICS",12);//默认是十二
        }
        //获取人数
        loanStatistics.setPeople(Integer.parseInt(RedisUtil.get("CUSTOMER_LOAN_STATISTICS").toString()));
        loanStatistics.setSum(30);

        resultDO.setData(loanStatistics);
        resultDO.setSuccess(true);

        return resultDO;
    }


}
