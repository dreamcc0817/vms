package com.dreamcc.application.iot;

import com.dreamcc.application.iot.dto.DeviceDTO;
import com.dreamcc.application.iot.mapstruct.DeviceMap;
import com.dreamcc.domain.iot.domain.Device;
import com.dreamcc.domain.iot.repository.DeviceRepository;
import com.dreamcc.domain.iot.service.DeviceFactory;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DeviceApplication
 * @Description 设备
 * @date 2021/11/30 10:26
 * @Version 1.0
 */
@Component
public class DeviceApplication {

    private final DeviceRepository deviceRepository;

    private final DeviceFactory deviceFactory;

    private final DeviceMap deviceMap;

    public DeviceApplication(DeviceRepository deviceRepository, DeviceFactory deviceFactory, DeviceMap deviceMap) {
        this.deviceRepository = deviceRepository;
        this.deviceFactory = deviceFactory;
        this.deviceMap = deviceMap;
    }

    /**
     * 创建设备
     *
     * @param deviceDTO 设备
     */
    public void create(DeviceDTO deviceDTO) {
        Device device = deviceMap.dtoToDevice(deviceDTO);
        deviceFactory.create(device);
        deviceRepository.save(device);
    }

    /**
     * 根据ID获取设备
     *
     * @param id ID
     * @return 设备信息
     */
    public Device getById(Long id) {
        return deviceRepository.getById(id);
    }
}
