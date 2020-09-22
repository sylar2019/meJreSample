package me.sample.rpc.grpc.client;


import me.java.library.rpc.grpc.client.GrpcClient;
import me.sample.rpc.grpc.api.*;
import org.springframework.stereotype.Service;

import io.grpc.Channel;

@Service
public class GrpcClientService {

    @GrpcClient("grpc-xxx-server")
    private Channel serverChannel;

    public String sendMessage(String name) {
        SimpleGrpc.SimpleBlockingStub stub = SimpleGrpc.newBlockingStub(serverChannel);
        HelloReply response = stub.sayHello(HelloRequest.newBuilder().setName(name).build());
        return response.getMessage();
    }
}
