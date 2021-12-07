package com.dreamcc.api.iot.controller;

import com.dreamcc.application.iot.DriverApplication;
import com.dreamcc.application.iot.dto.DriverDTO;
import com.dreamcc.common.core.domain.R;
import com.dreamcc.common.core.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cloud-cc
 * @ClassName DriverController
 * @Description 驱动API
 * @date 2021/12/6 08:25
 * @Version 1.0
 */
@Api("驱动管理")
@RestController
@RequestMapping("/driver")
public class DriverController extends BaseController {

    private final DriverApplication driverApplication;

    public DriverController(DriverApplication driverApplication) {
        this.driverApplication = driverApplication;
    }

    @ApiOperation("添加驱动")
    @PostMapping
    public R register(@RequestBody DriverDTO driverDTO){
        driverApplication.registry(driverDTO);
        return R.ok();
    }

}
