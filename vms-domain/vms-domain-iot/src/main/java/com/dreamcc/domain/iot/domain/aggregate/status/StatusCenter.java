package com.dreamcc.domain.iot.domain.aggregate.status;

import com.dreamcc.domain.iot.common.StatusConstants;
import com.dreamcc.domain.iot.common.annotation.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cloud-cc
 * @ClassName StatusCenter
 * @Description 驱动/设备状态管理
 * @date 2021/11/29 20:50
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@AggregateRoot
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
