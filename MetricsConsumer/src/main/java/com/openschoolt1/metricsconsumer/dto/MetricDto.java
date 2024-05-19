package com.openschoolt1.metricsconsumer.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Объект передачи данных, представляющий метрику")
public record MetricDto(
        @Schema(description = "Уникальный идентификатор метрики", example = "1")
        Long id,

        @Schema(description = "Метка времени, когда метрика была записана", example = "1627845623000")
        Long timestamp,

        @Schema(description = "Используемая память JVM в байтах", example = "1024")
        Long jvmMemoryUsed,

        @Schema(description = "Максимально доступная память JVM в байтах", example = "2048")
        Long jvmMemoryMax) {
}