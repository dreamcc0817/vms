package com.dreamcc.domain.iot.repository;

import com.dreamcc.domain.iot.domain.Point;
import org.springframework.stereotype.Component;


/**
 * @author cloud-cc
 * @ClassName PointRepository
 * @Description 位号仓储类
 * @date 2021/11/30 09:12
 * @Version 1.0
 */
@Component
public interface PointRepository {

    /**
     * 保存位号信息
     *
     * @param point 位号信息
     * @return 位号信息
     */
    void save(Point point);

    /**
     * 根据ID获取位号信息
     *
     * @param id id
     * @return 位号信息
     */
    Point getById(Long id);
}
