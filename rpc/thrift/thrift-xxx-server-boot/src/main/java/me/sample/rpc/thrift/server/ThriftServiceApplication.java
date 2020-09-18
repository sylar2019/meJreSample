package me.sample.rpc.thrift.server;

import me.java.library.rpc.thrift.server.annotation.EnableThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableThriftServer
@SpringBootApplication
public class ThriftServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThriftServiceApplication.class, args);
    }

}
