package com.dreamcc.domain.iot.repository;

import com.dreamcc.domain.iot.domain.aggregate.profile.Profile;

/**
 * @author cloud-cc
 * @ClassName ProfileRepository
 * @Description 模板仓库
 * @date 2021/11/27 09:12
 * @Version 1.0
 */
public interface ProfileRepository {

    /**
     * 保存
     *
     * @param profile 模板信息
     */
    void addProfile(Profile profile);

    /**
     * 更新
     *
     * @param profile 模板信息
     */
    void updateProfile(Profile profile);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteProfile(Long id);

    /**
     * 根据Id获取模板
     *
     * @param id 模板ID
     * @return 模板
     */
    Profile getProfileById(Long id);


    /**
     * 根据模板名称查找模板
     *
     * @param profileName 名称
     * @return 模板信息
     */
    Profile getProfileByName(String profileName);

}
