package com.dongtong.clerk.dao;

import com.dongtong.clerk.domain.CustomerSignIn;
import com.dongtong.clerk.dto.CustomerSignInDTO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.clerk.dao.CustomerSignInMapper.java
 * @Description: 签到表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author zhanghz
 * @date 2017/05/12 09:06
 * version v1.0.0
 */
@Repository
public interface CustomerSignInMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zhanghz
     * @Date 2017/05/12 09:06
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zhanghz
     * @Date 2017/05/12 09:06
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerSignIn record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zhanghz
     * @Date 2017/05/12 09:06
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerSignIn record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zhanghz
     * @Date 2017/05/12 09:06
     * @param id
     * @return com.dongtong.clerk.domain.CustomerSignIn
     * @throws []
     */
    CustomerSignIn selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zhanghz
     * @Date 2017/05/12 09:06
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerSignIn record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zhanghz
     * @Date 2017/05/12 09:06
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerSignIn record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author zhanghz
     * @Date 2017/05/12 09:06
     * @return null
     * @throws []
     */
    List<CustomerSignIn> selectByPage(Page<CustomerSignIn> page);

    Integer selectSignInCount(Long customerId);

    /**
     * 签到历史查询
     * @param page
     * @return
     */
    List<CustomerSignInDTO> selectSignInByPage(Page<CustomerSignInDTO> page);
}