package com.dreamcc.application.iot.mapstruct;

import com.dreamcc.application.iot.dto.DeviceDTO;
import com.dreamcc.domain.iot.domain.aggregate.device.Device;
import org.mapstruct.Mapper;

/**
 * @author cloud-cc
 * @ClassName DeviceMap
 * @Description 转换类
 * @date 2021/11/30 15:01
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DeviceMapper {

    /**
     * entity转dto
     *
     * @param device entity
     * @return dto
     */
    DeviceDTO deviceToDto(Device device);
}
