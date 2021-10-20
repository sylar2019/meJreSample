## 通讯开发快速示例

**基于 meJreLibsVersion 1.x 版本组件**

### 项目库结构

使用tree命令显示项目结构

```
tree -d -L 4
```

```
├── io
│   ├── app-frame
│   ├── coap
│   │   ├── coap-client-app
│   │   └── coap-server-app
│   ├── codec
│   │   └── codec-jsonLine
│   ├── lwm2m
│   │   ├── lwm2m-bsserver-app
│   │   ├── lwm2m-client-app
│   │   └── lwm2m-server-app
│   ├── modbus
│   │   ├── modbus-abstract
│   │   ├── modbus-ascii
│   │   │   ├── modbus-ascii-master-app
│   │   │   └── modbus-ascii-slave-app
│   │   ├── modbus-rtu
│   │   │   ├── modbus-rtu-master-app
│   │   │   └── modbus-rtu-slave-app
│   │   ├── modbus-tcp
│   │   │   ├── modbus-tcp-listener-app
│   │   │   ├── modbus-tcp-master-app
│   │   │   └── modbus-tcp-slave-app
│   │   └── modbus-udp
│   │       ├── modbus-udp-master-app
│   │       └── modbus-udp-slave-app
│   ├── mqtt
│   │   ├── mqtt-client-app
│   │   └── mqtt-server
│   ├── opc
│   │   └── opc-client-app
│   ├── rxtx
│   │   └── rxtx-app
│   ├── tcp
│   │   ├── tcp-client-app
│   │   └── tcp-server-app
│   ├── udp
│   │   ├── udp-multicast-app
│   │   └── udp-peer-app
│   └── websocket
│       ├── ws-client-app
│       └── ws-server-app
└── rpc
    ├── consul
    ├── grpc
    │   ├── grpc-xxx-api
    │   ├── grpc-xxx-client
    │   └── grpc-xxx-server
    └── thrift
        ├── thrift-xxx-api
        ├── thrift-xxx-client
        ├── thrift-xxx-server-boot
        └── thrift-xxx-server-cloud


```