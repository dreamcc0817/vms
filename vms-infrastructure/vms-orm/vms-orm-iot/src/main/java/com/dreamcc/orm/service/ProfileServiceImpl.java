package com.dreamcc.orm.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dreamcc.domain.iot.domain.Profile;
import com.dreamcc.domain.iot.repository.ProfileRepository;
import com.dreamcc.orm.dao.ProfileDAO;
import com.dreamcc.orm.mapstruct.ProfilePoMapper;
import com.dreamcc.orm.po.ProfilePO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private final ProfilePoMapper poMapper;

    public ProfileServiceImpl(ProfileDAO profileDAO, ProfilePoMapper poMapper) {
        this.profileDAO = profileDAO;
        this.poMapper = poMapper;
    }

    @Override
    public void add(Profile profile) {
        ProfilePO profilePo = poMapper.profileToPo(profile);
        profileDAO.insert(profilePo);
    }

    @Override
    public void update(Profile profile) {
        ProfilePO profilePo = poMapper.profileToPo(profile);
        profileDAO.updateById(profilePo);
    }

    @Override
    public void delete(Long id) {
        profileDAO.deleteById(id);
    }

    @Override
    public Profile getById(Long id) {
        ProfilePO profilePo = profileDAO.selectById(id);
        return poMapper.poToProfile(profilePo);
    }

    @Override
    public Profile getByName(String profileName) {
        LambdaQueryWrapper<ProfilePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProfilePO::getName, profileName);
        ProfilePO profilePo = profileDAO.selectOne(wrapper);
        return poMapper.poToProfile(profilePo);
    }

    @Override
    public List<Profile> getList(Profile profile) {
        LambdaQueryWrapper<ProfilePO> wrapper = new LambdaQueryWrapper<>();
        if (Optional.ofNullable(profile).isPresent()) {
            if (StrUtil.isNotBlank(profile.getName())) {
                wrapper.like(ProfilePO::getName, profile.getName());
            }
        }
        List<ProfilePO> profilePoList = profileDAO.selectList(wrapper);
        return profilePoList.stream().map(poMapper::poToProfile).collect(Collectors.toList());
    }
}
