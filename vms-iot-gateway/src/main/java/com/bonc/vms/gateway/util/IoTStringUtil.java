package com.bonc.vms.gateway.util;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.util
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/12/10 17:21
 * @Version: V1.0
 */
public class IoTStringUtil {

	/**
	 * 格式化IP地址
	 *
	 * @param ip   IP地址
	 * @param port 端口号
	 * @return 格式化后的地址
	 */
	public static String formatIpAddress(String ip, String port) {
		return String.format("%s|%s", ip.trim(), port.trim());
	}
}
