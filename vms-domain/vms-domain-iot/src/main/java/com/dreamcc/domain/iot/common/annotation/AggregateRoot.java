package com.dreamcc.domain.iot.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author cloud-cc
 * @ClassName AggregateRoot
 * @Description 聚合根
 * @date 2021/12/8 09:01
 * @Version 1.0
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Component
public @interface AggregateRoot {
}
