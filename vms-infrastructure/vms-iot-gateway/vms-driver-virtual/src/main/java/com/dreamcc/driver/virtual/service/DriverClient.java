package com.dreamcc.driver.virtual.service;

import com.dreamcc.common.core.domain.R;
import com.dreamcc.driver.virtual.dto.DriverDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author cloud-cc
 * @ClassName DriverClient
 * @Description 驱动请求类
 * @date 2021/12/7 09:05
 * @Version 1.0
 */
@FeignClient(name = "vms-iot")
public interface DriverClient {

    @PostMapping("/driver")
    R register(@RequestBody DriverDTO driverDTO);
}
