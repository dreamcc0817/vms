package com.dreamcc.service.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamcc.service.device.entity.ServerDevice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.device.dao
 * @Description: 服务器设备业务逻辑处理类
 * @Author: dreamcc
 * @Date: 2019/12/23 15:03
 * @Version: V1.0
 */
@Mapper
public interface ServerDeviceMapper extends BaseMapper<ServerDevice> {

}
