package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseShopNumber;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @Package: com.dongtong.basic.dao.BaseShopNumberMapper.java
 * @Description: 商铺编号
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:09
 * version v1.0.0
 */
@Repository
public interface BaseShopNumberMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseShopNumber record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseShopNumber record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param id
     * @return com.dongtong.basic.domain.BaseShopNumber
     * @throws []
     */
    BaseShopNumber selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseShopNumber record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseShopNumber record);
    /**
     * 根据二维码编号查询未使用的二维码
     * @param shopNum
     * @return
     * @Author xiehb
     */
    List<BaseShopNumber> selectUnusedByShopNum(String shopNum);

    int insertSet(@Param("list") Set<String> list);
}