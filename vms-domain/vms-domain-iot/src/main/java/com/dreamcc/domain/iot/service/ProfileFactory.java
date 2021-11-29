package com.dreamcc.domain.iot.service;

import com.dreamcc.domain.iot.domain.Profile;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName ProfileFactory
 * @Description 模板工厂类
 * @date 2021/11/27 13:56
 * @Version 1.0
 */
@Component
public class ProfileFactory {

    private final IdGenerator idGenerator;

    public ProfileFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Profile create(String profileName, String profileDesc) {
        Long profileId = idGenerator.snowFlakeId();
        return Profile.createProfile(profileId, profileName, profileDesc);
    }
}
