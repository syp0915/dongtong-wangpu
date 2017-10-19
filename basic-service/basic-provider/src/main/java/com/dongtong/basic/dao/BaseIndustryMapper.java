package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseIndustry;
import com.dongtong.basic.dto.IndustryInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.basic.dao.BaseIndustryMapper.java
 * @Description: 业态
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:06
 * version v1.0.0
 */
@Repository
public interface BaseIndustryMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:06
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:06
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseIndustry record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:06
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseIndustry record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:06
     * @param id
     * @return com.dongtong.basic.domain.BaseIndustry
     * @throws []
     */
    BaseIndustry selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:06
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseIndustry record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:06
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseIndustry record);

    /**
     * @Description: 查询业态经营范围
     * @Title queryIndustry
     * @Author wuky
     * @Date 2017/05/08 14:06
     * @param
     * @return int
     * @throws []
     */
    List<IndustryInfoDTO> queryIndustry();

    /**
     * @Description: 查询业态经营范围
     * @Title industryList
     * @Author wuky
     * @Date 2017/05/08 14:06
     * @param
     * @return int
     * @throws []
     */
    List<IndustryInfoDTO> industryList();
}