package com.dreamcc.domain.iot.repository;

import com.dreamcc.domain.iot.domain.Profile;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName ProfileRepository
 * @Description 模板仓库
 * @date 2021/11/27 09:12
 * @Version 1.0
 */
@Component
public interface ProfileRepository {

    /**
     * 保存
     *
     * @param profile 模板信息
     * @return 模板
     */
    void save(Profile profile);

    /**
     * 根据Id获取模板
     *
     * @param id 模板ID
     * @return 模板
     */
    Profile getById(Long id);

    /**
     * 根据模板名称查找模板
     *
     * @param profileName 名称
     * @return 模板信息
     */
    Profile getByName(String profileName);
}
