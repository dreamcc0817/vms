package com.bonc.service.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bonc.service.device.entity.ServerUnit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.dao
 * @Description: 服务单元Mapper
 * @Author: dreamcc
 * @Date: 2020/1/2 11:36
 * @Version: V1.0
 */
@Mapper
public interface ServerUnitMapper extends BaseMapper<ServerUnit> {

	/**
	 * 通过IP与类型查找服务单元
	 *
	 * @param host ip地址
	 * @param type 设备类型
	 * @return
	 */
	ServerUnit findByIpAndType(@Param("host") String host, @Param("type") Integer type);
}
