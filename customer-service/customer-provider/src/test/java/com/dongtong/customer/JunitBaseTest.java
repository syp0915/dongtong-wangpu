package com.dongtong.customer;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(
        locations = {"classpath:config/*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class JunitBaseTest {


}
