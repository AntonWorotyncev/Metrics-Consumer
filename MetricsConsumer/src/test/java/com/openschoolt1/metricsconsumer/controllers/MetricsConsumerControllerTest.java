package com.openschoolt1.metricsconsumer.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.openschoolt1.metricsconsumer.dto.MetricDto;
import com.openschoolt1.metricsconsumer.service.MetricsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

    @WebMvcTest(MetricsConsumerController.class)
    public class MetricsConsumerControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private MetricsService metricsService;

        @Test
        public void testGetAllMetrics() throws Exception {
            MetricDto metric1 = new MetricDto(2L,123456L,1024L, 1025L);
            MetricDto metric2 = new MetricDto(3L,123456L,1024L, 1025L);

            when(metricsService.findAllMetrics()).thenReturn(Arrays.asList(metric1, metric2));

            mockMvc.perform(get("/api/metrics-consumer/metrics")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json( """
                            [
                                {
                                    "id": 2,
                                    "timestamp": 123456,
                                    "jvmMemoryUsed": 1024,
                                    "jvmMemoryMax": 1025
                                },
                                {
                                    "id": 3,
                                    "timestamp": 123456,
                                    "jvmMemoryUsed": 1024,
                                    "jvmMemoryMax": 1025
                                }
                            ]
                            """));

        }

        @Test
        public void testGetMetricById_Success() throws Exception {
            MetricDto metric = new MetricDto(6L, 123456L, 1028L, 1029L);

            when(metricsService.findById(1L)).thenReturn(Optional.of(metric));

            mockMvc.perform(get("/api/metrics-consumer/metrics/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """
                                        {
                                            "id": 6,
                                            "timestamp": 123456,
                                            "jvmMemoryUsed": 1028,
                                            "jvmMemoryMax": 1029
                                        }
                                           
                                    """));
        }

        @Test
        public void testGetMetricById_NotFound() throws Exception {
            when(metricsService.findById(1L)).thenReturn(Optional.empty());

            mockMvc.perform(get("/api/metrics-consumer/metrics/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }

        @Test
        public void testGetMetricById_BadRequest() throws Exception {
            mockMvc.perform(get("/api/metrics-consumer/metrics/invalidId")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }