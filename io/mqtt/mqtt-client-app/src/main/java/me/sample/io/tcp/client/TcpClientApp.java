//package me.sample.io.tcp.client;
//
//import me.sample.io.codec.jsonLine.JsonCmd;
//import me.sample.io.codec.jsonLine.JsonCmdUtils;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import static java.lang.System.exit;
//
//
///**
// * File Name             :  TcpClientApp
// *
// * @author :  sylar
// * Create                :  2019/12/9
// * Description           :
// * Reviewed By           :
// * Reviewed On           :
// * Version History       :
// * Modified By           :
// * Modified Date         :
// * Comments              :
// * CopyRight             : COPYRIGHT(c) allthings.vip  All Rights Reserved
// * *******************************************************************************************
// */
//@SpringBootApplication
//public class TcpClientApp implements CommandLineRunner {
//    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(TcpClientApp.class);
//        app.setRegisterShutdownHook(true);
//        ConfigurableApplicationContext ctx = app.run(args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Client client = new Client();
//        Menu.show();
//        char ch;
//        while ((ch = (char) System.in.read()) != Menu.Exit.getMenuKey()) {
//            Menu menu = Menu.from(ch);
//            if (menu != null) {
//                switch (menu) {
//                    case Start:
//                        System.out.println("start...");
//                        client.start();
//                        break;
//                    case Stop:
//                        System.out.println("stop...");
//                        client.stop();
//                        break;
//                    case Send:
//                        System.out.println("send...");
//                        JsonCmd cmd = JsonCmdUtils.clientToServer("111");
//                        client.send(cmd);
//                        break;
//                    default:
//                        break;
//                }
//                Menu.show();
//            }
//        }
//
//        client.stop();
//        exit(0);
//    }
//}
