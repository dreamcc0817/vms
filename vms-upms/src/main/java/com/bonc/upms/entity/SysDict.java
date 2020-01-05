package com.bonc.upms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

/**
 * @Title: vms
 * @Package: com.bonc.upms.entity
 * @Description: 字典表
 * @Author: dreamcc
 * @Date: 2020/1/4 19:02
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = ASSIGN_ID)
    private Long id;

    /**
     * 数据值
     */
    private String value;

    /**
     * 标签名
     */
    private String label;

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序（升序）
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    private String delFlag;


}
