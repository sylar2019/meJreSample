package me.sample.io.websocket.client;

import me.java.library.io.store.websocket.WebSocketCmd;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
@SpringBootApplication
public class WSClientApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WSClientApp.class);
        app.setRegisterShutdownHook(true);
        ConfigurableApplicationContext ctx = app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client();
        Menu.show();
        char ch;
        while ((ch = (char) System.in.read()) != Menu.Exit.getMenuKey()) {
            Menu menu = Menu.from(ch);
            if (menu != null) {
                switch (menu) {
                    case Start:
                        System.out.println("start...");
                        client.start();
                        break;
                    case Stop:
                        System.out.println("stop...");
                        client.stop();
                        break;
                    case Send:
                        System.out.println("send...");
                        WebSocketCmd cmd = WebSocketCmd.fromText("hello wsServer, I am client");
                        client.send(cmd);
                        break;
                    default:
                        break;
                }
                Menu.show();
            }
        }

        client.stop();
        exit(0);
    }
}
