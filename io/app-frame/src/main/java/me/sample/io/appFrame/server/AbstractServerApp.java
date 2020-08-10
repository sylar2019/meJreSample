package me.sample.io.appFrame.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import static java.lang.System.exit;


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
public abstract class AbstractServerApp implements CommandLineRunner {

    @Autowired
    IServer server;

    @Override
    public void run(String... args) throws Exception {
        ServerMenu.show();
        char ch;
        while ((ch = (char) System.in.read()) != ServerMenu.Exit.getMenuKey()) {
            ServerMenu menu = ServerMenu.from(ch);
            if (menu != null) {
                switch (menu) {
                    case Start:
                        System.out.println("启动:" + server.getName());
                        server.start();
                        break;
                    case Stop:
                        System.out.println("停止" + server.getName());
                        server.stop();
                        break;
                    default:
                        break;
                }
                ServerMenu.show();
            }
        }

        server.stop();
        exit(0);
    }
}
