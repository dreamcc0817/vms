package com.dreamcc.domain.iot.service;

import com.dreamcc.domain.iot.domain.Point;

/**
 * @author cloud-cc
 * @ClassName PointFactory
 * @Description TODO
 * @date 2021/11/30 11:23
 * @Version 1.0
 */
public class PointFactory {

    private final IdGenerator idGenerator;

    public PointFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Point create(Point point){
        Long id = idGenerator.snowFlakeId();
        return point.create(id);
    }
}
