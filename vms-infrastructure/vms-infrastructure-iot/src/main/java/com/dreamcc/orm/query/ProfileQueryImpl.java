package com.dreamcc.orm.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dreamcc.application.iot.ProfileQueryApplication;
import com.dreamcc.application.iot.dto.ProfileDTO;
import com.dreamcc.application.iot.query.ProfileQuery;
import com.dreamcc.domain.iot.domain.aggregate.profile.Profile;
import com.dreamcc.orm.dao.ProfileDAO;
import com.dreamcc.orm.mapstruct.ProfilePoMapper;
import com.dreamcc.orm.dataobject.ProfileDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author cloud-cc
 * @ClassName ProfileQueryImpl
 * @Description 模板查询类
 * @date 2021/12/8 10:55
 * @Version 1.0
 */
@Service
public class ProfileQueryImpl implements ProfileQueryApplication {

    private final ProfileDAO profileDAO;

    private final ProfilePoMapper profileMapper;

    public ProfileQueryImpl(ProfileDAO profileDAO, ProfilePoMapper profileMapper) {
        this.profileDAO = profileDAO;
        this.profileMapper = profileMapper;
    }

    @Override
    public List<ProfileDTO> getProfileList(ProfileQuery profileQuery) {
        LambdaQueryWrapper<ProfileDO> wrapper = new LambdaQueryWrapper<>();
        if (Optional.ofNullable(profileQuery).isPresent()) {
            if (StrUtil.isNotBlank(profileQuery.getName())) {
                wrapper.like(ProfileDO::getName, profileQuery.getName());
            }
        }
        List<ProfileDO> profileDOList = profileDAO.selectList(wrapper);
        return profileDOList.stream().map(profileMapper::doToDto).collect(Collectors.toList());
    }

    @Override
    public ProfileDTO getProfileById(Long profileId) {
        return null;
    }

    @Override
    public Profile getProfileByName(String profileName) {
        return null;
    }
}
