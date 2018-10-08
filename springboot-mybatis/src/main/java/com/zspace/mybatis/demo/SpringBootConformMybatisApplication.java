package com.zspace.mybatis.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
@MapperScan({
        "com.zspace.mybatis.demo.dao"
})
public class SpringBootConformMybatisApplication {

    // mybatis 自动生成代码
    // mybatis-generator:generate
    public static void main(String[] args) {
        SpringApplication.run(SpringBootConformMybatisApplication.class, args);
    }

}
