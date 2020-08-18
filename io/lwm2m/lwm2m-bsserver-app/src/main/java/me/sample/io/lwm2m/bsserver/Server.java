package me.sample.io.lwm2m.bsserver;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.lwm2m.bsserver.Lwm2mBSServerParams;
import me.java.library.io.store.lwm2m.bsserver.Lwm2mBSServerPipe;
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
        return "LwM2M BootServer";
    }

    @Override
    protected Pipe buildPipe() {
        Lwm2mBSServerParams params = new Lwm2mBSServerParams();
        return new Lwm2mBSServerPipe(params);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        super.onReceivedCmd(pipe, cmd);
    }

}
