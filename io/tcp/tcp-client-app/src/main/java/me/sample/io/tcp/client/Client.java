package me.sample.io.tcp.client;


import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.cmd.Host;
import me.java.library.io.base.cmd.Terminal;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.base.pipe.PipeWatcher;
import me.java.library.io.store.socket.SocketExpress;
import me.java.library.io.store.socket.tcp.TcpClientPipe;
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
            pipe = SocketExpress.tcpClient(
                    "localhost",
                    10000,
                    new JsonFrameDecoder(),
                    new JsonResolver()
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

    public void send(Cmd cmd) {
        if (pipe != null) {
            pipe.send(cmd);
        }
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
