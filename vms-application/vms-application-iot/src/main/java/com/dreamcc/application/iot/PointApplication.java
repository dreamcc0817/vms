package com.dreamcc.application.iot;

import com.dreamcc.domain.iot.domain.Point;
import com.dreamcc.domain.iot.repository.PointRepository;
import com.dreamcc.domain.iot.service.PointFactory;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName PointApplication
 * @Description 位号应用层
 * @date 2021/11/30 11:21
 * @Version 1.0
 */
@Component
public class PointApplication {

    private final PointRepository pointRepository;

    private final PointFactory pointFactory;

    public PointApplication(PointRepository pointRepository, PointFactory pointFactory) {
        this.pointRepository = pointRepository;
        this.pointFactory = pointFactory;
    }

    /**
     * 保存位号信息
     *
     * @param point 位号信息
     * @return 位号
     */
    public void save(Point point) {
        pointFactory.create(point);
        pointRepository.save(point);
    }
}
