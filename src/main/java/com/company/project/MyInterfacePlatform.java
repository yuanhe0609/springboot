package com.company.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.company.project.mapper")
public class MyInterfacePlatform {

    public static void main(String[] args) {
        SpringApplication.run(MyInterfacePlatform.class, args);
    }

}
