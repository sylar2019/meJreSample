package me.sample.io.opc.client;

import com.google.common.collect.Sets;
import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.opc.OpcExpress;
import me.java.library.io.store.opc.client.OpcClientParams;
import me.java.library.io.store.opc.cmd.OpcReadRequestCmd;
import me.java.library.io.store.opc.cmd.OpcReadResponseCmd;
import me.sample.io.appFrame.client.AbstractClient;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : sylar
 * @fullName : me.sample.io.opc.client.Client
 * @createDate : 2020/8/13
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class Client extends AbstractClient {


    @Override
    public String getName() {
        return "OPC Client";
    }

    @Override
    protected Pipe buildPipe() {
        OpcClientParams params = new OpcClientParams();
        params.setServer("127.0.0.1");
        params.setUser("opc");
        params.setPassword("opc");
        params.setProgId("...");
        params.setClsId("...");
        return OpcExpress.client(params);
    }

    @Override
    public void sendTestCmd() {
        OpcReadRequestCmd req = new OpcReadRequestCmd();
        Set<String> items = Sets.newHashSet();
        items.add("tag1");
        items.add("tag2");
        items.add("tag3");
        items.add("tag4");
        req.setItems(items);
        send(req);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        super.onReceivedCmd(pipe, cmd);
        if (cmd instanceof OpcReadResponseCmd) {
            logger.info("### onReceived: OpcReadResponseCmd \n");
            OpcReadResponseCmd res = (OpcReadResponseCmd) cmd;
            res.getResult().forEach((tag, var) -> {
                logger.info(String.format("tagId: %s  tagValue: %s", tag, var));
            });
        }
    }
}
