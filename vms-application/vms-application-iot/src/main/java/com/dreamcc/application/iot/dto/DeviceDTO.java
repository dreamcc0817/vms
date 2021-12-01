package com.dreamcc.application.iot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cloud-cc
 * @ClassName DeviceDTO
 * @Description 设备传输对象
 * @date 2021/11/30 14:55
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO implements Serializable {
    private Long id;
}
