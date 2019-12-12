package com.bonc.common.log.annotation;

import java.lang.annotation.*;

/**
 * @Title: vms
 * @Package: com.bonc.common.log.annotation
 * @Description: 日志操作注解
 * @Author: dreamcc
 * @Date: 2019/12/12 16:06
 * @Version: V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
}
