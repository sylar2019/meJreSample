package me.sample.io.udp.broadcast;

import me.java.library.io.Terminal;
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
public class UdpBroadcastApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UdpBroadcastApp.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Peer peer = new Peer(Peer.BROADCAST_PORT);
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

                        Terminal to = Terminal.REMOTE;
                        to.setInetSocketAddress(InetSocketAddress.createUnresolved(Peer.BROADCAST_HOST, Peer.BROADCAST_PORT));
                        JsonCmd cmd = new JsonCmd(Terminal.LOCAL, to, "222");
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
