package com.dongtong.customer.service;

import com.dongtong.customer.domain.CustomerSign;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.resp.SignRespDTO;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;

import java.text.ParseException;
import java.util.List;

/**
 * 商铺签约
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 15:35
 * @since 1.0
 */
public interface CustomerSignSerivce {
    /**
     * 签约列表
     * @param signReqDTO
     * @param page
     * @return
     */
    ResultDO<Page<SignRespDTO>> getSignList(SignReqDTO signReqDTO , Page page);

    /**
     * 签约详情
     * @param signReqDTO
     * @return
     */
    ResultDO<SignRespDTO> getSignInfo(SignReqDTO signReqDTO );

    /**
     * 修改签约时间
     * @param signReqDTO
     * @return
     */
    ResultDO updateSignTime(SignReqDTO signReqDTO  ) throws ParseException;

    /**
     * 签约取消
     * @param signReqDTO
     * @return
     */
    ResultDO updateSignStatus(SignReqDTO signReqDTO  );

    /**
     * 获取过期数量
     * @param signReqDTO
     * @return
     */
    ResultDO<Integer>getDeadTimeNum(SignReqDTO signReqDTO );

    /**
     * 获取待办列表
     * @param signReqDTO
     * @param page
     * @return
     */
    ResultDO<Page<SignRespDTO>> selectNeedByPage(SignReqDTO signReqDTO , Page page);

    /**
     * 获取所有业务员待办列表
     * @return
     */
    ResultDO<List<CustomerSign>> pendingList();

    /**
     * 修改签约状态
     * @param signReqDTO
     * @return
     */

    ResultDO updateSignedStatus(SignReqDTO signReqDTO  );

    /**
     *根据id查询签约信息
     * @param signId
     * @Author zhoumin
     * @return
     */
    ResultDO<CustomerSign> getCustomerSignInfoById(Long signId);
}
