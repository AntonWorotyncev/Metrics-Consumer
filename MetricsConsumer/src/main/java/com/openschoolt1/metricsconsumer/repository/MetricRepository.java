package com.openschoolt1.metricsconsumer.repository;


import com.openschoolt1.metricsconsumer.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long>{}