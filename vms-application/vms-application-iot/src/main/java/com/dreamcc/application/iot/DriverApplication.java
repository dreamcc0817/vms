package com.dreamcc.application.iot;

import com.dreamcc.domain.iot.domain.Driver;
import com.dreamcc.domain.iot.repository.DriverRepository;
import com.dreamcc.domain.iot.service.DriverFactory;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DriverApplication
 * @Description 驱动Component
 * @date 2021/11/30 09:40
 * @Version 1.0
 */
@Component
public class DriverApplication {

    private final DriverFactory driverFactory;

    private final DriverRepository driverRepository;

    public DriverApplication(DriverFactory driverFactory, DriverRepository driverRepository) {
        this.driverFactory = driverFactory;
        this.driverRepository = driverRepository;
    }

    /**
     * 注册驱动
     *
     * @param driver 驱动
     */
    public void save(Driver driver) {
        driverFactory.registry(driver);
        driverRepository.save(driver);
    }

    /**
     * 根据ID获取设备
     *
     * @param id ID
     * @return 设备信息
     */
    public Driver getById(Long id) {
        return driverRepository.getById(id);
    }
}
