package com.dreamcc.application.iot;

import com.dreamcc.application.iot.dto.ProfileDTO;
import com.dreamcc.application.iot.exception.DuplicateException;
import com.dreamcc.application.iot.mapstruct.ProfileMapper;
import com.dreamcc.domain.iot.domain.Profile;
import com.dreamcc.domain.iot.domain.valueObject.CacheConstants;
import com.dreamcc.domain.iot.repository.ProfileRepository;
import com.dreamcc.domain.iot.service.ProfileFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author cloud-cc
 * @ClassName ProfileApplication
 * @Description 模板应用层
 * @date 2021/11/27 09:26
 * @Version 1.0
 */
@Component
public class ProfileApplication {

    private final ProfileFactory profileFactory;

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    public ProfileApplication(ProfileFactory profileFactory, ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileFactory = profileFactory;
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    /**
     * 获取模板列表
     *
     * @return List<ProfileDTO>
     */
    public List<ProfileDTO> list(ProfileDTO profileDTO) {
        Profile profile = profileMapper.dtoToProfile(profileDTO);
        return profileRepository.getList(profile).stream().map(profileMapper::profileToDto).collect(Collectors.toList());
    }

    /**
     * 保存模板
     *
     * @param profileDTO 模板信息
     * @return profile
     */
    @Caching(
            put = {
                    @CachePut(value = CacheConstants.PROFILE + CacheConstants.ID, key = "#profileDTO.profileId", condition = "#result != null")
                    , @CachePut(value = CacheConstants.PROFILE + CacheConstants.NAME, key = "#profileDTO.profileName", condition = "#result != null")
            }
    )
    public ProfileDTO save(ProfileDTO profileDTO) {
        Profile profile = profileMapper.dtoToProfile(profileDTO);
        Profile isExist = profileRepository.getByName(profileDTO.getProfileName());
        if (Optional.ofNullable(isExist).isPresent()) {
            throw new DuplicateException("The profile already exists");
        } else {
            profileFactory.create(profile);
            profileRepository.save(profile);
        }
        return profileMapper.profileToDto(profile);
    }

    /**
     * 根据ID获取模板
     *
     * @param id id
     * @return 模板
     */
    public Profile getById(Long id) {
        return profileRepository.getById(id);
    }
}
