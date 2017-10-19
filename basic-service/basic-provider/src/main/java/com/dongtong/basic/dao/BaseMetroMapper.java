package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseMetro;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.basic.dao.BaseMetroMapper.java
 * @Description: 地铁
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:10
 * version v1.0.0
 */
@Repository
public interface BaseMetroMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseMetro record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseMetro record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param id
     * @return com.dongtong.basic.domain.BaseMetro
     * @throws []
     */
    BaseMetro selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseMetro record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseMetro record);
    /**
     * @Description: 查询线路
     * @Title selectByCityId
     * @Author wuky
     * @Date 2017/05/04 16:10
     * @param cityId
     * @return int
     * @throws []
     */
    List<BaseMetro> selectByCityId(String cityId);

}