package me.sample.io.udp.multicast;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.cmd.Terminal;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.socket.SocketExpress;
import me.sample.io.appFrame.client.AbstractClient;
import me.sample.io.codec.jsonLine.JsonCmd;
import me.sample.io.codec.jsonLine.JsonResolver;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * File Name             :  TcpServerPipe
 *
 * @author :  sylar
 * Create                :  2019/12/18
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
@Component
public class Peer extends AbstractClient {
    public final static String MULTICAST_HOST = "224.0.2.10";
    public final static int MULTICAST_PORT = 9999;

    @Override
    public String getName() {
        return "Udp Multlcast Node";
    }

    @Override
    protected Pipe buildPipe() {
        return SocketExpress.multicast(
                "en0",
                MULTICAST_PORT,
                MULTICAST_HOST,
                new JsonResolver());
    }

    @Override
    public void sendTestCmd() {
        Terminal from = Terminal.LOCAL;
        Terminal to = Terminal.REMOTE;
        InetSocketAddress toAddress = new InetSocketAddress(MULTICAST_HOST, MULTICAST_PORT);
        to.setInetSocketAddress(toAddress);
        JsonCmd cmd = new JsonCmd(from, to, "101");
        cmd.setAttr("这是一条组播消息");
        send(cmd);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        super.onReceivedCmd(pipe, cmd);
        //TODO do something ,eg: save to DB or send to MQ
    }

}
