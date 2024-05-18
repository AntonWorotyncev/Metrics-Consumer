package com.openschoolt1.metricsconsumer.controllers;


import com.openschoolt1.metricsconsumer.dto.MetricDto;
import com.openschoolt1.metricsconsumer.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/metrics-consumer")
@RequiredArgsConstructor
@Tag(name = "Metrics Consumer", description = "Микросервис для просмотра и анализа метрик")
public class MetricsConsumerController {

    private final MetricsService metricsService;

    @Operation(summary = "Получить список всех метрик приложения",
            description = "Возвращает общий список всех метрик")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
    })
    @GetMapping("/metrics")
    public List<MetricDto> getAllMetrics() {
        return metricsService.findAllMetrics();
    }

    @Operation(summary = "Получение конкретной метрики по ее идентификатору",
            description = "Возвращает метрику по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "404", description = "Метрика отсутствует")
    })
    @GetMapping("/metrics/{id}")
    public ResponseEntity<MetricDto> getMetricById(@PathVariable String id) {
        try {
            Optional<MetricDto> metricDto = metricsService
                    .findById(Long.valueOf(id));
            return metricDto.map(ResponseEntity::ok).orElseGet(() ->
                    ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
