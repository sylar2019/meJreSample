package me.sample.io.lwm2m.client.instance;

import me.java.library.io.store.lwm2m.client.AbstractInstanceEnabler;
import me.java.library.utils.base.RandomUtils;
import org.eclipse.leshan.client.servers.ServerIdentity;
import org.eclipse.leshan.core.response.ReadResponse;

/**
 * @author : sylar
 * @fullName : me.sample.io.lwm2m.client.instance.Pm25Sensor
 * @createDate : 2020/8/13
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class Pm25Sensor extends AbstractInstanceEnabler {

    private static final String UNIT = "%";
    private static final int SENSOR_VALUE = 5700;
    private static final int UNITS = 5701;
    private static final int MAX_MEASURED_VALUE = 5602;
    private static final int MIN_MEASURED_VALUE = 5601;
    private static final int RESET_MIN_MAX_MEASURED_VALUES = 5605;


    @Override
    public ReadResponse read(ServerIdentity identity, int resourceId) {

        switch (resourceId) {
            case MIN_MEASURED_VALUE:
                return ReadResponse.success(resourceId, 1);
            case MAX_MEASURED_VALUE:
                return ReadResponse.success(resourceId, 100);
            case RESET_MIN_MAX_MEASURED_VALUES:
                return ReadResponse.success(resourceId, true);
            case SENSOR_VALUE:
                return ReadResponse.success(resourceId, RandomUtils.getRandom(30, 80));
            case UNITS:
                return ReadResponse.success(resourceId, UNIT);
            default:
                return super.read(identity, resourceId);
        }
    }
}
