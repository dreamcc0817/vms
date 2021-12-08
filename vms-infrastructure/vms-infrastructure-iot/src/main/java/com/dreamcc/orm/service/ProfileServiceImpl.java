package com.dreamcc.orm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dreamcc.domain.iot.domain.aggregate.profile.Profile;
import com.dreamcc.domain.iot.repository.ProfileRepository;
import com.dreamcc.orm.dao.ProfileDAO;
import com.dreamcc.orm.mapstruct.ProfilePoMapper;
import com.dreamcc.orm.dataobject.ProfileDO;
import org.springframework.stereotype.Service;

/**
 * @author cloud-cc
 * @ClassName ProfileServiceImpl
 * @Description 模板ORM
 * @date 2021/12/1 09:09
 * @Version 1.0
 */
@Service
public class ProfileServiceImpl implements ProfileRepository {

    private final ProfileDAO profileDAO;

    private final ProfilePoMapper profileMapper;

    public ProfileServiceImpl(ProfileDAO profileDAO, ProfilePoMapper profileMapper) {
        this.profileDAO = profileDAO;
        this.profileMapper = profileMapper;
    }

    @Override
    public void addProfile(Profile profile) {
        ProfileDO profileDO = profileMapper.profileToPo(profile);
        profileDAO.insert(profileDO);
    }

    @Override
    public void updateProfile(Profile profile) {
        ProfileDO profileDO = profileMapper.profileToPo(profile);
        profileDAO.updateById(profileDO);
    }

    @Override
    public void deleteProfile(Long id) {
        profileDAO.deleteById(id);
    }

    @Override
    public Profile getProfileById(Long id) {
        ProfileDO profileDO = profileDAO.selectById(id);
        return profileMapper.poToProfile(profileDO);
    }

    @Override
    public Profile getProfileByName(String profileName) {
        LambdaQueryWrapper<ProfileDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProfileDO::getName, profileName);
        ProfileDO profileDO = profileDAO.selectOne(wrapper);
        return profileMapper.poToProfile(profileDO);
    }

}
