package me.sample.io.lwm2m.client.instance;

import me.java.library.io.store.lwm2m.client.AbstractInstanceEnabler;
import me.java.library.utils.base.RandomUtils;
import org.eclipse.leshan.client.servers.ServerIdentity;
import org.eclipse.leshan.core.response.ReadResponse;

/**
 * @author : sylar
 * @fullName : me.sample.io.lwm2m.client.instance.LocationSensor
 * @createDate : 2020/8/13
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class LocationSensor extends AbstractInstanceEnabler {

    @Override
    public ReadResponse read(ServerIdentity identity, int resourceid) {
        return ReadResponse.success(resourceid, RandomUtils.getRandom(1, 100));
    }
}
