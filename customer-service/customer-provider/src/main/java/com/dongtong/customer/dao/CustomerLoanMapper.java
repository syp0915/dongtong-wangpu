package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerLoan;
import com.dongtong.customer.dto.LoadDTO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.customer.dao.CustomerLoanMapper.java
 * @Description: 申请贷款表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:44
 * version v1.0.0
 */
@Repository
public interface CustomerLoanMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:44
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:44
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerLoan record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:44
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerLoan record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:44
     * @param id
     * @return com.dongtong.customer.domain.CustomerLoan
     * @throws []
     */
    CustomerLoan selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:44
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerLoan record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:44
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerLoan record);
    /**
     * @Description: 贷款申请列表----分页
     * @Title loanList
     * @Author wky
     * @Date 2017/05/09 14:44
     * @param page
     * @return int
     * @throws []
     */
    List<LoadDTO> loanList(Page page);

    /**
     * @Description:查询最近的贷款申请
     * @Title queryLastLoad
     * @Author wky
     * @Date 2017/05/12 14:44
     * @param customerId
     * @return int
     * @throws []
     */
    LoadDTO queryLastLoad(Long customerId);
}