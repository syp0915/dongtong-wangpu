package com.dongtong.clerk.manager;

import com.dongtong.clerk.dao.ClerkMapper;
import com.dongtong.clerk.domain.Clerk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 下午3:21.
 */
@Service
public class ClerkManager {

    @Autowired(required = false)
    private ClerkMapper clerkMapper;

    /**
     * 根据id查询业务员信息
     *
     * @param clerkId
     * @return
     */
    public Clerk getClerkInfoById(Long clerkId) {
        return clerkMapper.selectByPrimaryKey(clerkId);
    }

    public int updateClerkInfo(Clerk clerk){
        return clerkMapper.updateByPrimaryKey(clerk);
    }

    public Clerk getClerkInfoByUserPhone(String userPhone) {
        return clerkMapper.getClerkInfoByUserPhone(userPhone);
    }

    public void registerClerk(Clerk clerk) {
        clerkMapper.insert(clerk);
    }

    public List<Long> getClerkId(){ return clerkMapper.getClerkId();}

    public boolean updateByPrimaryKeySelective(Clerk clerk) {
        return clerkMapper.updateByPrimaryKeySelective(clerk) > 0;
    }

    public List<Clerk> getAllClerkInfo() {
        return clerkMapper.getAllClerkInfo();
    }
}
