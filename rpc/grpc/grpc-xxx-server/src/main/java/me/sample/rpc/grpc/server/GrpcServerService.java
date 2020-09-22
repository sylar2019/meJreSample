package me.sample.rpc.grpc.server;


import io.grpc.stub.StreamObserver;
import me.java.library.rpc.grpc.server.GrpcService;
import me.sample.rpc.grpc.api.*;

@GrpcService(SimpleGrpc.class)
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello =============> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
