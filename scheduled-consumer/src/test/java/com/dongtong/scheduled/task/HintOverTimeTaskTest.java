package com.dongtong.scheduled.task;

import com.dongtong.scheduled.JunitBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:zhoumin
 * @Description:
 * @Date:Created in 15:34 2017/8/14.
 */
public class HintOverTimeTaskTest extends JunitBaseTest {

    @Autowired(required = false)
    public HintOverTimeTask hintOverTimeTask;

    @Test
    public void testOverTimeUpdate(){
        hintOverTimeTask.overTimeUpdate();
    }
}
