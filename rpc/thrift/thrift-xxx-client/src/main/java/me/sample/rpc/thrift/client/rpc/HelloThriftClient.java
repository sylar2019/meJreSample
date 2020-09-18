package me.sample.rpc.thrift.client.rpc;


import me.java.library.rpc.thrift.client.annotation.ThriftClient;
import me.java.library.rpc.thrift.client.common.ThriftClientAware;
import me.sample.rpc.thrift.api.HelloThriftService;

@ThriftClient(serviceId = "thrift-xxx-server", refer = HelloThriftService.class)
public interface HelloThriftClient extends ThriftClientAware<HelloThriftService.Client> {
}
