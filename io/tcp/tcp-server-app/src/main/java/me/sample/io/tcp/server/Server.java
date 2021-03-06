package me.sample.io.tcp.server;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.socket.SocketExpress;
import me.sample.io.appFrame.server.AbstractServer;
import me.sample.io.codec.jsonLine.JsonCmd;
import me.sample.io.codec.jsonLine.JsonCmdUtils;
import me.sample.io.codec.jsonLine.JsonFrameDecoder;
import me.sample.io.codec.jsonLine.JsonResolver;
import org.springframework.stereotype.Component;

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
public class Server extends AbstractServer {

    @Override
    public String getName() {
        return "Tcp Server";
    }

    @Override
    protected Pipe buildPipe() {
        return SocketExpress.tcpServer(
                9999,
                new JsonFrameDecoder(),
                new JsonResolver()
        );
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        super.onReceivedCmd(pipe, cmd);
        //TODO do something ,eg: save to DB or send to MQ

        //response
        JsonCmd res = JsonCmdUtils.serverToClient("201");
        pipe.send(res);
    }

}
