package com.dongtong.customer.service;

import com.dongtong.customer.dto.LoadDTO;
import com.dongtong.customer.dto.LoanStatistics;
import com.dongtong.customer.query.BaseQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * 用户贷款
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-09 18:43
 **/
public interface CustomerLoanService {

    /**
     * @Description: 贷款申请列表
     * @Title loanList
     * @Author  wuky
     * @Date 2017/5/9 17:17
     * @param
     * @return ResultDO<LoanRespInfoDTO>
     * @throws
     */
    ResultDO<Page<LoadDTO>> loanList(BaseQuery query, Long customerId);

    /**
     * @Description: 贷款申请
     * @Title applyLoad
     * @Author  wuky
     * @Date 2017/5/10 9:17
     * @param
     * @return ResultDO<LoanRespInfoDTO>
     * @throws
     */
    ResultDO<String> applyLoad(LoadDTO dto);

    /**
     * @Description: 查询最近贷款申请
     * @Title queryLastLoan
     * @Author  wuky
     * @Date 2017/5/12 9:17
     * @param
     * @return ResultDO<LoanRespInfoDTO>
     * @throws
     */
    LoadDTO queryLastLoan(Long customerId);
    /**
     * @Description: 贷款统计数据
     * @Title Statistics
     * @Author  wuky
     * @Date 2017/5/15 9:17
     * @param
     * @return ResultDO<LoanStatistics>
     * @throws
     */
    ResultDO<LoanStatistics> statistics();

}
