package com.dongtong.customer.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.dto.CustomerSignDTO;
import com.dongtong.customer.dto.resp.LoginRespDTO;
import com.dongtong.customer.query.CustomerServiceQuery;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/11 下午1:40.
 */
public class CustomerServiceTest extends JunitBaseTest{

    @Autowired(required = false)
    private CustomerService customerService;

    @Test
    public void tesCustomerLogin(){
        String userPhone = "18512114337";
        String smsVerifyCode = "8579";
        Long messageId = 2L;
        String picVerifyCode = "8890";
        Long picVerifyId = 2L;
        String inviteCode = "5678";
        Integer osType = 0;
        String deviceId = "FDSFSDGERQ$T748923748923789432DSSFS";
        ResultDO<LoginRespDTO> resultDO = customerService.customerLogin(userPhone, smsVerifyCode, messageId, picVerifyCode, picVerifyId, inviteCode, osType, deviceId);
        System.out.println(JSON.toJSONString("result--------------->" + JSON.toJSONString(resultDO)));
    }

    @Test
    public void testApplySign(){
        CustomerSignDTO dto=new CustomerSignDTO();
        dto.setCustomerId(1L);
        dto.setShopId(1L);
        dto.setContactName("李敏");
        dto.setContactMobile("12313");
        dto.setSubscribeTime("2017-05-12 18:00:00");

        ResultDO<Long> resultDO=customerService.applySign(dto,1L,"","武东路198号");
        System.out.println(JSON.toJSONString("result--------------->" + JSON.toJSONString(resultDO)));
    }

    @Test
    public void testApplyVisit(){
        CustomerSignDTO dto=new CustomerSignDTO();
        dto.setCustomerId(1L);
        dto.setShopId(1L);
        dto.setContactName("李敏1");
        dto.setContactMobile("1");
        dto.setSubscribeTime("2017-06-12 19:00:00");

        ResultDO<Long> resultDO=customerService.applyVisit(dto,1L,"","武东路198号");
        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testCancelVisit(){
        ResultDO<Boolean> resultDO=customerService.cancelVisit(10L,5L);
        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testStatistics(){
        System.out.println(customerService.visitStatistics());

    }
    @Test
    public void findCustomerInfoByCustomerId(){
        ResultDO<Customer> resultDO=customerService.findCustomerInfoByCustomerId(1L);
        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testClerkLogout(){
        ResultDO resultDO = customerService.customerLogout(1L, "DADAFSARE5234324DFSFDSFDSF43");
        System.out.println("result--------------->" + JSON.toJSONString(resultDO));
    }

    @Test
    public void testUpdateDeviceId(){
        ResultDO resultDO = customerService.updateDeviceId(1L, "DADAFSARE5234324DFSFDSFDSF43");
        System.out.println("result--------------->" + JSON.toJSONString(resultDO));
    }

    @Test
    public void testRevokedService(){
        CustomerServiceQuery customerServiceQuery = new CustomerServiceQuery();
        customerServiceQuery.setId(141L);
        customerServiceQuery.setType(1);
        ResultDO resultDO = customerService.revokedService(customerServiceQuery);
        System.out.println("result--------------->" + JSON.toJSONString(resultDO));
    }
}
