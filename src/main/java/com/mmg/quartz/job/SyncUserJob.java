package com.mmg.quartz.job;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 用户任务
 */
public class SyncUserJob extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        //获取JobDetail中传递的参数
        String username = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("userName");
        String blogUrl = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("blogUrl");
        String blogRemark  = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("blogRemark");

        //获取当前时间
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        //打印信息
        System.out.println("用户：" + username);
        System.out.println("博客地址：" + blogUrl);
        System.out.println("博客信息：" + blogRemark);
        System.out.println("当前时间：" + format);


    }
}
