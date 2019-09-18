package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * 基础微服务的启动类
 */
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }

    //将idworker放入spring容器中，@autowire prvaite Idwork idwork；
    @Bean
    public IdWorker idWorker(){

        return new IdWorker(1,1);
    }


}
