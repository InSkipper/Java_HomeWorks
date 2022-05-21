package com.example.defaultproject.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;

public class CustomMetric {
    private final Counter counter;

    public CustomMetric(MeterRegistry registry) {
        counter = registry.counter("custom.metric.counter");
    }

    @Scheduled(fixedRate = 1000)
    public void metricTask() {
        counter.increment();
    }
}
