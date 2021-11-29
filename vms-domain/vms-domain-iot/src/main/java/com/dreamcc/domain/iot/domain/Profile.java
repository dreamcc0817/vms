package com.dreamcc.domain.iot.domain;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * @author cloud-cc
 * @ClassName Profile
 * @Description 模板
 * @date 2021/11/26 20:14
 * @Version 1.0
 */
@Getter
@Builder
public class Profile {

    private Long id;

    @NotEmpty(message = "name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$",
            message = "invalid name,contains invalid characters or length is not in the range of 2~32")
    private String name;

    private Boolean enable;

    private String description;

    private Set<Long> pointIds;

    /**
     * 创建模板
     *
     * @param profileId   ID
     * @param name        模板名称
     * @param description 描述
     * @return 模板
     */
    public static Profile createProfile(Long profileId, String name, String description) {
        Profile profile = Profile.builder()
                .id(profileId)
                .name(name)
                .description(description)
                .enable(true)
                .build();
        return profile;
    }

}
