package me.sample.io.websocket.client;

import io.netty.handler.logging.LogLevel;
import me.java.library.io.common.cmd.Cmd;
import me.java.library.io.common.cmd.Host;
import me.java.library.io.common.cmd.Terminal;
import me.java.library.io.common.pipe.Pipe;
import me.java.library.io.common.pipe.PipeWatcher;
import me.java.library.io.store.ws.WebSocketClientBus;
import me.java.library.io.store.ws.WebSocketClientCodec;
import me.java.library.io.store.ws.WebSocketClientPipe;
import me.java.library.io.store.ws.WebSocketCmdResolver;
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
public class Client {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private WebSocketClientPipe pipe;

    public void start() {
        if (pipe == null) {
            WebSocketClientBus bus = getBus();
            WebSocketClientCodec codec = getCodec();
            pipe = new WebSocketClientPipe(bus, codec);
            pipe.setWatcher(watcher);
        }
        pipe.start();
    }

    public void stop() {
        if (pipe != null) {
            pipe.stop();
        }
    }

    public void send(Cmd cmd) {
        if (pipe != null) {
            pipe.send(cmd);
        }
    }

    private WebSocketClientBus getBus() {
        WebSocketClientBus bus = new WebSocketClientBus();
//        bus.setHost("121.40.165.18");
        bus.setHost("127.0.0.1");
        bus.setPort(8800);
        return bus;
    }

    private WebSocketClientCodec getCodec() {
        WebSocketClientCodec codec = new WebSocketClientCodec(WebSocketCmdResolver.DEFAULT);
        codec.setLogLevel(LogLevel.INFO);
        return codec;
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
            logger.info(String.format("### onReceived: \n%s", cmd));
        }

        @Override
        public void onException(Pipe pipe, Throwable t) {
            logger.error(String.format("### onException: %s", t));
        }
    };

}
