# 
# -d 后台启动运行
# –name 容器的名称
# –net=host docker使用host网络模式启动容器，默认是桥接模式
# agent -server 在服务器模式下运行Consul代理，consul支持以server或client的模式运行, server是服务发现模块的核心, client主要用于转发请求，此处不写-server默认是客户端模式启动
# -bind 该地址用来在集群内部的通讯，集群内的所有节点到地址都必须是可达的，默认是0.0.0.0
# -bootstrap-expect 指定consul将等待几个节点连通，成为一个完整的集群，1表示单节点
# -client 0.0.0.0 表示任何地址可以访问
# -ui 提供web管理功能：http://{ip}:8500/

# docker run -dti --name consul -e CONSUL_BIND_INTERFACE=eth0 consul

docker run -dti \
    --name consul \
    -p 8500:8500 -p 8300:8300 -p 8301:8301 -p 8302:8302 -p 8600:8600 \
    consul agent -server \
    -bootstrap-expect=1 \
    -client 0.0.0.0 \
    -bind=0.0.0.0 \
    -ui 