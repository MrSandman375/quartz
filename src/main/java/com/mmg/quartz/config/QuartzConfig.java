package com.mmg.quartz.config;

import com.mmg.quartz.job.SyncUserJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class QuartzConfig {

    private static final String JOB_GROUP_NAME = "JOB_GROUP_NAME";
    private static final String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";

    /**
     * 定时任务
     */
    @Bean
    public JobDetail syncUserJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        Map<String,String> map = new HashMap<>();
        map.put("userName", "pan_junbiao的博客");
        map.put("blogUrl","https://blog.csdn.net/pan_junbiao");
        map.put("blogRemark","您好，欢迎访问 pan_junbiao的博客");
        jobDataMap.putAll(map);
        return JobBuilder.newJob(SyncUserJob.class)
                .withIdentity("syncUserJobDetail",JOB_GROUP_NAME)
                .usingJobData(jobDataMap)
                .storeDurably() //即使没有Trigger关联时，也不需要删除该JobDetail

                .build();
    }

    /**
     * 触发器
     */
    @Bean
    public Trigger syncUserJobTrigger(){
        //每五秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        //创建触发器
        return TriggerBuilder.newTrigger()
                .forJob(syncUserJobDetail()) // 关联需要执行的任务
                .withIdentity("syncUserJobTrigger",TRIGGER_GROUP_NAME)
                .withSchedule(cronScheduleBuilder) //触发条件
                .build();
    }

}
