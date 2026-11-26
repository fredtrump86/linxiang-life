package com.linxiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.linxiang.mapper")
@SpringBootApplication
public class LinxiangLifeApplication {

  public static void main(String[] args) {
    SpringApplication.run(LinxiangLifeApplication.class, args);
  }
}
