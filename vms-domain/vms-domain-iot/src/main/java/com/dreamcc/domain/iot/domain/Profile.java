package com.dreamcc.domain.iot.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

/**
 * @author cloud-cc
 * @ClassName Profile
 * @Description 模板
 * @date 2021/11/26 20:14
 * @Version 1.0
 */
@Data
@Builder
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private Long id;

    @NotEmpty(message = "name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$",
            message = "invalid name,contains invalid characters or length is not in the range of 2~32")
    private String name;

    private Integer enable;

    private String description;

    private Set<Long> pointIds;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 逻辑删除符
     */
    private Integer deleted;

    /**
     * 创建模板
     *
     * @return 模板
     */
    public Profile create(Long id) {
        this.id = id;
        return this;
    }

}
