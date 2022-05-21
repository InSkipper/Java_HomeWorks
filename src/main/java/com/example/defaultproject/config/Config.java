package com.example.defaultproject.config;

import com.example.defaultproject.metric.CustomMetric;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@Aspect
@EnableScheduling
public class Config {
    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) {
        return new TimedAspect(meterRegistry);
    }

    @Bean
    public CustomMetric customMetric(MeterRegistry registry) {
        return new CustomMetric(registry);
    }
}
