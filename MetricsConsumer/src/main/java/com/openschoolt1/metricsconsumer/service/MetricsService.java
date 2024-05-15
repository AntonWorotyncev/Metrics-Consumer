package com.openschoolt1.metricsconsumer.service;

import com.openschoolt1.metricsconsumer.dto.MetricDto;

import java.util.List;
import java.util.Optional;

public interface MetricsService {
    /*
    Получение метрики по её идентификатору
     */
    Optional<MetricDto> findById(Long id);

    List<MetricDto> findAllMetrics();
}
