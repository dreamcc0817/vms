package com.dreamcc.application.iot;

import com.dreamcc.application.iot.dto.ProfileDTO;
import com.dreamcc.application.iot.query.ProfileQuery;
import com.dreamcc.domain.iot.domain.aggregate.profile.Profile;

import java.util.List;

/**
 * @author cloud-cc
 * @ClassName ProfileQueryApplication
 * @Description 模板查询类
 * @date 2021/12/8 10:22
 * @Version 1.0
 */
public interface ProfileQueryApplication {

    /**
     * 获取模板列表
     *
     * @param profileQuery 查询条件
     * @return List<ProfileDTO>
     */
    List<ProfileDTO> getProfileList(ProfileQuery profileQuery);

    /**
     * 根据ID获取模板
     *
     * @param profileId 模板ID
     * @return ProfileDTO
     */
    ProfileDTO getProfileById(Long profileId);

    /**
     * 根据模板名称查找模板
     *
     * @param profileName 名称
     * @return 模板信息
     */
    Profile getProfileByName(String profileName);
}
