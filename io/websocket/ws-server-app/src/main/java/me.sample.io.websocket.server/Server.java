package me.sample.io.websocket.server;

import io.netty.buffer.ByteBufUtil;
import io.netty.handler.logging.LogLevel;
import me.java.library.io.Cmd;
import me.java.library.io.Host;
import me.java.library.io.Terminal;
import me.java.library.io.core.bus.list.WebSocketServerBus;
import me.java.library.io.core.codec.list.websocket.WebSocketCmdNode;
import me.java.library.io.core.codec.list.websocket.WebSocketCmdResolver;
import me.java.library.io.core.codec.list.websocket.WebSocketServerCodec;
import me.java.library.io.core.pipe.Pipe;
import me.java.library.io.core.pipe.PipeWatcher;
import me.java.library.io.core.pipe.list.WebSocketServerPipe;
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
            WebSocketServerBus bus = getBus();
            WebSocketServerCodec codec = getCodec();
            pipe = new WebSocketServerPipe(bus, codec);
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

    private WebSocketServerBus getBus() {
        WebSocketServerBus bus = new WebSocketServerBus();
        bus.setPort(10000);
        return bus;
    }

    private WebSocketServerCodec getCodec() {
        WebSocketServerCodec tcpCodec = new WebSocketServerCodec(WebSocketCmdResolver.DEFAULT);
        tcpCodec.setLogLevel(LogLevel.INFO);

        return tcpCodec;
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
                switch (wsCmd.getWebSocketFrameType()) {
                    case Binary:
                        logger.info(String.format("### onReceived Text: \n%s", wsCmd.getBinaryContent()));
                        break;
                    case Text:
                        logger.info("### onReceived Binary: \n");
                        ByteBufUtil.prettyHexDump(wsCmd.getBinaryContent());
                        break;
                    default:
                        break;
                }
            }


//            JsonCmd res = JsonCmdUtils.serverToClient("222");
//            pipe.send(res);
        }

        @Override
        public void onException(Pipe pipe, Throwable t) {
            logger.error(String.format("### onException: %s", t));
        }
    };

}
