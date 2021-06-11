package com.mmg.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ThreadTiming {

    public ThreadTiming() {

        Thread 线程实现定时任务 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("线程实现定时任务");
                    Thread.sleep(1000 * 5);
                } catch (Exception e) {
                    log.error(e.toString());
                }
            }
        });
        Thread thread = new Thread(线程实现定时任务);
        thread.setDaemon(true);
        thread.start();
    }

}
