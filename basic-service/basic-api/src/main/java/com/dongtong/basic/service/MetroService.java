package com.dongtong.basic.service;

import com.dongtong.basic.dto.MetroLineInfoDTO;
import com.dongtong.basic.query.MetroQuery;
import com.shfc.common.result.ResultDO;

import java.util.List;


/**
 * @File com.fc.plot.service.BuildingExtService.java
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:上海房产
 * 
 * @author lvbin
 * @date 2016年9月5日 下午6:39:37
 * @version V1.0
 */
public interface MetroService {

	/**
	 * Copyright: Copyright (c) 2016 
	 *
	 * 
	 * @author wky
	 * @date 2017年5月5日 下午1:37:46
	 * @version V1.0
	 * @param query
	 * @return
	 */
	ResultDO<List<MetroLineInfoDTO>> queryMetro(MetroQuery query);



}
