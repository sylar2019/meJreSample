package me.sample.io.coap.client;

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
public class CoapClientApp extends AbstractClientApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CoapClientApp.class);
        app.run(args);
    }
}
