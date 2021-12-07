package com.dreamcc.domain.iot.domain;

import com.dreamcc.common.constans.BaseDomain;
import com.dreamcc.domain.iot.service.IdGenerator;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
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
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends BaseDomain implements Serializable {

    private Long id;

    @NotEmpty(message = "name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$",
            message = "invalid name,contains invalid characters or length is not in the range of 2~32")
    private String name;

    private Integer enable;

    private String description;

    private Set<Point> pointIds;


    /**
     * 创建模板
     *
     * @return 模板
     */
    public static Profile create(String name, String description) {
        Profile profile = Profile.builder()
                .id(IdGenerator.snowFlakeId())
                .name(name)
                .enable(1)
                .description(description)
                .build();
        //TODO 事件event
        return profile;
    }

}
