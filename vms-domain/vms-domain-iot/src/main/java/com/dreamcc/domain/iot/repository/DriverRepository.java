package com.dreamcc.domain.iot.repository;

import com.dreamcc.domain.iot.domain.Driver;

/**
 * @author cloud-cc
 * @ClassName DriverRepository
 * @Description 驱动仓储类
 * @date 2021/11/30 09:13
 * @Version 1.0
 */
public interface DriverRepository {

    /**
     * 注册驱动
     *
     * @param driver 驱动
     */
    void save(Driver driver);

    /**
     * 根据ID获取驱动
     *
     * @param id ID
     * @return 驱动
     */
    Driver getById(Long id);
}
