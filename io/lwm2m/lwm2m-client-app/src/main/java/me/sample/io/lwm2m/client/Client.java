package me.sample.io.lwm2m.client;


import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.lwm2m.client.Lwm2mClientParams;
import me.java.library.io.store.lwm2m.client.Lwm2mClientPipe;
import me.sample.io.appFrame.client.AbstractClient;
import me.sample.io.lwm2m.client.instance.LocationSensor;
import me.sample.io.lwm2m.client.instance.Pm25Sensor;
import org.eclipse.leshan.core.LwM2mId;
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
        return "LwM2M Client";
    }

    @Override
    protected Pipe buildPipe() {
        Lwm2mClientParams params = new Lwm2mClientParams();
        params.setServerUri("coap://localhost:9999");
        params.setRemotePort(9988);
        return new Lwm2mClientPipe(params);
    }

    @Override
    protected void afterPipeBuilded(Pipe pipe) {
        super.afterPipeBuilded(pipe);
        Lwm2mClientPipe p = (Lwm2mClientPipe) pipe;
        p.initInstances(LwM2mId.DEVICE, new Pm25Sensor());
    }

    @Override
    public void sendTestCmd() {
    }

}
