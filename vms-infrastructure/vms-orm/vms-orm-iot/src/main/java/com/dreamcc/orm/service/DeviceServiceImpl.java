package com.dreamcc.orm.service;

import com.dreamcc.domain.iot.domain.Device;
import com.dreamcc.domain.iot.repository.DeviceRepository;
import org.springframework.stereotype.Service;

/**
 * @author cloud-cc
 * @ClassName DeviceServiceImpl
 * @Description 设备
 * @date 2021/12/1 13:58
 * @Version 1.0
 */
@Service
public class DeviceServiceImpl implements DeviceRepository {
    @Override
    public void save(Device device) {

    }

    @Override
    public Device getById(Long id) {
        return null;
    }
}
