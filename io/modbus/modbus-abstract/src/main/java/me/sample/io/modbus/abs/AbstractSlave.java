package me.sample.io.modbus.abs;

import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.serotonin.modbus4j.ProcessImageListener;
import me.java.library.common.service.ConcurrentService;
import me.java.library.io.base.pipe.Pipe;
import me.java.library.io.store.modbus.slave.ModbusSlavePipe;
import me.java.library.io.store.modbus.slave.SlaveImage;
import me.java.library.utils.base.RandomUtils;
import me.sample.io.appFrame.server.AbstractServer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : sylar
 * @fullName : me.sample.io.modbus.abs.AbstractSlave
 * @createDate : 2020/8/13
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public abstract class AbstractSlave extends AbstractServer {

    ListenableScheduledFuture<?> future;
    List<SlaveImage> images;

    @Override
    protected void afterPipeBuilded(Pipe pipe) {
        super.afterPipeBuilded(pipe);
        assert pipe instanceof ModbusSlavePipe;
        ModbusSlavePipe slavePipe = (ModbusSlavePipe) pipe;
        slavePipe.addImage(createImage(1));
        slavePipe.addImage(createImage(2));
        slavePipe.addImage(createImage(3));
        images = slavePipe.getImages();

        future = ConcurrentService.getInstance().scheduleAtFixedRate(
                updateRunner,
                1,
                1,
                TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        future.cancel(true);
        super.stop();
    }

    SlaveImage createImage(int slaveId) {
        SlaveImage image = new SlaveImage(slaveId);
        image.addListener(new ImageListener(image));
        image.setAllowInvalidAddress(true);
        image.setInvalidAddressValue(Short.MIN_VALUE);

        return image;
    }

    Runnable updateRunner = new Runnable() {
        @Override
        public void run() {
            images.forEach(image -> {
                image.setExceptionStatus((byte) RandomUtils.getRandom(128));
                for (int i = 0; i < 10; i++) {
                    image.setCoil(i, RandomUtils.getRandom(0, 1) == 1);
                    image.setInput(i, RandomUtils.getRandom(0, 1) == 1);
                    image.setHoldingRegister(i, (short) RandomUtils.getRandom(1, 100));
                    image.setInputRegister(i, (short) RandomUtils.getRandom(1, 100));
                }
            });
        }
    };

    static class ImageListener implements ProcessImageListener {
        SlaveImage image;

        public ImageListener(SlaveImage image) {
            this.image = image;
        }

        @Override
        public void coilWrite(int offset, boolean oldValue, boolean newValue) {
            System.out.println("Coil at " + offset + " was set from " + oldValue + " to " + newValue);
            image.setCoil(offset, newValue);
        }

        @Override
        public void holdingRegisterWrite(int offset, short oldValue, short newValue) {
            System.out.println("HR at " + offset + " was set from " + oldValue + " to " + newValue);
            image.setHoldingRegister(offset, newValue);
        }
    }
}
