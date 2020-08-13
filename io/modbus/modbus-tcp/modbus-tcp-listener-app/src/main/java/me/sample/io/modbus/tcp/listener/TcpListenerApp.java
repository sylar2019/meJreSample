package me.sample.io.modbus.tcp.listener;

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
public class TcpListenerApp extends AbstractClientApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TcpListenerApp.class);
        app.run(args);
    }
}
