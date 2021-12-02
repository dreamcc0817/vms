package com.dreamcc.application.iot;

import com.dreamcc.application.iot.mq.event.DriverEvent;
import com.dreamcc.application.iot.mq.producer.DriverProducer;
import com.dreamcc.domain.iot.domain.Driver;
import com.dreamcc.domain.iot.domain.valueObject.DriverConstants;
import com.dreamcc.domain.iot.repository.DriverRepository;
import com.dreamcc.domain.iot.service.DriverFactory;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DriverApplication
 * @Description 驱动应用编排
 * @date 2021/11/30 09:40
 * @Version 1.0
 */
@Component
public class DriverApplication {

    private final DriverFactory driverFactory;

    private final DriverRepository driverRepository;

    private final DriverProducer driverProducer;

    public DriverApplication(DriverFactory driverFactory, DriverRepository driverRepository, DriverProducer driverProducer) {
        this.driverFactory = driverFactory;
        this.driverRepository = driverRepository;
        this.driverProducer = driverProducer;
    }

    /**
     * 注册驱动
     *
     * @param driver 驱动
     */
    public void registry(Driver driver) {

        driverFactory.add(driver);

        DriverEvent driverEvent = new DriverEvent(DriverConstants.DRIVER_REGISTER,driver);
        driverProducer.driverEventSender(driverEvent);
    }

    /**
     * 根据ID获取驱动
     *
     * @param id ID
     * @return 驱动信息
     */
    public Driver getById(Long id) {
        return driverRepository.getById(id);
    }
}
