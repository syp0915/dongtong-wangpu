package com.dongtong.app.ao;

import com.dongtong.app.model.NeedDealList;
import com.dongtong.app.utils.AuthSessionUtils;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.clerk.bo.ClerkHintBO;
import com.dongtong.clerk.dto.ClerkHintDTO;
import com.dongtong.clerk.service.ClerkHintService;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.SignRespDTO;
import com.dongtong.customer.dto.resp.VisitShopRespDTO;
import com.dongtong.customer.service.CustomerSignSerivce;
import com.dongtong.customer.service.CustomerVisitShopService;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 待办事项列表
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 18:05
 * @since 1.0
 */
@Service
public class BackLogAO {

    @Resource
    private ClerkHintService clerkHintService;
    @Resource
    private CustomerVisitShopService customerVisitShopService;
    @Resource
    private CustomerSignSerivce customerSignSerivce;

    public ResultDO<NeedDealList> getNeedDealList() {
        ResultDO <NeedDealList>res=new ResultDO();
        Page page=new Page();
        page.setPageNumber(1);
        page.setPageSize(3);
        /*业务员id*/
        Long clerkId=(Long) HttpSessionUtils.getObject(AuthSessionUtils.APP_CURRENT_USER_ID);
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setOwnerId(clerkId);
        NeedDealList needDealList=new NeedDealList();
        VisitShopReqDTO visitShopReqDTO=new VisitShopReqDTO();
        visitShopReqDTO.setClerkId(clerkId);
        SignReqDTO signReqDTO=new SignReqDTO();
        signReqDTO.setClerkId(clerkId);
        /*查询待实勘线索列表*/
        ResultDO <Page<ClerkHintBO>> HintData= clerkHintService.selectNeedByPage(clerkHintDTO,page);
        /*查询过期数量*/
        ResultDO<Integer> clerkCount=clerkHintService.getDeadTimeCount(clerkHintDTO);
        needDealList.setShopRentData(HintData.getData().getData()==null?null:HintData.getData().getData());
        needDealList.setClerkHintTotalSize(HintData.getData().getTotalSize()==null?0:HintData.getData().getTotalSize());
        needDealList.setHintDeadTimeSize(clerkCount.getData()==null?0:clerkCount.getData());
        /*查询待约看线索列表*/
        ResultDO <Page<VisitShopRespDTO>> meetData= customerVisitShopService.selectNeedByPage(visitShopReqDTO,page);
        /*查询过期数量*/
        ResultDO<Integer> visitCount=customerVisitShopService.getDeadTimeNum(visitShopReqDTO);
        needDealList.setMeetData(meetData.getData().getData()==null?null:meetData.getData().getData());
        needDealList.setVisitShopTotalSize(meetData.getData().getTotalSize()==null?0:meetData.getData().getTotalSize());
        needDealList.setVisitDeadTimeSize(visitCount.getData()==null?0:visitCount.getData());
        /*查询待签约列表*/
        ResultDO<Page<SignRespDTO>> signData=customerSignSerivce.selectNeedByPage(signReqDTO,page);
        /*查询过期数量*/
        ResultDO<Integer> signCount=customerSignSerivce.getDeadTimeNum(signReqDTO);
        needDealList.setSignData(signData.getData().getData()==null?null:signData.getData().getData());
        needDealList.setSignTotalSize(signData.getData().getTotalSize()==null?0:signData.getData().getTotalSize());
        needDealList.setSignDeadTimeSize(signCount.getData()==null?0:signCount.getData());
        /*插入needDealList*/
        res.setSuccess(true);
        res.setData(needDealList);
        return res;

    }
}
