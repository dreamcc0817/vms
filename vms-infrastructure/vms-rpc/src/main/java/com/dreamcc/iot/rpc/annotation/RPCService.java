package com.dreamcc.iot.rpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.rpc
 * @Description: RPC注解
 * @Author: dreamcc
 * @Date: 2019/12/13 15:08
 * @Version: V1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RPCService {
	String value() default "";
}
