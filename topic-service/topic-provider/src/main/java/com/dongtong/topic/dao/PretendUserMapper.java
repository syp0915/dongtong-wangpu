package com.dongtong.topic.dao;

import com.dongtong.topic.domain.PretendUser;
import java.util.List;

import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.topic.dao.PretendUserMapper.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author chen xiushen
 * @date 2017/08/14 10:12
 * version v1.0.0
 */
@Repository
public interface PretendUserMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/14 10:12
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author chen xiushen
     * @Date 2017/08/14 10:12
     * @param record
     * @return int
     * @throws []
     */
    int insert(PretendUser record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author chen xiushen
     * @Date 2017/08/14 10:12
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(PretendUser record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/14 10:12
     * @param id
     * @return com.dongtong.topic.domain.PretendUser
     * @throws []
     */
    PretendUser selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author chen xiushen
     * @Date 2017/08/14 10:12
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(PretendUser record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/14 10:12
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(PretendUser record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author chen xiushen
     * @Date 2017/08/14 10:12
     * @return null
     * @throws []
     */
    List<PretendUser> selectByPage(Page<PretendUser> page);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/14 0014 10:30
     * @param id
     * @return PretendUser
     */
    PretendUser selectPretendUserById(Long id);
}