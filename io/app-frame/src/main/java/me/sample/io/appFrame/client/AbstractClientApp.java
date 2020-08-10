package me.sample.io.appFrame.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import static java.lang.System.exit;


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
public abstract class AbstractClientApp implements CommandLineRunner {

    @Autowired
    IClient client;

    @Override
    public void run(String... args) throws Exception {
        ClientMenu.show();
        char ch;
        while ((ch = (char) System.in.read()) != ClientMenu.Exit.getMenuKey()) {
            ClientMenu menu = ClientMenu.from(ch);
            if (menu != null) {
                switch (menu) {
                    case Start:
                        System.out.println("启动客户端:" + client.getName());
                        client.start();
                        break;
                    case Stop:
                        System.out.println("停止客户端:" + client.getName());
                        client.stop();
                        break;
                    case Send:
                        System.out.println("客户端发送测试指令...");
                        client.sendTestCmd();
                        break;
                    default:
                        break;
                }
                ClientMenu.show();
            }
        }

        client.stop();
        exit(0);
    }
}
