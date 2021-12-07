package com.dreamcc.application.iot.dto;

import com.dreamcc.common.constans.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cloud-cc
 * @ClassName ProfileDTO
 * @Description 模板传输对象
 * @date 2021/11/27 14:38
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO extends BaseDomain implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Integer enable;
}
