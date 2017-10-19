package com.dongtong.clerk.service;

import com.dongtong.clerk.JunitBaseTest;
import com.dongtong.clerk.dto.req.ClerkAgreementDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/12 17:06
 * @since 1.0
 */
public class ClerkAgreementServiceTest extends JunitBaseTest {
    @Autowired
    private ClerkAgreementService clerkAgreementService;
    @Test
    public void insertContract() throws ParseException {
        ClerkAgreementDTO clerkAgreementDTO=new ClerkAgreementDTO();
        List agreementUrl=new ArrayList();
        agreementUrl.add("www.baidu.com");
        agreementUrl.add("www.dadada.com");
        agreementUrl.add("www.goolge.com");
        clerkAgreementDTO.setSignId(6L);
        clerkAgreementDTO.setShopAddress("武东路198号");
        clerkAgreementDTO.setStartTime("20170512123433");
        clerkAgreementDTO.setEndTime("20180512123433");
        clerkAgreementDTO.setAgreementUrl(agreementUrl);
        clerkAgreementDTO.setRentName("黄磊");
        clerkAgreementDTO.setRentMobile("13215445451");
        clerkAgreementDTO.setLesseeName("黄晓明");
        clerkAgreementDTO.setLesseeMobile("15023224565");
        clerkAgreementDTO.setRent(20000.0);
        clerkAgreementDTO.setRentWay(1);
        clerkAgreementDTO.setTransferFee(20000.0);
        ResultDO res=clerkAgreementService.insertContract(clerkAgreementDTO);
        System.out.println(res);
    }
    @Test
    public void getAgreementInfo() throws ParseException {
        ClerkAgreementDTO clerkAgreementDTO=new ClerkAgreementDTO();
        clerkAgreementDTO.setSignId(6L);
        ResultDO res= clerkAgreementService.getAgreementInfo(clerkAgreementDTO);
        System.out.println(res);
    }
}
