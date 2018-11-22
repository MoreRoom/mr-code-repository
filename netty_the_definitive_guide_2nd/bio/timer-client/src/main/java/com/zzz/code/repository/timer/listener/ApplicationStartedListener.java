package com.zzz.code.repository.timer.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @auther: MoreRoom
 * @date: 2018/11/22 14:49
 */
@Component
public class ApplicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Spring application started success...");
        new Thread(new ServerThread()).start();
    }

    class ServerThread implements Runnable {
        @Override
        public void run() {
//            TimerServer.serverStart();
        }
    }

}
