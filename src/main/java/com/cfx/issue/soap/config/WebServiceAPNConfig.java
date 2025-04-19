package com.cfx.issue.soap.config;

import com.cfx.issue.soap.server.WebServiceAPN;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)

public class WebServiceAPNConfig {

    @Value("${soap.server.apn.endpoint}")
    private String endpoint;

    private final Bus bus;
    private final WebServiceAPN webServiceAPN;
    private final LoggingOutInterceptor loggingOutInterceptor;
    private final LoggingInInterceptor loggingInInterceptor;

    public WebServiceAPNConfig(Bus bus, WebServiceAPN webServiceAPN,
            LoggingOutInterceptor loggingOutInterceptor,
            LoggingInInterceptor loggingInInterceptor) {
        this.bus = bus;
        this.webServiceAPN = webServiceAPN;
        this.loggingOutInterceptor = loggingOutInterceptor;
        this.loggingInInterceptor = loggingInInterceptor;
    }

    @Bean
    EndpointImpl apnEndpoint() {
        EndpointImpl endpointImpl = new EndpointImpl(this.bus, this.webServiceAPN);
        endpointImpl.publish(this.endpoint);
        endpointImpl.getOutInterceptors().add(this.loggingOutInterceptor);
        endpointImpl.getInInterceptors().add(this.loggingInInterceptor);
        endpointImpl.getInFaultInterceptors().add(this.loggingInInterceptor);
        endpointImpl.getOutFaultInterceptors().add(this.loggingOutInterceptor);
        return endpointImpl;
    }
}
