package com.dongtong.customer.service;

import com.dongtong.customer.domain.CustomerVisitShop;
import com.dongtong.customer.dto.VisitDTO;
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.VisitShopRespDTO;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;

import java.text.ParseException;
import java.util.List;

/**
 * 约看
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 10:42
 * @since 1.0
 */
public interface CustomerVisitShopService {
    /**
     * 约看列表接口
     * @param visitShopReqDTO
     * @param page
     * @return
     */
    ResultDO<Page<VisitShopRespDTO>> getVisitList(VisitShopReqDTO visitShopReqDTO , Page page);

    /**
     * 约看详情
     * @param visitShopReqDTO
     * @return
     */
    ResultDO<VisitShopRespDTO> getVisitInfo(VisitShopReqDTO visitShopReqDTO );

    /**
     * 修改约看时间
     * @param visitShopReqDTO
     * @return
     */
    ResultDO updateVisitTime(VisitShopReqDTO visitShopReqDTO ) throws ParseException;

    /**
     * 约看取消
     * @param visitShopReqDTO
     * @return
     */
    ResultDO updateVisitStatus(VisitShopReqDTO visitShopReqDTO );
    /**
     * 获取过期数量
     * @param visitShopReqDTO
     * @return
     */
    ResultDO<Integer>getDeadTimeNum(VisitShopReqDTO visitShopReqDTO );

    /**
     * 约看待办列表
     * @param visitShopReqDTO
     * @param page
     * @return
     */
    ResultDO<Page<VisitShopRespDTO>> selectNeedByPage(VisitShopReqDTO visitShopReqDTO , Page page);

    /**
     * 获取所有待办列表
     *
     * @return
     */
    ResultDO<List<VisitShopRespDTO>> pendingList();

    /**
     * 修改约看状态
     * @param visitShopReqDTO
     * @return
     */
    ResultDO updateMeetStatus(VisitShopReqDTO visitShopReqDTO );

    /**
     * 根据约看id查询约看信息
     * @param visitId
     * @Author zhoumin
     * @return
     */
    ResultDO<CustomerVisitShop> getCustomerVisitInfoById(Long visitId);

    /**
     * 查看用户最近一次约看
     * @Author zhoumin
     * @param customerId
     * @return
     */
    ResultDO<VisitDTO> queryLastVisit(Long customerId);
}
