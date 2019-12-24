package com.bonc.vms.gateway.codec;

import com.bonc.vms.gateway.entity.KeepAliveReceived;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.codec
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/12/24 9:27
 * @Version: V1.0
 */
public class KeepAliveReceivedDecoder extends AbstractMonitorDecoder<KeepAliveReceived> {

	@Override
	protected KeepAliveReceived decodeBody(ChannelHandlerContext ctx, Map map, String xml) throws Exception {
		KeepAliveReceived keepalive=new KeepAliveReceived();
		return keepalive;
	}
}
