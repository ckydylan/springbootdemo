package com.cky.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dylan
 */
@SpringBootApplication
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
//        new SpringApplicationBuilder(SpringbootdemoApplication.class)
//                .bannerMode(Banner.Mode.OFF).run(args);
    }

}
