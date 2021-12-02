package com.dreamcc.domain.iot.service;

import com.dreamcc.domain.iot.domain.Driver;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DriverFactory
 * @Description 驱动工厂类
 * @date 2021/11/30 09:21
 * @Version 1.0
 */
@Component
public class DriverFactory {
    private final IdGenerator idGenerator;

    public DriverFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Driver add(Driver driver) {
        Long id = idGenerator.snowFlakeId();
        return driver.add(id);
    }
}
