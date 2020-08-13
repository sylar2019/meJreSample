package me.sample.io.udp.peer;


import com.google.common.base.Preconditions;
import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.cmd.Terminal;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.socket.SocketExpress;
import me.sample.io.appFrame.client.AbstractClient;
import me.sample.io.codec.jsonLine.JsonCmd;
import me.sample.io.codec.jsonLine.JsonResolver;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Objects;

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

    @Override
    public String getName() {
        return "Udp对等节点";
    }

    @Override
    protected Pipe buildPipe() {
        return SocketExpress.peer(10001, false, new JsonResolver());
    }

    @Override
    public void sendTestCmd() {
        InetSocketAddress toAddress = new InetSocketAddress("localhost", 10002);
        Preconditions.checkState(!toAddress.isUnresolved(), "单播目标地址错误");

        Terminal from = Terminals.Peer1;
        Terminal to = Terminals.Peer2;
        to.setInetSocketAddress(toAddress);
        JsonCmd cmd = new JsonCmd(from, to, "101");
        cmd.setAttr("这是一条udp消息，from peer1");
        send(cmd);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        logger.info(String.format("### onReceived: \n%s", cmd));

        JsonCmd res = new JsonCmd(cmd.getTo(), cmd.getFrom());
        if (Objects.equals(cmd.getCode(), "101")) {
            res.setCode("201");
            res.setAttr("答复101");
        } else {
            res.setCode("000");
            res.setAttr("答复");
        }
    }

}
