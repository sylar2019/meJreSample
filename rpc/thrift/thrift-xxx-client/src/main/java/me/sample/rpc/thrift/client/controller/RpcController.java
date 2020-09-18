package me.sample.rpc.thrift.client.controller;

import me.java.library.rpc.thrift.client.annotation.ThriftRefer;
import me.sample.rpc.thrift.client.rpc.HelloThriftClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpcController {

    @ThriftRefer
    private HelloThriftClient client;

    @GetMapping("/say")
    public String add(String word) throws Exception {
        return client.client().say(word);
    }

}
