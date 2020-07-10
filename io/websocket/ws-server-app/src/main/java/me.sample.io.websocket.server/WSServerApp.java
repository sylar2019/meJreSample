package me.sample.io.websocket.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@SpringBootApplication
public class WSServerApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WSServerApp.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Server server = new Server();
        Menu.show();
        char ch;
        while ((ch = (char) System.in.read()) != Menu.Exit.getMenuKey()) {
            Menu menu = Menu.from(ch);
            if (menu != null) {
                switch (menu) {
                    case Start:
                        System.out.println("start...");
                        server.start();
                        break;
                    case Stop:
                        System.out.println("stop...");
                        server.stop();
                        break;
                    case Status:
                        System.out.println("server status: " + server.isRunning());
                        break;
                    default:
                        break;
                }
                Menu.show();
            }
        }

        server.stop();
        exit(0);
    }
}
