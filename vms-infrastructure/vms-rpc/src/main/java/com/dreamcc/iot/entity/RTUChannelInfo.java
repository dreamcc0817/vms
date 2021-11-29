package com.dreamcc.iot.entity;

import io.netty.channel.ChannelId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.entity
 * @Description: 远程服务单元channel 信息
 * @Author: dreamcc
 * @Date: 2020/1/5 18:30
 * @Version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RTUChannelInfo {
	/**
	 * netty通道ID
	 */
	private ChannelId channelId;

	/**
	 * 设备sn码
	 */
	private String sn;
}
