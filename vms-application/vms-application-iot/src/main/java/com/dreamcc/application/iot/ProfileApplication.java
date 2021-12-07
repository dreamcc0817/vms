package com.dreamcc.application.iot;

import com.dreamcc.application.iot.command.CreateProfileCommand;
import com.dreamcc.application.iot.dto.ProfileDTO;
import com.dreamcc.application.iot.exception.DuplicateException;
import com.dreamcc.application.iot.mapstruct.ProfileMapper;
import com.dreamcc.application.iot.query.ProfileQuery;
import com.dreamcc.domain.iot.domain.Profile;
import com.dreamcc.domain.iot.domain.valueObject.CacheConstants;
import com.dreamcc.domain.iot.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@Slf4j
@Component
public class ProfileApplication {

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    public ProfileApplication(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    /**
     * 获取模板列表
     *
     * @param profileQuery 查询条件
     * @return List<ProfileDTO>
     */
    @Cacheable(value = CacheConstants.PROFILE + CacheConstants.LIST, keyGenerator = "commonKeyGenerator", unless = "#result == null")
    public List<ProfileDTO> list(ProfileQuery profileQuery) {
        Profile profile = profileMapper.queryToProfile(profileQuery);
        return profileRepository.getList(profile).stream().map(profileMapper::profileToDto).collect(Collectors.toList());
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
        Profile isExist = profileRepository.getByName(createProfileCommand.getName());
        if (Optional.ofNullable(isExist).isPresent()) {
            throw new DuplicateException("The profile already exists");
        } else {
            profileRepository.add(profile);
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
        profileRepository.delete(id);
    }

    /**
     * 根据ID获取模板
     *
     * @param id id
     * @return 模板
     */
    @Cacheable(value = CacheConstants.PROFILE + CacheConstants.ID, key = "#id", unless = "#result==null")
    public Profile getById(Long id) {
        return profileRepository.getById(id);
    }

}
