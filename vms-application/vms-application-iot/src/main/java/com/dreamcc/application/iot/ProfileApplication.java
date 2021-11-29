package com.dreamcc.application.iot;

import com.dreamcc.domain.iot.domain.Profile;
import com.dreamcc.domain.iot.service.ProfileService;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName ProfileApplication
 * @Description 模板应用层
 * @date 2021/11/27 09:26
 * @Version 1.0
 */
@Component
public class ProfileApplication {

    private final ProfileService profileService;

    public ProfileApplication(ProfileService profileService) {
        this.profileService = profileService;
    }

    public Profile save(Profile profile){
        return profileService.save(profile.getName(), profile.getDescription());
    }

    public Profile getById(Long id){
        return profileService.getById(id);
    }
}
