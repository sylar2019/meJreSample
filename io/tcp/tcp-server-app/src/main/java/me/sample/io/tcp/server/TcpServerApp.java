package me.sample.io.tcp.server;

import me.sample.io.appFrame.server.AbstractServerApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * File Name             :  TcpServerApp
 *
 * @author :  sylar
 * Create                :  2019/12/9
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
public class TcpServerApp extends AbstractServerApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TcpServerApp.class);
        app.run(args);
    }
}
