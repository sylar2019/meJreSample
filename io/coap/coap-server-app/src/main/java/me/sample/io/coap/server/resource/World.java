package me.sample.io.coap.server.resource;

import me.java.library.io.store.coap.CoapFormat;
import me.java.library.io.store.coap.CoapRequestCmd;
import me.java.library.io.store.coap.server.ServerResource;
import me.java.library.utils.base.JsonUtils;
import me.sample.io.coap.server.Foo;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * @author : sylar
 * @fullName : me.sample.io.coap.server.resource.World
 * @createDate : 2020/8/10
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class World extends ServerResource {
    public World() {
        super("world");
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        CoapRequestCmd cmd = fromExchange(exchange);
        System.out.println("### handlePOST:" + cmd);
        //TODO do something ,eg: save to DB or send to MQ

        //response
        Foo foo = new Foo("I am coap-server");
        exchange.respond(CoAP.ResponseCode.CONTENT,
                JsonUtils.toJSONString(foo),
                CoapFormat.json.getValue());
    }
}
