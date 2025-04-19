package com.cfx.setup.soap.config;

import com.cfx.setup.soap.server.WebServiceAPR;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class WebServiceAPRConfig {

    @Value("${soap.server.apr.endpoint}")
    private String endpoint;

    private final Bus bus;
    private final WebServiceAPR webServiceAPR;
    private final LoggingOutInterceptor loggingOutInterceptor;
    private final LoggingInInterceptor loggingInInterceptor;

    public WebServiceAPRConfig(Bus bus, WebServiceAPR webServiceAPR,
            LoggingOutInterceptor loggingOutInterceptor,
            LoggingInInterceptor loggingInInterceptor) {
        this.bus = bus;
        this.webServiceAPR = webServiceAPR;
        this.loggingOutInterceptor = loggingOutInterceptor;
        this.loggingInInterceptor = loggingInInterceptor;
    }

    @Bean
    EndpointImpl aprEndpoint() {
        EndpointImpl endpointImpl = new EndpointImpl(this.bus, this.webServiceAPR);
        endpointImpl.publish(this.endpoint);
        endpointImpl.getOutInterceptors().add(this.loggingOutInterceptor);
        endpointImpl.getInInterceptors().add(this.loggingInInterceptor);
        endpointImpl.getInFaultInterceptors().add(this.loggingInInterceptor);
        endpointImpl.getOutFaultInterceptors().add(this.loggingOutInterceptor);
        return endpointImpl;
    }
}
