package me.sample.io.udp.multicast;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.cmd.Host;
import me.java.library.io.base.cmd.Terminal;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.base.pipe.PipeWatcher;
import me.java.library.io.core.bus.AbstractSocketBus;
import me.java.library.io.store.socket.SocketExpress;
import me.java.library.io.store.socket.udp.UdpMulticastPipe;
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
public class Peer {

    public final static String MULTICAST_HOST = AbstractSocketBus.DEFAULT_MULTICAST_HOST;
    public final static int MULTICAST_PORT = 12000;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private int port;
    private UdpMulticastPipe pipe;

    public Peer(int port) {
        this.port = port;
    }

    public void start() {
        if (pipe == null) {
            pipe = SocketExpress.udpMulticast(
                    "en0",
                    port,
                    new JsonResolver());
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
//            JsonCmd res = new JsonCmd(cmd.getTo(), cmd.getFrom(), "111");
//            pipe.send(res);
        }

        @Override
        public void onException(Pipe pipe, Throwable t) {
            logger.error(String.format("### onException: %s", t));
        }
    };

}
