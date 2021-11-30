package com.dreamcc.domain.iot.repository;

import com.dreamcc.domain.iot.domain.Device;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DeviceRepository
 * @Description 设备仓储类
 * @date 2021/11/30 09:12
 * @Version 1.0
 */
@Component
public interface DeviceRepository {

    /**
     * 保存设备
     *
     * @param device 设备信息
     */
    void save(Device device);

    /**
     * 通过ID获取设备
     *
     * @param id ID
     * @return 设备
     */
    Device getById(Long id);
}
