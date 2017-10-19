package com.dongtong.basic.service;

import com.dongtong.basic.dto.AdvInfoDTO;
import com.dongtong.basic.query.AdvQuery;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 19:30
 **/
public interface AdvInfoService  {

    ResultDO<List<AdvInfoDTO>> advList(AdvQuery query);

    ResultDO<List<AdvInfoDTO>> adList(AdvQuery query);
}
