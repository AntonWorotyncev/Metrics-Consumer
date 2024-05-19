package com.openschoolt1.metricsconsumer.consumer;

import com.openschoolt1.metricsconsumer.model.Metric;
import com.openschoolt1.metricsconsumer.repository.MetricRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.Acknowledgment;

import java.util.Arrays;
import java.util.List;

    @ExtendWith(MockitoExtension.class)
    public class MetricConsumerTest {

        @Mock
        private MetricRepository metricRepository;

        @Mock
        private Acknowledgment acknowledgment;

        @InjectMocks
        private MetricConsumer metricConsumer;

        @BeforeEach
        void setUp() {
            metricConsumer = new MetricConsumer(metricRepository);
        }

        @Test
        void testListenMetricsGroup_Success() {
            Metric metric1 = new Metric(1L, 123456L, 1024L, 1025L);
            Metric metric2 = new Metric(2L, 123456L, 2048L, 2050L);
            List<Metric> metrics = Arrays.asList(metric1, metric2);

            metricConsumer.listenMetricsGroup(metrics, acknowledgment);

            Mockito.verify(metricRepository, Mockito.times(1)).saveAll(metrics);
            Mockito.verify(acknowledgment, Mockito.times(1)).acknowledge();
        }

        @Test
        void testListenMetricsGroup_SaveError() {
            Metric metric1 = new Metric(1L, 123456L, 1024L, 1025L);
            Metric metric2 = new Metric(2L, 123456L, 2048L, 2050L);
            List<Metric> metrics = Arrays.asList(metric1, metric2);

            Mockito.doThrow(new RuntimeException("Хьюстон, у нас проблемы")).when(metricRepository).saveAll(metrics);

            metricConsumer.listenMetricsGroup(metrics, acknowledgment);

            Mockito.verify(metricRepository, Mockito.times(1)).saveAll(metrics);
            Mockito.verify(acknowledgment, Mockito.times(0)).acknowledge();
        }
    }
