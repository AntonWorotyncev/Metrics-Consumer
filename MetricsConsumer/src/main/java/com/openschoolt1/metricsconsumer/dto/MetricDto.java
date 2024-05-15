package com.openschoolt1.metricsconsumer.dto;

public record MetricDto(Long id, Long timestamp, Long httpServerRequests, Double httpServerRequestsAvgTime,
                        Long jvmMemoryUsed, Long jvmMemoryMax, Integer systemCpuCount, Double systemCpuUsage,
                        Long processUptime, Double systemLoadAverage
) {
}
