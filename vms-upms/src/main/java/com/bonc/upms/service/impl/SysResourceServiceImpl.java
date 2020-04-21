package com.bonc.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.upms.entity.SysResource;
import com.bonc.upms.mapper.SysResourceMapper;
import com.bonc.upms.service.ISysResourceService;
import org.springframework.stereotype.Service;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service.impl
 * @Description: 资源表(SysResource)表服务实现类
 * @Author: dreamcc
 * @Date: 2020-04-21 21:48:51
 * @Version: V1.0
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {
}