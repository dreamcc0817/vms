package com.dreamcc.orm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamcc.orm.dataobject.ProfileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cloud-cc
 * @ClassName ProfileDAO
 * @Description Profile数据库操作类
 * @date 2021/12/1 09:10
 * @Version 1.0
 */
@Mapper
public interface ProfileDAO extends BaseMapper<ProfileDO> {

}