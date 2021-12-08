package com.dreamcc.api.iot.controller;

import com.dreamcc.application.iot.ProfileCommandApplication;
import com.dreamcc.application.iot.ProfileQueryApplication;
import com.dreamcc.application.iot.command.CreateProfileCommand;
import com.dreamcc.application.iot.query.ProfileQuery;
import com.dreamcc.common.core.domain.R;
import com.dreamcc.common.core.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author cloud-cc
 * @ClassName ProfileController
 * @Description 模板API
 * @date 2021/11/27 15:19
 * @Version 1.0
 */
@Api("模板管理")
@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController {

    private final ProfileCommandApplication profileCommandApplication;

    private final ProfileQueryApplication profileQueryApplication;

    public ProfileController(ProfileCommandApplication profileCommandApplication, ProfileQueryApplication profileQueryApplication) {
        this.profileCommandApplication = profileCommandApplication;
        this.profileQueryApplication = profileQueryApplication;
    }

    @ApiOperation(value = "查询模板列表")
    @GetMapping("/list")
    public R list(ProfileQuery profileQuery){
        startPage();
        return R.ok(profileQueryApplication.getProfileList(profileQuery));
    }

    @ApiOperation("获取模板")
    @GetMapping("/{profileId}")
    public R getProfile(@PathVariable("profileId")Long profileId){
        return R.ok(profileQueryApplication.getProfileById(profileId));
    }

    @ApiOperation("添加模板")
    @PostMapping
   public R addProfile(CreateProfileCommand createProfileCommand){
        profileCommandApplication.create(createProfileCommand);
        return R.ok();
    }
}
