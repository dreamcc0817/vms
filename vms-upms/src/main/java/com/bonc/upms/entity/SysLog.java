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
 * @Description: 日志表
 * @Author: dreamcc
 * @Date: 2020/1/4 19:02
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = ASSIGN_ID)
    private Long id;

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 操作IP地址
     */
    private String remoteAddr;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 操作方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 执行时间
     */
    private Long time;

    /**
     * 删除标记
     */
    private Integer delFlag;

    /**
     * 异常信息
     */
    private String exception;


}
