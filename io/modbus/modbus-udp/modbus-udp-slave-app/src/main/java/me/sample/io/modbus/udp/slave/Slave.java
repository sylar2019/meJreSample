package me.sample.io.modbus.udp.slave;

import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.modbus.ModbusExpress;
import me.sample.io.modbus.abs.AbstractSlave;
import org.springframework.stereotype.Component;

/**
 * @author : sylar
 * @fullName : me.sample.io.opc.client.Client
 * @createDate : 2020/8/13
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class Slave extends AbstractSlave {
    @Override
    protected Pipe buildPipe() {
        return ModbusExpress.udpSlave(9999,false);
    }

    @Override
    public String getName() {
        return "Udp Slave";
    }
}
