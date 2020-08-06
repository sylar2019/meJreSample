package me.sample.io.websocket.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import me.java.library.io.common.cmd.Cmd;
import me.java.library.io.common.cmd.Host;
import me.java.library.io.common.cmd.Terminal;
import me.java.library.io.common.pipe.Pipe;
import me.java.library.io.common.pipe.PipeWatcher;
import me.java.library.io.store.websocket.WebSocketCmdNode;
import me.java.library.io.store.websocket.WebSocketCmdResolver;
import me.java.library.io.store.websocket.server.WebSocketServerPipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class Server {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private WebSocketServerPipe pipe;

    public void start() {
        if (pipe == null) {
            pipe = WebSocketServerPipe.express(
                    "ws://127.0.0.1:8800/ws",
                    WebSocketCmdResolver.DEFAULT
            );
            pipe.setWatcher(watcher);
        }
        pipe.start();
    }

    public void stop() {
        if (pipe != null) {
            pipe.stop();
        }
    }

    public boolean isRunning() {
        if (pipe != null) {
            return pipe.isRunning();
        }
        return false;
    }

    private PipeWatcher watcher = new PipeWatcher() {
        @Override
        public void onHostStateChanged(Host host, boolean isRunning) {
            logger.info(String.format("### onHostStateChanged: %s", isRunning));
        }

        @Override
        public void onPipeRunningChanged(Pipe pipe, boolean isRunning) {
            logger.info(String.format("### onPipeRunningChanged: %s", isRunning));
        }

        @Override
        public void onConnectionChanged(Pipe pipe, Terminal terminal, boolean isConnected) {
            logger.info(String.format("### onConnectionChanged: [%s] %s", terminal, isConnected));
        }

        @Override
        public void onReceived(Pipe pipe, Cmd cmd) {
            if (cmd instanceof WebSocketCmdNode) {
                WebSocketCmdNode wsCmd = (WebSocketCmdNode) cmd;
                WebSocketCmdNode res = null;
                switch (wsCmd.getWebSocketFrameType()) {
                    case Text:
                        logger.info(String.format("### onReceived Text: %s", wsCmd.getTextContent()));

                        //回应
                        res = WebSocketCmdNode.fromText("Received text: " + wsCmd.getTextContent());
                        break;
                    case Binary:
                        logger.info("### onReceived Binary: \n");
                        ByteBufUtil.prettyHexDump(wsCmd.getBinaryContent());

                        //回应
                        ByteBuf buf = Unpooled.buffer();
                        buf.writeBytes("Received binary".getBytes(CharsetUtil.UTF_8));
                        res = WebSocketCmdNode.fromBinary(buf);
                        break;
                    default:
                        break;
                }

                if (res != null) {
                    pipe.send(res);
                }
            }
        }

        @Override
        public void onException(Pipe pipe, Throwable t) {
            logger.error(String.format("### onException: %s", t));
        }
    };

}
