package me.sample.io.coap.server;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.coap.CoapExpress;
import me.java.library.io.store.coap.CoapRequestCmd;
import me.java.library.io.store.coap.server.CoapServerPipe;
import me.sample.io.appFrame.server.AbstractServer;
import me.sample.io.coap.server.resource.World;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        return "CoAP服务端";
    }

    @Override
    protected Pipe buildPipe() {
        CoapServerPipe pipe = CoapExpress.server(9999);
        pipe.addResources(new World());
        return pipe;
    }

    @Override
    protected void printReceivedCmd(Cmd cmd) {
        Optional.of(cmd)
                .flatMap(v -> {
                    assert v instanceof CoapRequestCmd;
                    return Optional.of((CoapRequestCmd) v);
                })
                .ifPresent(v -> logger.info("### onReceived from client: \n" + v.getContentText()));
    }
}
