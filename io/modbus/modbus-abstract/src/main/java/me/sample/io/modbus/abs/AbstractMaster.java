package me.sample.io.modbus.abs;

import com.serotonin.modbus4j.msg.ReadInputRegistersResponse;
import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.modbus.FunctionType;
import me.java.library.io.store.modbus.cmd.ModbusRequestCmd;
import me.java.library.io.store.modbus.cmd.ModbusResponseCmd;
import me.sample.io.appFrame.client.AbstractClient;

import java.util.Arrays;

/**
 * @author : sylar
 * @fullName : me.sample.io.opc.client.Client
 * @createDate : 2020/8/13
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public abstract class AbstractMaster extends AbstractClient {

    @Override
    public void sendTestCmd() {
        int slaveId = 1;
        ModbusRequestCmd req = new ModbusRequestCmd(FunctionType.READ_INPUT_REGISTERS, slaveId);
        send(req);
    }

    @Override
    protected void onReceivedCmd(Pipe pipe, Cmd cmd) {
        if (cmd instanceof ModbusResponseCmd) {
            logger.info("### onReceived: ModbusResponseCmd \n");
            ModbusResponseCmd resCmd = (ModbusResponseCmd) cmd;
            FunctionType functionType = resCmd.getFunctionType();
            logger.info("### functionType: " + functionType.toString());

            switch (functionType) {
                case READ_INPUT_REGISTERS:
                    ReadInputRegistersResponse readInputRegistersResponse = resCmd.getResponse();
                    if (readInputRegistersResponse.isException()) {
                        logger.error("### error:\n" + readInputRegistersResponse.getExceptionMessage());
                    } else {
                        logger.info("### response:\n" + Arrays.toString(readInputRegistersResponse.getShortData()));
                    }
                    break;
                //@formatter:off
                case READ_COILS:break;
                case READ_DISCRETE_INPUTS:break;
                case READ_HOLDING_REGISTERS:break;
                case READ_EXCEPTION_STATUS:break;
                case REPORT_SLAVE_ID:break;
                case WRITE_COIL:break;
                case WRITE_COILS:break;
                case WRITE_REGISTER:break;
                case WRITE_REGISTERS:break;
                case WRITE_MASK_REGISTER:break;
                default: break;
                //@formatter:on
            }

        }
    }
}
