package me.sample.io.modbus.tcp.master;

import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.modbus.IpParam;
import me.java.library.io.store.modbus.ModbusExpress;
import me.sample.io.modbus.abs.AbstractMaster;
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
public class Master extends AbstractMaster {

    @Override
    public String getName() {
        return "Tcp Master";
    }


    @Override
    protected Pipe buildPipe() {
        IpParam param = new IpParam("127.0.0.1", 9999);
        return ModbusExpress.tcpMaster(param, true);
    }
}
