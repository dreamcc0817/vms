package com.bonc.upms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Title: vms
 * @Package: com.bonc.upms.entity
 * @Description: 角色菜单表
 * @Author: dreamcc
 * @Date: 2020/1/4 19:02
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;


}
