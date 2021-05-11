package com.lj.gkzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lj.gkzy.dao")
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class GkzyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GkzyApplication.class, args);
    }

}
