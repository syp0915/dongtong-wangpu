package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseBlock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.basic.dao.BaseBlockMapper.java
 * @Description: 板块
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:09
 * version v1.0.0
 */
@Repository
public interface BaseBlockMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:09
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/04 16:09
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseBlock record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/04 16:09
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseBlock record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:09
     * @param id
     * @return com.dongtong.basic.domain.BaseBlock
     * @throws []
     */
    BaseBlock selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/04 16:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseBlock record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseBlock record);

    List<BaseBlock> selectByDistrictId(Long id);
}