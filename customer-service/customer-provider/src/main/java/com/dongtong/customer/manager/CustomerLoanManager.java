package com.dongtong.customer.manager;

import com.dongtong.customer.dao.CustomerLoanMapper;
import com.dongtong.customer.domain.CustomerLoan;
import com.dongtong.customer.dto.LoadDTO;
import com.dongtong.customer.enums.LoanStatus;
import com.shfc.common.date.DateUtils;
import com.shfc.common.exception.AppException;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-09 18:48
 **/
@Service
public class CustomerLoanManager {
    @Autowired(required = false)
    private CustomerLoanMapper customerLoanMapper;

    public Page<LoadDTO> loanList(Page page){

        customerLoanMapper.loanList(page);
        return page;
    }
    @Transactional(rollbackFor = AppException.class)
    public void applyLoan(LoadDTO dto){
        CustomerLoan customerLoan=new CustomerLoan();
        customerLoan.setCustomerId(String.valueOf(dto.getCustomerId()));
        customerLoan.setLoanLimit(dto.getLoanLimit());
        customerLoan.setLoanMaturity(dto.getLoanMaturity());
        customerLoan.setMaturityUnit("month");
        customerLoan.setLinkmanName(dto.getContactName());
        customerLoan.setLinkmanPhone(dto.getContactMobile());
        customerLoan.setStatus(LoanStatus.ACCEPTED.getValue());
        customerLoan.setSubscribeTime(DateUtils.string2Date(dto.getSubscribeTime()));

        customerLoanMapper.insert(customerLoan);

    }

    public LoadDTO queryLastLoad(Long customerId){
        return customerLoanMapper.queryLastLoad(customerId);
    }
}
