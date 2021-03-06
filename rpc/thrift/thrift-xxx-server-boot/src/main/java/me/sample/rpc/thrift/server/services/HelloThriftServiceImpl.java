package me.sample.rpc.thrift.server.services;


import me.java.library.rpc.thrift.server.annotation.ThriftService;
import me.sample.rpc.thrift.api.HelloThriftService;
import org.apache.thrift.TException;

import java.util.Date;

@ThriftService(name = "helloThriftService")
public class HelloThriftServiceImpl implements HelloThriftService.Iface {

    @Override
    public String say(String word) throws TException {
        System.out.println("### servers: bootServer");
        return String.format("thrfitServer say: %s", word);
    }
}
