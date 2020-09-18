package me.sample.rpc.thrift.client;

import me.java.library.rpc.thrift.client.annotation.EnableThriftClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EnableThriftClient
@SpringBootApplication
public class ThriftClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThriftClientApplication.class, args);
    }

}
