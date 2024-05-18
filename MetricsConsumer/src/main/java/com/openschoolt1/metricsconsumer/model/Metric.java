package com.openschoolt1.metricsconsumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
      ВременнАя метка
    */
    private Long timestamp;
    /*
      Использование памяти JVM приложением MetricsProducer
     */
    private Long jvmMemoryUsed;

    /*
    Максимальный объем памяти,
    который JVM разрешено использовать во работы приложения MetricsProducer
   */
    private Long jvmMemoryMax;
}
