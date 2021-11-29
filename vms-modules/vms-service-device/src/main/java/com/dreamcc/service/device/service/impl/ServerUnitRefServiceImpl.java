package com.dreamcc.service.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamcc.service.device.entity.ServerUnitRef;
import com.dreamcc.service.device.mapper.ServerUnitRefMapper;
import com.dreamcc.service.device.service.IServerUnitRefService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.device.service.impl
 * @Description: 服务器与服务单元业务处理类
 * @Author: dreamcc
 * @Date: 2020/1/2 11:35
 * @Version: V1.0
 */
@Slf4j
@Service("serverUnitRefService")
@AllArgsConstructor
public class ServerUnitRefServiceImpl extends ServiceImpl<ServerUnitRefMapper, ServerUnitRef> implements IServerUnitRefService {
}
