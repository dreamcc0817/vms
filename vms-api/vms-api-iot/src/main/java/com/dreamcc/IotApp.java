package com.dreamcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author cloud-cc
 * @ClassName IotApp
 * @Description Iot启动类
 * @date 2021/11/27 20:48
 * @Version 1.0
 */
@EnableCaching
@SpringBootApplication
public class IotApp {
    public static void main(String[] args) {
        SpringApplication.run(IotApp.class, args);
    }
}
