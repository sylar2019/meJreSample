package me.sample.io.websocket.client;

import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.websocket.WebSocketCmd;
import me.java.library.io.store.websocket.WebSocketCmdResolver;
import me.java.library.io.store.websocket.WebSocketExpress;
import me.sample.io.appFrame.client.AbstractClient;
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
        return "Websocket客户端";
    }

    @Override
    protected Pipe buildPipe() {
        return WebSocketExpress.client(
                "ws://127.0.0.1:9999/ws",
                WebSocketCmdResolver.DEFAULT);
    }

    @Override
    public void sendTestCmd() {
        WebSocketCmd cmd = WebSocketCmd.fromText("I am websocket-client");
        send(cmd);
    }

}
