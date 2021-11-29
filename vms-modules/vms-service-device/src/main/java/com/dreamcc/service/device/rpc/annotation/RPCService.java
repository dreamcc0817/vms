package com.dreamcc.service.device.rpc.annotation;

import java.lang.annotation.*;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.device.rpc.annotation
 * @Description: RPC注解
 * @Author: dreamcc
 * @Date: 2019/12/13 15:08
 * @Version: V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface RPCService {
	String value() default "";
}
