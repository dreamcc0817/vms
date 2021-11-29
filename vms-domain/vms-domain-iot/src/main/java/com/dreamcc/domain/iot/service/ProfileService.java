package com.dreamcc.domain.iot.service;

import com.dreamcc.domain.iot.domain.Profile;
import com.dreamcc.domain.iot.repository.ProfileRepository;
import org.springframework.stereotype.Service;

/**
 * @author cloud-cc
 * @ClassName ProfileService
 * @Description 模板领域层
 * @date 2021/11/27 13:51
 * @Version 1.0
 */
@Service
public class ProfileService {

    private final ProfileFactory profileFactory;

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileFactory profileFactory, ProfileRepository profileRepository) {
        this.profileFactory = profileFactory;
        this.profileRepository = profileRepository;
    }

    public Profile save(String profileName,String profileDesc){
        return profileFactory.create(profileName,profileDesc);
    }

    public Profile getById(Long id) {
        return profileRepository.getById(id);
    }
}
