package com.openschoolt1.metricsconsumer.consumer;

import com.openschoolt1.metricsconsumer.repository.MetricRepository;
import com.openschoolt1.metricsconsumer.model.Metric;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetricConsumer {

private static final String CLIENT_ID = "metric-consumer";
private static final String CLIENT_PREFIX = "t1";

private final MetricRepository metricRepository;

    /**
     * Потребляет сообщение с данными метрик от сервиса Metrics Producer.
     *
     * @param metric сообщение в формате JSON
     */
    @KafkaListener(
            id = CLIENT_ID,
            clientIdPrefix = CLIENT_PREFIX,
            topics = "${kafka.metrics.topic}",
            groupId = "${kafka.metrics.group-id}",
            autoStartup = "${kafka.metrics.enable}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenMetricsGroup(Metric metric) {
        metricRepository.save(metric);
    }
}