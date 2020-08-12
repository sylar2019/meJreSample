package me.sample.io.udp.peer;

import me.sample.io.appFrame.client.AbstractClientApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * File Name             :  TcpClientApp
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
public class UdpUnicastApp extends AbstractClientApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UdpUnicastApp.class);
        app.run(args);
    }
}
