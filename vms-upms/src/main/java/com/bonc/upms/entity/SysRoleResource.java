package com.bonc.upms.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * 角色资源表(SysRoleResource)表实体类
 *
 * @author makejava
 * @since 2020-04-21 21:57:44
 */
@SuppressWarnings("serial")
public class SysRoleResource extends Model<SysRoleResource> {
    //角色ID
    private Integer resourceId;
    //资源ID
    private Integer roleId;


    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}