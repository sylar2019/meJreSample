package me.sample.io.modbus.rtu.master;

import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.modbus.ModbusExpress;
import me.java.library.utils.rxtx.RxtxParam;
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
        return "Rtu Master";
    }

    @Override
    protected Pipe buildPipe() {
        RxtxParam param = new RxtxParam("COM1", 9600);
        return ModbusExpress.rtuMaster(param);
    }

}
