package com.dreamcc.domain.iot.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author cloud-cc
 * @ClassName ValueObject
 * @Description 值对象
 * @date 2021/12/8 09:47
 * @Version 1.0
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Component
public @interface ValueObject {
}
