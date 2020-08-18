package me.sample.io.tcp.client;


import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.socket.SocketExpress;
import me.sample.io.appFrame.client.AbstractClient;
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
public class Client extends AbstractClient {

    @Override
    public String getName() {
        return "Tcp Client";
    }

    @Override
    protected Pipe buildPipe() {
        return SocketExpress.tcpClient(
                "localhost",
                9999,
                new JsonFrameDecoder(),
                new JsonResolver()
        );
    }

    @Override
    public void sendTestCmd() {
        JsonCmd cmd = JsonCmdUtils.clientToServer("101");
        send(cmd);
    }

}
