package me.sample.io.opc.client;

import me.sample.io.appFrame.client.AbstractClientApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : sylar
 * @fullName : me.sample.io.opc.client.OpcClientApp
 * @createDate : 2020/8/13
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@SpringBootApplication
public class OpcClientApp extends AbstractClientApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpcClientApp.class);
        app.run(args);
    }
}
