package com.louay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.louay.model", "com.louay.controller"})
public class ReactjsRestapiCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactjsRestapiCrudApplication.class, args);
    }
}
