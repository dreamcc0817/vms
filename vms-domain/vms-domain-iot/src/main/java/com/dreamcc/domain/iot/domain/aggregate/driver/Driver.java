package com.dreamcc.domain.iot.domain.aggregate.driver;

import com.dreamcc.domain.iot.common.annotation.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author cloud-cc
 * @ClassName Driver
 * @Description 驱动信息
 * @date 2021/11/29 10:30
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AggregateRoot
public class Driver implements Serializable {

    private Long id;

    /**
     * 协议名称
     */
    @NotBlank(message = "name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$", message = "invalid name,contains invalid characters or length is not in the range of 2~32")
    private String name;

    /**
     * 协议服务名称
     */
    @NotBlank(message = "service name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9][A-Za-z0-9-_]{1,31}$", message = "invalid service name,contains invalid characters or length is not in the range of 2~32")
    private String serviceName;

    /**
     * 服务IP地址
     */
    @NotBlank(message = "host can't be empty")
    @Pattern(regexp = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$", message = "invalid host")
    private String host;

    /**
     * 服务IP端口
     */
    @Min(value = 8600, message = "invalid port,port range is 8600-8799")
    @Max(value = 8799, message = "invalid port,port range is 8600-8799")
    private Integer port;

    /**
     * 类型：driver/gateway
     */
    private String type;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 描述
     */
    private String description;

    /**
     * 添加驱动基本信息
     *
     * @param id id
     * @return 驱动信息
     */
    public Driver add(Long id) {
        this.id = id;
        this.enable = Boolean.TRUE;
        return this;
    }
}
