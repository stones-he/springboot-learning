package io.java.learning.config;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MicrometerConfig {
    @Value("${spring.application.name:demo-app}")
    private String appName;

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> {
            registry.config().commonTags("application", appName).meterFilter(new MeterFilter() {
                @Override
                public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                    if (id.getType() == Meter.Type.TIMER & id.getName().matches("^(http){1}.*")) {
                        return DistributionStatisticConfig.builder()
                                .percentilesHistogram(true)
                                .percentiles(0.5, 0.9, 0.95, 0.99)
                                .serviceLevelObjectives(
                                        Duration.ofMillis(50).toNanos(),
                                        Duration.ofMillis(100).toNanos(),
                                        Duration.ofMillis(200).toNanos(),
                                        Duration.ofSeconds(1).toNanos(),
                                        Duration.ofSeconds(5).toNanos()
                                )
                                .build().merge(config);
                    } else {
                        return config;
                    }
                }
            });
        };
    }
}
