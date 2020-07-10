package me.sample.monolith;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * File Name             :  MonolithApp
 *
 * @author :  sylar
 * Create                :  2020/5/15
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) allthings.vip  All Rights Reserved
 * *******************************************************************************************
 */
@SpringBootApplication
public class MonolithApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MonolithApp.class);
        app.setRegisterShutdownHook(true);
        ConfigurableApplicationContext ctx = app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
