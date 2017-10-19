package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseMetroStation;
import com.dongtong.basic.dto.MetroStationInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.basic.dao.BaseMetroStationMapper.java
 * @Description: 地铁站表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:10
 * version v1.0.0
 */
@Repository
public interface BaseMetroStationMapper {
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
    int insert(BaseMetroStation record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseMetroStation record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param id
     * @return com.dongtong.basic.domain.BaseMetroStation
     * @throws []
     */
    BaseMetroStation selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseMetroStation record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseMetroStation record);
    /**
     * @Description: 根据地铁线路id查询地铁站
     * @Title selectByMetroId
     * @Author wuky
     * @Date 2017/05/04 16:10
     * @param metroid
     * @return int
     * @throws []
     */
    List<MetroStationInfoDTO> selectByMetroId(long metroid);
}