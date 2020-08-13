package me.sample.io.rxtx;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.cmd.Terminal;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.rxtx.RxtxExpress;
import me.java.library.io.store.rxtx.RxtxParams;
import me.sample.io.appFrame.client.AbstractClient;
import me.sample.io.codec.jsonLine.JsonCmd;
import me.sample.io.codec.jsonLine.JsonFrameDecoder;
import me.sample.io.codec.jsonLine.JsonResolver;
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
public class Client extends AbstractClient {


    @Override
    public String getName() {
        return "rxtx节点";
    }

    @Override
    protected Pipe buildPipe() {
        RxtxParams param = new RxtxParams("COM1", 9600);
        return RxtxExpress.create(
                param,
                new JsonFrameDecoder(),
                new JsonResolver());
    }

    @Override
    public void sendTestCmd() {
        JsonCmd cmd = new JsonCmd(Terminal.LOCAL, Terminal.REMOTE, "101");
        send(cmd);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        super.onReceivedCmd(pipe, cmd);
    }

}
