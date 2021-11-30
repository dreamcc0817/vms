package com.dreamcc.domain.iot.service;

import com.dreamcc.domain.iot.domain.Device;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DeviceFactory
 * @Description 设备工厂类
 * @date 2021/11/30 10:28
 * @Version 1.0
 */
@Component
public class DeviceFactory {

    private final IdGenerator idGenerator;

    public DeviceFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Device create(Device device){
        Long id = idGenerator.snowFlakeId();
        return device.create(id);
    }
}
