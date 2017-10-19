package com.dongtong.app;

import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.UUIDUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration("classpath*:config/*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Ignore
public class JunitBaseMockMvcTest {

    public MockMvc mockMvc;

    public String tokenKey = "";
    public String DEFAULT_TOKEN_NAME = "X-token";
    public String VERSION = "v1.0.0";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        tokenKey = UUIDUtils.getUUID();

        Long currentUserId = 2L;

        RedisUtil.set("CUSTOMER_" + tokenKey, currentUserId, 1800L);
    }
}
