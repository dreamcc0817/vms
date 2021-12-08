package com.dreamcc.application.iot;

import com.dreamcc.application.iot.command.CreateProfileCommand;
import com.dreamcc.application.iot.dto.ProfileDTO;
import com.dreamcc.application.iot.exception.DuplicateException;
import com.dreamcc.application.iot.mapstruct.ProfileMapper;
import com.dreamcc.domain.iot.common.CacheConstants;
import com.dreamcc.domain.iot.domain.aggregate.profile.Profile;
import com.dreamcc.domain.iot.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author cloud-cc
 * @ClassName ProfileApplication
 * @Description 模板应用层
 * @date 2021/11/27 09:26
 * @Version 1.0
 */
@Slf4j
@Component
public class ProfileCommandApplication {

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    public ProfileCommandApplication(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }


    /**
     * 保存模板
     *
     * @param createProfileCommand 创建模板命令
     * @return profile
     */
    @Caching(
            put = {
                    @CachePut(value = CacheConstants.PROFILE + CacheConstants.ID, key = "#result.id", condition = "#result != null")
                    ,@CachePut(value = CacheConstants.PROFILE + CacheConstants.NAME, key = "#createProfileCommand.name", condition = "#result != null")
            },
            evict = {
                    @CacheEvict(value = CacheConstants.PROFILE + CacheConstants.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public ProfileDTO create(CreateProfileCommand createProfileCommand) {
        Profile profile = Profile.create(createProfileCommand.getName(), createProfileCommand.getDescription());
        Profile isExist = profileRepository.getProfileByName(createProfileCommand.getName());
        if (Optional.ofNullable(isExist).isPresent()) {
            throw new DuplicateException("The profile already exists");
        } else {
            profileRepository.addProfile(profile);
        }
        log.info("添加模板：{}",profile);
        return profileMapper.profileToDto(profile);
    }


    /**
     * 删除模板
     *
     * @param id id
     */
    @Caching(
            evict = {
                    @CacheEvict(value = CacheConstants.PROFILE + CacheConstants.ID, key = "#id"),
                    @CacheEvict(value = CacheConstants.PROFILE + CacheConstants.NAME + CacheConstants.TYPE, allEntries = true),
                    @CacheEvict(value = CacheConstants.PROFILE + CacheConstants.LIST, allEntries = true)
            }
    )
    public void deleteById(Long id) {
        profileRepository.deleteProfile(id);
    }

    /**
     * 根据ID获取模板
     *
     * @param id id
     * @return 模板
     */
    @Cacheable(value = CacheConstants.PROFILE + CacheConstants.ID, key = "#id", unless = "#result==null")
    public Profile getById(Long id) {
        return profileRepository.getProfileById(id);
    }

}
