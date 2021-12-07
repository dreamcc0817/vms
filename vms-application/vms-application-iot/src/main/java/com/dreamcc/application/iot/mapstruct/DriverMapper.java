package com.dreamcc.application.iot.mapstruct;

import com.dreamcc.application.iot.dto.DriverDTO;
import com.dreamcc.domain.iot.domain.Driver;
import org.mapstruct.Mapper;

/**
 * @author cloud-cc
 * @ClassName DriverMap
 * @Description 转换类
 * @date 2021/11/30 15:06
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DriverMapper {

    /**
     * entity转dto
     *
     * @param driver entity
     * @return dto
     */
    DriverDTO driverToDto(Driver driver);
}
