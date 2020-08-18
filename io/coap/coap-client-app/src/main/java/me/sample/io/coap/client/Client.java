package me.sample.io.coap.client;


import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.coap.*;
import me.java.library.utils.base.JsonUtils;
import me.sample.io.appFrame.client.AbstractClient;
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
public class Client extends AbstractClient {

    @Override
    public String getName() {
        return "CoAP Client";
    }

    @Override
    protected Pipe buildPipe() {
        return CoapExpress.client();
    }

    @Override
    public void sendTestCmd() {
        String uri = "coap://127.0.0.1/world";
        CoapRequestCmd cmd = new CoapRequestCmd(uri,
                CoapMethod.POST,
                CoapFormat.json);

        Foo foo = new Foo("I am coap-client");
        cmd.setContent(JsonUtils.toJSONString(foo));

        send(cmd);
    }

    @Override
    protected void printReceivedCmd(Cmd cmd) {
        Optional.of(cmd)
                .flatMap(v -> {
                    assert v instanceof CoapResponseCmd;
                    return Optional.of((CoapResponseCmd) v);
                })
                .map(CoapResponseCmd::getResponse)
                .ifPresent(v -> logger.info("### onReceived from server: \n" + v.getResponseText()));

    }
}
