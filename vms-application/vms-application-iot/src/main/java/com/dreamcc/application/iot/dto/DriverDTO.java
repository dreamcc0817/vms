package com.dreamcc.application.iot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cloud-cc
 * @ClassName DriverDTO
 * @Description 驱动传输对象
 * @date 2021/11/30 14:55
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO implements Serializable {
    private Long id;
}
