package com.openschoolt1.metricsconsumer.service.impl;

import com.openschoolt1.metricsconsumer.dto.MetricDto;
import com.openschoolt1.metricsconsumer.mappers.MetricMetricDtoMapper;
import com.openschoolt1.metricsconsumer.model.Metric;
import com.openschoolt1.metricsconsumer.repository.MetricRepository;
import com.openschoolt1.metricsconsumer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final MetricRepository metricRepository;

    private final MetricMetricDtoMapper metricMetricDtoMapper;

    @Override
    public Optional<MetricDto> findById(Long id) {
        return metricRepository.findById(id)
                .map(metricMetricDtoMapper::metricToMetricDto);
    }

    @Override
    public List<MetricDto> findAllMetrics() {
        List<Metric> metrics = metricRepository.findAll();
        return metrics.stream()
                .map(metricMetricDtoMapper::metricToMetricDto)
                .collect(Collectors.toList());
    }
}

