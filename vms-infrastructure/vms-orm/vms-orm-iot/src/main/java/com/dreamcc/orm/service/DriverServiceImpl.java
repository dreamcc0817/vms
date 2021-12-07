package com.dreamcc.orm.service;

import com.dreamcc.domain.iot.domain.Driver;
import com.dreamcc.domain.iot.repository.DriverRepository;
import org.springframework.stereotype.Service;

/**
 * @author cloud-cc
 * @ClassName DriverServiceImpl
 * @Description 驱动业务操作类
 * @date 2021/12/1 13:59
 * @Version 1.0
 */
@Service
public class DriverServiceImpl implements DriverRepository {
    @Override
    public void save(Driver driver) {

    }

    @Override
    public Driver getById(Long id) {
        return null;
    }
}
