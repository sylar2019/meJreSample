package me.sample.io.udp.broadcast;

import me.java.library.io.common.bus.AbstractSocketBus;
import me.java.library.io.common.cmd.Cmd;
import me.java.library.io.common.cmd.Host;
import me.java.library.io.common.cmd.Terminal;
import me.java.library.io.common.pipe.Pipe;
import me.java.library.io.common.pipe.PipeWatcher;
import me.java.library.io.store.udp.UdpBroadcastBus;
import me.java.library.io.store.udp.UdpBroadcastPipe;
import me.java.library.io.store.udp.UdpCodec;
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

    public final static String BROADCAST_HOST = AbstractSocketBus.defaultBroadcastHost;
    public final static int BROADCAST_PORT = 11000;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private int port;
    private UdpBroadcastPipe pipe;

    public Peer(int port) {
        this.port = port;
    }

    public void start() {
        if (pipe == null) {
            UdpBroadcastBus bus = getBus();
            UdpCodec codec = getCodec();
            pipe = new UdpBroadcastPipe(bus, codec);
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

    private UdpBroadcastBus getBus() {
        UdpBroadcastBus bus = new UdpBroadcastBus();
        bus.setPort(port);
        return bus;
    }

    private UdpCodec getCodec() {
        JsonResolver resolver = new JsonResolver();
        return new UdpCodec(resolver);
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
