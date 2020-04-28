package com.bonc.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bonc.upms.entity.SysUserPermissionRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: vms
 * @Package: com.bonc.upms.mapper
 * @Description: 用户权限关系类
 * @Author: dreamcc
 * @Date: 2020/4/23 22:30
 * @Version: V1.0
 */
@Mapper
public interface SysUserPermissionRelationMapper extends BaseMapper<SysUserPermissionRelation> {
	int deleteByUserId(Long userId);
}
