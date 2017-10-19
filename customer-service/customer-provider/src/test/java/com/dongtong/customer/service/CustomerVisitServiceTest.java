package com.dongtong.customer.service;

import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.domain.CustomerVisitShop;
import com.dongtong.customer.dto.VisitDTO;
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.VisitShopRespDTO;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * 约看相关接口
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 14:34
 * @since 1.0
 */
public class CustomerVisitServiceTest extends JunitBaseTest {
    @Autowired
    private CustomerVisitShopService customerVisitShopService;

    @Test
    public void getVisitList() {
        VisitShopReqDTO visitShopReqDTO = new VisitShopReqDTO();
        visitShopReqDTO.setClerkId(1L);
        Page page = new Page();
        page.setPageNumber(1);
        page.setPageSize(2);
        ResultDO<Page<VisitShopRespDTO>> res = customerVisitShopService.getVisitList(visitShopReqDTO, page);
        System.out.println(res.toString());
    }

    @Test
    public void getVisitInfo() {
        VisitShopReqDTO visitShopReqDTO = new VisitShopReqDTO();
        visitShopReqDTO.setId(1L);
        ResultDO<VisitShopRespDTO> res = customerVisitShopService.getVisitInfo(visitShopReqDTO);
        System.out.println(res.toString());
    }

    @Test
    public void updateVisitTime() throws ParseException {
        VisitShopReqDTO visitShopReqDTO = new VisitShopReqDTO();
        visitShopReqDTO.setId(1L);
        visitShopReqDTO.setVisitTime("2017-05-17 16:52:00");
        ResultDO res = customerVisitShopService.updateVisitTime(visitShopReqDTO);
        System.out.println(res.toString());
    }

    @Test
    public void updateVisitStatus() {
        VisitShopReqDTO visitShopReqDTO = new VisitShopReqDTO();
        visitShopReqDTO.setId(1L);
        visitShopReqDTO.setTagId(0L);
        visitShopReqDTO.setCancelCause("222");
        ResultDO res = customerVisitShopService.updateVisitStatus(visitShopReqDTO);
        System.out.println(res.toString());
    }

    @Test
    public void pendingList() {
        ResultDO res = customerVisitShopService.pendingList();
        System.out.println(res.toString());

    }

    @Test
    public void testGetCustomerVisitInfoById(){
        ResultDO<CustomerVisitShop> resultDO = customerVisitShopService.getCustomerVisitInfoById(1L);
        System.out.println(resultDO.toString());
    }

    @Test
    public void testQueryLastVisit(){
        ResultDO<VisitDTO> resultDO = customerVisitShopService.queryLastVisit(30L);
        System.out.println(resultDO.toString());
    }
}