package com.dongtong.customer.service;

import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.domain.CustomerSign;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.resp.SignRespDTO;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/11 16:48
 * @since 1.0
 */
public class CustomerSignServiceTest extends JunitBaseTest{
    @Autowired
    private CustomerSignSerivce customerSignSerivce;
    @Test
    public void getSignList(){
        SignReqDTO signReqDTO=new SignReqDTO();
        signReqDTO.setClerkId(1L);
        Page page=new Page();
        page.setPageNumber(1);
        page.setPageSize(2);
        ResultDO<Page<SignRespDTO>>res =customerSignSerivce.getSignList( signReqDTO,page);
        System.out.println(res.toString());
    }
    @Test
    public void getSignInfo(){
        SignReqDTO signReqDTO=new SignReqDTO();
        signReqDTO.setId(1L);
        ResultDO<SignRespDTO> res=customerSignSerivce.getSignInfo(signReqDTO);
        System.out.println(res.toString());
    }
    @Test
    public void updateSignTime() throws ParseException {
        SignReqDTO signReqDTO=new SignReqDTO();
        signReqDTO.setId(1L);
        signReqDTO.setSignTime("2017-0518-21:22:34");
        ResultDO res=customerSignSerivce.updateSignTime( signReqDTO  );
        System.out.println(res.toString());
    }
    @Test
    public void updateSignStatus(){
        SignReqDTO signReqDTO=new SignReqDTO();
        signReqDTO.setId(1L);
        signReqDTO.setTagId(1L);
        signReqDTO.setCancelCause("2131");
        ResultDO res=customerSignSerivce.updateSignStatus( signReqDTO  );
        System.out.println(res.toString());
    }
    @Test
    public void pendingList(){
        ResultDO res=customerSignSerivce.pendingList();
        System.out.println(res.toString());
    }

    @Test
    public void testGetCustomerSignInfoById(){
        ResultDO<CustomerSign> resultDO = customerSignSerivce.getCustomerSignInfoById(2L);
        System.out.println(resultDO);
    }
}
