package com.dreamcc.domain.iot.domain;

import com.dreamcc.domain.iot.domain.valueObject.StatusConstants;
import lombok.Getter;

/**
 * @author cloud-cc
 * @ClassName StatusCenter
 * @Description 驱动/设备状态管理
 * @date 2021/11/29 20:50
 * @Version 1.0
 */
@Getter
public class StatusCenter {

    /**
     * 驱动/设备 ID
     */
    private Long id;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 启动
     *
     * @return 在线状态
     */
    public StatusCenter start(){
        this.status = StatusConstants.ONLINE.getCode();
        return this;
    }

    /**
     * 停止
     *
     * @return 离线状态
     */
    public StatusCenter stop(){
        this.status = StatusConstants.OFF_LINE.getCode();
        return this;
    }

}
