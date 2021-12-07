package com.dreamcc.driver.virtual.service;

import com.dreamcc.driver.virtual.bean.DriverProperty;
import com.dreamcc.driver.virtual.dto.DriverDTO;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DriverRegister
 * @Description 驱动注册
 * @date 2021/12/7 09:29
 * @Version 1.0
 */
@Component
public class DriverRegister implements ApplicationRunner {

    private final DriverProperty driverProperty;

    private final DriverClient driverClient;

    public DriverRegister(DriverProperty driverProperty, DriverClient driverClient) {
        this.driverProperty = driverProperty;
        this.driverClient = driverClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        DriverDTO driverInfo = driverProperty.getDriverInfo();

        driverClient.register(driverInfo);

    }
}
