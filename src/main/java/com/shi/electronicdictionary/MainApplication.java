package com.shi.electronicdictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.LocalTime;

@EnableScheduling //开启基于注解的定时任务
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withSecond(0).withNano(0);
        System.out.println(time);
        System.out.println(date);

    }
}
