package me.sample.io.tcp.client;

import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.mqtt.client.MqttClientParams;
import me.java.library.io.store.mqtt.client.MqttClientPipe;
import me.java.library.io.store.mqtt.common.MqttCmd;
import me.sample.io.appFrame.client.AbstractClient;
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
        return "mqtt客户端";
    }

    @Override
    protected Pipe buildPipe() {
        MqttClientParams param = new MqttClientParams("tcp://127.0.0.1:1883");
        return new MqttClientPipe(param);
    }

    @Override
    public void sendTestCmd() {
        MqttCmd cmd = new MqttCmd("topic1/aaa", "I am mqtt client");
        send(cmd);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        super.onReceivedCmd(pipe, cmd);
    }


}
