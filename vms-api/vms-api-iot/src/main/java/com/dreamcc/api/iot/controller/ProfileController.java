package com.dreamcc.api.iot.controller;

import com.dreamcc.api.iot.dto.ProfileDTO;
import com.dreamcc.api.iot.mapstruct.ProfileMap;
import com.dreamcc.application.iot.ProfileApplication;
import com.dreamcc.common.core.domain.R;
import com.dreamcc.common.core.web.controller.BaseController;
import com.dreamcc.domain.iot.domain.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cloud-cc
 * @ClassName ProfileController
 * @Description 模板Controller
 * @date 2021/11/27 15:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController {


    private final ProfileApplication profileApplication;

    private final ProfileMap profileMap;

    public ProfileController(ProfileApplication profileApplication, ProfileMap profileMap) {
        this.profileApplication = profileApplication;
        this.profileMap = profileMap;
    }

    @GetMapping("/list")
    public R list(){
        List<ProfileDTO> list = new ArrayList<>();
        list.add(new ProfileDTO(1L,"1","1","0"));
        list.add(new ProfileDTO(2L,"2","2","1"));
        return R.ok(list);
    }

    @RequestMapping("/{profileId}")
    public R getProfile(Long profileId){
        Profile profile = profileApplication.getById(profileId);
        return R.ok(profileMap.profileToDto(profile));
    }
}
