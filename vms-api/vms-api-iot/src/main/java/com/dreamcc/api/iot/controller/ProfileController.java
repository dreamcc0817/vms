package com.dreamcc.api.iot.controller;

import com.dreamcc.application.iot.ProfileApplication;
import com.dreamcc.application.iot.dto.ProfileDTO;
import com.dreamcc.common.core.domain.R;
import com.dreamcc.common.core.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author cloud-cc
 * @ClassName ProfileController
 * @Description 模板Controller
 * @date 2021/11/27 15:19
 * @Version 1.0
 */
@Api("模板管理")
@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController {

    private final ProfileApplication profileApplication;

    public ProfileController(ProfileApplication profileApplication) {
        this.profileApplication = profileApplication;
    }

    @ApiOperation(value = "查询模板列表")
    @GetMapping("/list")
    public R list(ProfileDTO profileDTO){
        startPage();
        return R.ok(profileApplication.list(profileDTO));
    }

    @ApiOperation("获取模板")
    @GetMapping("/{profileId}")
    public R getProfile(@PathVariable("profileId")Long profileId){
        return R.ok(profileApplication.getById(profileId));
    }

    @ApiOperation("添加模板")
    @PostMapping
    public R addProfile(ProfileDTO profileDTO){
        profileApplication.add(profileDTO);
        return R.ok();
    }
}
