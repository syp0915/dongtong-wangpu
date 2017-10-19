package com.dongtong.customer.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.dto.LoadDTO;
import com.dongtong.customer.dto.LoanStatistics;
import com.dongtong.customer.query.BaseQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-09 19:12
 **/
public class CustomerLoanServiceTest extends JunitBaseTest {

    @Autowired
    private CustomerLoanService customerLoanService;

    @Test
    public void testLoanList(){
        BaseQuery query=new BaseQuery();
//        query.setPageNumber(1);
//        query.setPageSize(4);
        ResultDO<Page<LoadDTO>> resultDO=customerLoanService.loanList(query,1L);
        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testApplyLoad(){
        LoadDTO dto=new LoadDTO();
        dto.setCustomerId(1L);
        dto.setLoanLimit("1000");
        dto.setLoanMaturity("3");
        dto.setContactName("李敏1");
        dto.setContactMobile("123123123");
        dto.setSubscribeTime("2017-05-10 10:01:01");
        ResultDO<String> resultDO=customerLoanService.applyLoad(dto);
        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testStatistics(){
        ResultDO<LoanStatistics> resultDO=customerLoanService.statistics();
        System.out.println(JSON.toJSONString(resultDO));
    }

}
