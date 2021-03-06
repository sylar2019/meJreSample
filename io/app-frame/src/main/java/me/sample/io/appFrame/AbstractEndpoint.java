package me.sample.io.appFrame;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.cmd.Host;
import me.java.library.io.base.cmd.Terminal;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.base.pipe.PipeWatcher;
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
public abstract class AbstractEndpoint implements IEndpoint {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    final PipeWatcher watcher = new PipeWatcher() {
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
            onReceivedCmd(pipe, cmd);
        }

        @Override
        public void onException(Pipe pipe, Throwable t) {
            logger.error(String.format("### onException: %s", t));
        }
    };
    protected Pipe pipe;

    protected abstract Pipe buildPipe();

    @Override
    public void start() {
        if(pipe != null && pipe.isRunning()){
            System.out.println("### 重复启动，已经在运行");
            return;
        }
        if (pipe == null) {
            pipe = buildPipe();
            afterPipeBuilded(pipe);
        }
        pipe.start();
    }

    @Override
    public void stop() {
        if (pipe != null) {
            pipe.stop();
            pipe = null;
        }
        else{
            System.out.println("### 未启动，无需停止");
        }
    }

    protected void afterPipeBuilded(Pipe pipe) {
        pipe.setWatcher(watcher);
    }

    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        printReceivedCmd(cmd);
    }

    protected void printReceivedCmd(Cmd cmd) {
        logger.info(String.format("### onReceived: \n%s", cmd));
    }

}
