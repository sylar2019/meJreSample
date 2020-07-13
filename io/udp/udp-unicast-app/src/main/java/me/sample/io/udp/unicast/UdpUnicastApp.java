package me.sample.io.udp.unicast;

import me.java.library.io.common.cmd.Terminal;
import me.sample.io.codec.jsonLine.JsonCmd;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

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
public class UdpUnicastApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UdpUnicastApp.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Peer peer = new Peer(10001);
        Menu.show();
        char ch;
        while ((ch = (char) System.in.read()) != Menu.Exit.getMenuKey()) {
            Menu menu = Menu.from(ch);
            if (menu != null) {
                switch (menu) {
                    case Start:
                        System.out.println("start...");
                        peer.start();
                        break;
                    case Stop:
                        System.out.println("stop...");
                        peer.stop();
                        break;
                    case Send:
                        System.out.println("send...");

                        Terminal to = Terminals.Peer2;
                        to.setInetSocketAddress(InetSocketAddress.createUnresolved("192.168.1.112", 10001));
                        JsonCmd cmd = new JsonCmd(Terminals.Peer1, Terminals.Peer2, "222");
                        peer.send(cmd);
                        break;
                    default:
                        break;
                }
                Menu.show();
            }
        }

        peer.stop();
        exit(0);
    }
}
