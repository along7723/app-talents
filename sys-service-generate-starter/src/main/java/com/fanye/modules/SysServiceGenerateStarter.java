package com.fanye.modules;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.fanye.modules")
@EnableWebMvc
@ServletComponentScan
public class SysServiceGenerateStarter {

    public static void main(String[] args) {
        SpringApplication.run(SysServiceGenerateStarter.class, args);
    }

}
