package com.dreamcc.application.iot.mapstruct;

import com.dreamcc.application.iot.dto.DeviceDTO;
import com.dreamcc.domain.iot.domain.Device;
import org.mapstruct.Mapper;

/**
 * @author cloud-cc
 * @ClassName DeviceMap
 * @Description 转换类
 * @date 2021/11/30 15:01
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DeviceMap {

    /**
     * dto转entity
     *
     * @param deviceDTO dto
     * @return entity
     */
    Device dtoToDevice(DeviceDTO deviceDTO);

    /**
     * entity转dto
     *
     * @param device entity
     * @return dto
     */
    DeviceDTO deviceToDto(Device device);
}
