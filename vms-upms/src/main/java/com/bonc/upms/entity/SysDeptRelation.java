package com.bonc.upms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Title: vms
 * @Package: com.bonc.upms.entity
 * @Description: 部门关系表
 * @Author: dreamcc
 * @Date: 2020/1/4 19:02
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDeptRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 祖先节点
     */
    private Long ancestor;

    /**
     * 后代节点
     */
    private Long descendant;


}
