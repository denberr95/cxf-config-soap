package com.cfx.setup.soap.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SoapApacheCXFConfig {

    @Value("${soap.feature.log-pretty-print}")
    private boolean isLoggingEnabled = false;

    @Bean(name = Bus.DEFAULT_BUS_ID)
    SpringBus springBus(LoggingFeature loggingFeature) {
        SpringBus springBus = new SpringBus();
        springBus.getFeatures().add(loggingFeature);
        return springBus;
    }

    @Bean
    LoggingFeature loggingFeature() {
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(this.isLoggingEnabled);
        return loggingFeature;
    }

    @Bean
    LoggingOutInterceptor loggingOutInterceptor() {
        return new LoggingOutInterceptor();
    }

    @Bean
    LoggingInInterceptor loggingInInterceptor() {
        return new LoggingInInterceptor();
    }
}
