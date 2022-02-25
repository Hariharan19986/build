package com.example.demo;

import io.micrometer.appoptics.AppOpticsConfig;
import io.micrometer.appoptics.AppOpticsMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.lang.Nullable;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitorConfig {

    AppOpticsConfig appOpticsConfig = new AppOpticsConfig() {
        @Override
        public String apiToken() {
            return "DeOjWl7Ei7KhI4KZ2jLfavDYaLecX0fD-jyb47ksK81vWKGHJokleyzGO9dVkWlFA6jDlzA";
        }
        @Override
        @Nullable
        public String get(String s) {
            return null;
        }

    };
    MeterRegistry registry = new AppOpticsMeterRegistry(appOpticsConfig, Clock.SYSTEM);


}
