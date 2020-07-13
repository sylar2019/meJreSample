package me.sample.io.tcp.client;

import io.netty.handler.logging.LogLevel;
import me.java.library.io.common.cmd.Cmd;
import me.java.library.io.common.cmd.Host;
import me.java.library.io.common.cmd.Terminal;
import me.java.library.io.common.pipe.Pipe;
import me.java.library.io.common.pipe.PipeWatcher;
import me.java.library.io.store.tcp.TcpClientBus;
import me.java.library.io.store.tcp.TcpClientPipe;
import me.java.library.io.store.tcp.TcpCodec;
import me.sample.io.codec.jsonLine.JsonFrameDecoder;
import me.sample.io.codec.jsonLine.JsonResolver;
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

    private TcpClientPipe pipe;

    public void start() {
        if (pipe == null) {
            TcpClientBus bus = getBus();
            TcpCodec codec = getCodec();
            pipe = new TcpClientPipe(bus, codec);
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

    private TcpClientBus getBus() {
        TcpClientBus bus = new TcpClientBus();
        bus.setHost("192.168.1.112");
        bus.setPort(10000);
        return bus;
    }

    private TcpCodec getCodec() {
        JsonResolver resolver = new JsonResolver();
        TcpCodec tcpCodec = new TcpCodec(resolver, JsonFrameDecoder.class);
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
            logger.info(String.format("### onReceived: \n%s", cmd));
        }

        @Override
        public void onException(Pipe pipe, Throwable t) {
            logger.error(String.format("### onException: %s", t));
        }
    };

}
