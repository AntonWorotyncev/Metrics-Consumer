package com.openschoolt1.metricsconsumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Metric {
    /*
    Идентификатор метрики
     */
    @Id
    private Long id;
    /*
    Временная метка
    */
    private Long timestamp;
    /*
    Колличество HTTP запросов
     */
    private Long httpServerRequests;
    /*
    Среднее время выполнения HTTP запросов
     */
    private Double httpServerRequestsAvgTime;
    /*
      Использование памяти JVM
     */
    private Long jvmMemoryUsed;
    /*
     Максимальный объем памяти JVM
     */
    private Long jvmMemoryMax;
    /*
    Количество доступных процессоров
     */
    private Integer systemCpuCount;
    /*
     Загрузка процессора
     */
    private Double systemCpuUsage;
    /*
     Время работы процесса
     */
    private Long processUptime;
    /*
     Средняя загрузка системы
     */
    private Double systemLoadAverage;
}
