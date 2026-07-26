package com.lakeatts.ftsprocurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = "com.lakeatts.ftsevents") 
public class FtsProcurementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtsProcurementApplication.class, args);
    }
}
