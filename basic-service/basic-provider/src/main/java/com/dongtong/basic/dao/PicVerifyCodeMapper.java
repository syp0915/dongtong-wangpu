package com.dongtong.basic.dao;

import com.dongtong.basic.domain.PicVerifyCode;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.basic.dao.PicVerifyCodeMapper.java
 * @Description: 图片验证码表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author Jianguo Li
 * @date 2017/05/08 18:56
 * version v1.0.0
 */
@Repository
public interface PicVerifyCodeMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/08 18:56
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author Jianguo Li
     * @Date 2017/05/08 18:56
     * @param record
     * @return int
     * @throws []
     */
    int insert(PicVerifyCode record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author Jianguo Li
     * @Date 2017/05/08 18:56
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(PicVerifyCode record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/08 18:56
     * @param id
     * @return com.dongtong.basic.domain.PicVerifyCode
     * @throws []
     */
    PicVerifyCode selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author Jianguo Li
     * @Date 2017/05/08 18:56
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(PicVerifyCode record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/08 18:56
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(PicVerifyCode record);
}