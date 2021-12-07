package com.dreamcc.driver.virtual.bean;

import com.dreamcc.driver.virtual.dto.DriverDTO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DriverProperty
 * @Description 驱动配置文件
 * @date 2021/12/6 15:06
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "driver")
public class DriverProperty {

    /**
     * 基本信息
     */
    private DriverDTO driverInfo;

}
