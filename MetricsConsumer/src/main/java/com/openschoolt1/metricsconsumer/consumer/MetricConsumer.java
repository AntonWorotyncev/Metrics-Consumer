package com.openschoolt1.metricsconsumer.consumer;

import com.openschoolt1.metricsconsumer.repository.MetricRepository;
import com.openschoolt1.metricsconsumer.model.Metric;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MetricConsumer {

    private static final String CLIENT_ID = "metric-consumer";
    private static final String CLIENT_PREFIX = "t1";

    private final MetricRepository metricRepository;

    /**
     * Потребляет сообщение с данными метрик от сервиса Metrics Producer.
     *
     * @param metrics сообщения в формате JSON
     */
    @KafkaListener(
            id = CLIENT_ID,
            clientIdPrefix = CLIENT_PREFIX,
            topics = "${spring.kafka.metrics.topic}",
            groupId = "${spring.kafka.metrics.group-id}",
            autoStartup = "${spring.kafka.metrics.enable}"
    )
    public void listenMetricsGroup(List<Metric> metrics, Acknowledgment ack) {
        log.info("Пакет сообщений успешно принят слушателем, количество сообщений: {}", metrics.size());
        try {
            metricRepository.saveAll(metrics);
            log.info("Все сообщения успешно добавлены в БД");
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Ошибка при сохранении сообщений: {}", e.getMessage());
        }
    }
}