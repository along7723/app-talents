package com.fanye.modules;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@MapperScan("com.fanye.modules")
@EnableWebMvc
@ServletComponentScan
public class SysServiceManageStarter {

    public static void main(String[] args) {
        SpringApplication.run(SysServiceManageStarter.class, args);
    }

}
