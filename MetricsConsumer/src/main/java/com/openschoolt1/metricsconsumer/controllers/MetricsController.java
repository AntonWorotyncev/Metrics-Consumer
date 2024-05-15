package com.openschoolt1.metricsconsumer.controllers;


import com.openschoolt1.metricsconsumer.dto.MetricDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "api/metrics")
@RequiredArgsConstructor
@Tag(name = "Metrics Consumer", description = "Микросервис для просмотра и анализа метрик")
public class MetricsController {

    private MetricsService metricsService;

    @Operation(summary = "Получить список всех метрик приложения",
            description = "Возвращает общий список всех метрик")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
    })
    @GetMapping("/metrics")
    public MetricsDto getAllMetrics(){
        return metricsService.getAllMetrics();
    }


    @Operation(summary = "Получение конкретной метрики по ее идентификатору",
            description = "Возвращает метрику по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
    })
    @GetMapping("metrics/{id}")
    public MetricDto getMetricById(@PathVariable String metricId){
      return metricsService.findById(metricId);
    }
}
