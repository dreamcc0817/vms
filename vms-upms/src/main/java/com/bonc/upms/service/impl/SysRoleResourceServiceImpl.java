package com.bonc.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.upms.entity.SysRoleResource;
import com.bonc.upms.mapper.SysRoleResourceMapper;
import com.bonc.upms.service.ISysRoleResourceService;
import org.springframework.stereotype.Service;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service.impl
 * @Description: 角色资源表(SysRoleResource)表服务实现类
 * @Author: dreamcc
 * @Date: 2020-04-21 21:48:51
 * @Version: V1.0
 */
@Service("sysRoleResourceService")
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements ISysRoleResourceService {

}