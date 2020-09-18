package me.sample.rpc.thrift.server.controller;

import me.sample.rpc.thrift.api.HelloThriftService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloThriftController implements HelloThriftService.Iface {

    @Autowired
    HelloThriftService.Iface service;

    @Override
    @GetMapping("/say")
    public String say(String word) throws TException {
        return service.say(word);
    }
}
