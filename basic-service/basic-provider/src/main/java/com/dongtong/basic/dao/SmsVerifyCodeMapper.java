package com.dongtong.basic.dao;

import com.dongtong.basic.domain.SmsVerifyCode;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.basic.dao.SmsVerifyCodeMapper.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author Jianguo Li
 * @date 2017/05/08 15:09
 * version v1.0.0
 */
@Repository
public interface SmsVerifyCodeMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/08 15:09
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author Jianguo Li
     * @Date 2017/05/08 15:09
     * @param record
     * @return int
     * @throws []
     */
    int insert(SmsVerifyCode record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author Jianguo Li
     * @Date 2017/05/08 15:09
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(SmsVerifyCode record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/08 15:09
     * @param id
     * @return com.dongtong.basic.domain.SmsVerifyCode
     * @throws []
     */
    SmsVerifyCode selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author Jianguo Li
     * @Date 2017/05/08 15:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(SmsVerifyCode record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/08 15:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(SmsVerifyCode record);
}