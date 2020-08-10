package me.sample.io.coap.server.resource;

import me.java.library.io.store.coap.server.ServerResource;
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
        exchange.respond("hello, I am server");
    }
}
