package com.dongtong.scheduled.task;

import com.dongtong.customer.service.ScheduleService;
import com.dongtong.scheduled.JunitBaseTest;
import com.dongtong.scheduled.notice.ArrivalTime;
import com.dongtong.scheduled.notice.MonthRanking;
import com.dongtong.scheduled.notice.PushSchedule;
import com.dongtong.scheduled.notice.WeekRanking;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sunyaping
 * @Package com.dongtong.scheduled.task
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-06-12 11:29
 * version V1.0.0
 **/
public class NoticeTaskTest extends JunitBaseTest {

    @Autowired(required = false)
    public WeekRanking weekRanking;

    @Autowired(required = false)
    public MonthRanking monthRanking;

    @Autowired(required = false)
    public PushSchedule pushSchedule;

    @Autowired(required = false)
    public ArrivalTime arrivalTime;

    @Test
    public void testWeekRanking(){
        weekRanking.weekRanking();
    }

    @Test
    public void testMonthRanking(){
        monthRanking.monthRanking();
    }

    @Test
    public void testPushSchedule(){
        pushSchedule.pushSchedule();
    }


    @Test
    public void testScheduleTime(){
        arrivalTime.scheduleTime();
    }
}
