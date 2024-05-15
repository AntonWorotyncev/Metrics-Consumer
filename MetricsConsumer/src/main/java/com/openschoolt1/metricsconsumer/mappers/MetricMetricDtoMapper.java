package com.openschoolt1.metricsconsumer.mappers;

import com.openschoolt1.metricsconsumer.dto.MetricDto;
import com.openschoolt1.metricsconsumer.model.Metric;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetricMetricDtoMapper {
    Metric  metricDtoToMetric(MetricDto metricDto);
    MetricDto metricToMetricDto(Metric metric);
}
