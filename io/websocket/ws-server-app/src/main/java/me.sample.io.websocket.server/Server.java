package me.sample.io.websocket.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.websocket.WebSocketCmd;
import me.java.library.io.store.websocket.WebSocketExpress;
import me.sample.io.appFrame.server.AbstractServer;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        return "Websocket Server";
    }

    @Override
    protected Pipe buildPipe() {
        return WebSocketExpress.server(9999, "ws", false);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        Optional.ofNullable(cmd).flatMap(v -> Optional.of((WebSocketCmd) cmd)).ifPresent(wsCmd -> {

            //TODO do something ,eg: save to DB or send to MQ

            WebSocketCmd res = null;
            String resText = "I am websocket-server";
            switch (wsCmd.getWebSocketFrameType()) {
                case Text:
                    logger.info(String.format("### onReceived Text from client:\n %s", wsCmd.getTextContent()));
                    //回应
                    res = WebSocketCmd.fromText(resText);
                    break;
                case Binary:
                    logger.info(String.format("### onReceived Binary from client:\n %s", ByteBufUtil.prettyHexDump(wsCmd.getBinaryContent())));
                    //回应
                    ByteBuf buf = Unpooled.buffer();
                    buf.writeBytes(resText.getBytes(CharsetUtil.UTF_8));
                    res = WebSocketCmd.fromBinary(buf);
                    break;
                default:
                    break;
            }
            pipe.send(res);
        });
    }
}
