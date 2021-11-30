package com.dreamcc.domain.iot.domain;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * @author cloud-cc
 * @ClassName Device
 * @Description 设备
 * @date 2021/11/30 08:09
 * @Version 1.0
 */
@Getter
@Builder
public class Device {

    /**
     * ID
     */
    private Long id;

    /**
     * 设备名称
     */
    @NotBlank(message = "name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$"
            , message = "invalid name,contains invalid characters or length is not in the range of 2~32")
    private String name;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 所属驱动
     */
    @NotNull(message = "driver can't be empty")
    private Driver driver;

    /**
     * 目标集合
     */
    private Set<Profile> profiles;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建设备
     *
     * @param id id
     * @return 设备
     */
    public Device create(Long id) {
        this.id = id;
        this.enable = Boolean.TRUE;
        return this;
    }

}
