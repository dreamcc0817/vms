package com.dreamcc.orm.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cloud-cc
 * @ClassName ProfilePO
 * @Description 模板数据库存储类
 * @date 2021/12/1 09:11
 * @Version 1.0
 */
@Data
@TableName("vms_iot_profile")
public class ProfileDO implements Serializable {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 是否可用
     */
    private Integer enable;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 逻辑删除符
     */
    private Integer deleted;
}
