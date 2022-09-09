package com.umarbariev.projects.cb_demo_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CbDemoTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbDemoTaskApplication.class, args);
    }

}
