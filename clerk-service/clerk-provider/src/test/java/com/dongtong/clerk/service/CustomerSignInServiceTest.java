package com.dongtong.clerk.service;

import com.dongtong.clerk.JunitBaseTest;
import com.dongtong.clerk.dto.CustomerSignInDTO;
import com.dongtong.clerk.query.CustomerSignInQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package com.dongtong.clerk.service.CustomerSignInServiceTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/12 10:28
 * version V1.0.0
 */
public class CustomerSignInServiceTest extends JunitBaseTest{
	@Autowired
	private CustomerSignInService customerSignInService;
	@Test
	public void testCustomerSignIn(){
		CustomerSignInDTO customerSignInDTO = new CustomerSignInDTO();
		customerSignInDTO.setCustomerId(1L);
		customerSignInDTO.setAddress("abc");
		customerSignInDTO.setLatitude("1");
		customerSignInDTO.setLongitude("2");
		ResultDO<Integer> result = customerSignInService.customerSignIn(customerSignInDTO);
	}

	@Test
	public void testQuerySignList(){
		CustomerSignInQuery query = new CustomerSignInQuery();
		query.setCustomerId(1L);
		ResultDO<Page<CustomerSignInDTO>> page = customerSignInService.querySignList(query);
	}
}
