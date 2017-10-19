package com.dongtong.clerk.manager;

import com.dongtong.clerk.dao.ClerkRemarkMapper;
import com.dongtong.clerk.domain.ClerkRemark;
import com.shfc.common.base.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.clerk.manager.ClerkRemarkManager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/8/8 9:54
 * version V1.0.0
 */
@Service
public class ClerkRemarkManager {

    @Autowired
    private ClerkRemarkMapper clerkRemarkMapper;

    public Boolean addRemark(ClerkRemark clerkRemark){
        try {
            clerkRemarkMapper.insert(clerkRemark);
        }catch (Exception e){
            Logger.error(e, "插入月度历史排名错误!:"+e.getMessage());
            return false;
        }
        return true;
    }

}
