package com.dreamcc.application.iot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cloud-cc
 * @ClassName ProfileDTO
 * @Description 模板传输对象
 * @date 2021/11/27 14:38
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long profileId;

    private String profileName;

    private String profileDesc;

    private String enable;
}
