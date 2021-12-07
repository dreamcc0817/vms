package com.dreamcc.application.iot.query;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author cloud-cc
 * @ClassName ProfileQuery
 * @Description 模板查询条件
 * @date 2021/12/7 17:07
 * @Version 1.0
 */
@Data
public class ProfileQuery {

    private Long id;

    @NotEmpty(message = "name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$",
            message = "invalid name,contains invalid characters or length is not in the range of 2~32")
    private String name;

    private Integer enable;

    private String description;
}
