package com.openschoolt1.metricsconsumer.dto;

public record MetricDto(Long id, Long timestamp,
                        Long jvmMemoryUsed, Long jvmMemoryMax) {
}
