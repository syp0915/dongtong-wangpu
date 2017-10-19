package com.dongtong.scheduled.task;

import com.dongtong.scheduled.JunitBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/31 上午10:54.
 */
public class CustomerScheduleTaskTest extends JunitBaseTest {

    @Autowired(required = false)
    public CustomerScheduleTask customerScheduleTask;

    @Test
    public void testExpireCustomerSchedule(){
        customerScheduleTask.expireCustomerSchedule();
    }
}
