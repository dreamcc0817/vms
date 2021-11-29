package com.dreamcc.domain.iot.domain;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author cloud-cc
 * @ClassName Driver
 * @Description 驱动信息
 * @date 2021/11/29 10:30
 * @Version 1.0
 */
@Getter
@Builder
public class Driver {

    private Long id;

    @NotBlank(message = "name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$", message = "invalid name,contains invalid characters or length is not in the range of 2~32")
    private String name;

    @NotBlank(message = "service name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9][A-Za-z0-9-_]{1,31}$", message = "invalid service name,contains invalid characters or length is not in the range of 2~32")
    private String serviceName;

    @NotBlank(message = "host can't be empty")
    @Pattern(regexp = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$", message = "invalid host")
    private String host;

    private String type;

    @Min(value = 8600, message = "invalid port,port range is 8600-8799")
    @Max(value = 8799, message = "invalid port,port range is 8600-8799")
    private Integer port;

    private Boolean enable;

    private String status;

    private String description;

    public Driver(String name, String serviceName, String host, Integer port, String type) {
        super();
        this.name = name;
        this.serviceName = serviceName;
        this.host = host;
        this.port = port;
        this.type = type;
    }
}
