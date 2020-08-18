package me.sample.io.lwm2m.server;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.lwm2m.server.Lwm2mServerParams;
import me.java.library.io.store.lwm2m.server.Lwm2mServerPipe;
import me.sample.io.appFrame.server.AbstractServer;
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
public class Server extends AbstractServer {

    @Override
    public String getName() {
        return "LwM2M Server";
    }

    @Override
    protected Pipe buildPipe() {
        Lwm2mServerParams params = new Lwm2mServerParams();
        params.setWebPort(9999);
        params.setLocalPort(9988);
        return new Lwm2mServerPipe(params);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        super.onReceivedCmd(pipe, cmd);
        logger.info("to do something, eg: saveDB or sendMQ");
        //TODO do something ,eg: save to DB or send to MQ
    }
}
