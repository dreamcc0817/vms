package com.dreamcc.domain.iot.domain.aggregate.profile;

import com.dreamcc.domain.iot.common.annotation.Entity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author cloud-cc
 * @ClassName Point
 * @Description 位号
 * @date 2021/11/30 08:41
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Entity
public class Point implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 位号名称
     */
    @NotBlank(message = "name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$"
            , message = "invalid name,contains invalid characters or length is not in the range of 2~32")
    private String name;

    /**
     * 数据类型：string/int/double/float/long/boolean
     */
    private String type;

    /**
     * 读写标识：读写标识：0读，1写，2读写
     */
    private Short rw;

    /**
     * 基础值
     */
    private Float base;

    /**
     * 最小值
     */
    private Float minimum;

    /**
     * 最大值
     */
    private Float maximum;

    /**
     * 倍数
     */
    private Float multiple;

    /**
     * 累积标识
     */
    private Boolean accrue;

    /**
     * 格式数据，Java格式 %.3f
     */
    private String format;

    /**
     * 单位
     */
    private String unit;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 描述
     */
    private String description;

    /**
     * 所属模板
     */
    private Profile profile;

    /**
     * 创建位号
     *
     * @param id ID
     * @return 位号
     */
    public Point create(Long id) {
        this.id = id;
        this.enable = Boolean.TRUE;
        return this;
    }
}
