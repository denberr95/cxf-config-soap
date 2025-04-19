package com.cfx.issue.soap.config;

import jakarta.xml.ws.soap.SOAPBinding;
import com.cfx.issue.soap.util.Constants;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import it.mkt.hub.soap.schemas.v1.apr.APR;
import it.mkt.hub.soap.schemas.v1.apr.APRService;

@Configuration(proxyBeanMethods = false)
public class ClientAPRConfig {

    @Value("${soap.client.apr.connection-timeout}")
    private long connectionTimeout = Constants.DEFAULT_CONNECTION_TIMEOUT;

    @Value("${soap.client.apr.receive-timeout}")
    private long receiveTimeout = Constants.DEFAULT_RECEIVE_TIMEOUT;

    @Value("${soap.client.apr.allow-chuncking}")
    private boolean allowChunking = Constants.DEFAULT_ALLOW_CHUNKING;

    @Value("${soap.client.apr.username}")
    private String username = Constants.DEFAULT_USERNAME;

    @Value("${soap.client.apr.password}")
    private String password = Constants.DEFAULT_PASSWORD;

    @Value("${soap.client.apr.endpoint}")
    private String endpoint = Constants.DEFAULT_URL;

    private final LoggingInInterceptor loggingInInterceptor;
    private final LoggingOutInterceptor loggingOutInterceptor;

    public ClientAPRConfig(LoggingInInterceptor loggingInInterceptor,
            LoggingOutInterceptor loggingOutInterceptor) {
        this.loggingInInterceptor = loggingInInterceptor;
        this.loggingOutInterceptor = loggingOutInterceptor;
    }

    @Bean
    APR apr() {
        APRService service = new APRService(APRService.WSDL_LOCATION, APRService.SERVICE);
        service.addPort(APRService.APRPort, SOAPBinding.SOAP11HTTP_BINDING, this.endpoint);

        APR apr = service.getPort(APRService.APRPort, APR.class);

        Client client = ClientProxy.getClient(apr);
        client.getInInterceptors().add(this.loggingInInterceptor);
        client.getOutInterceptors().add(this.loggingOutInterceptor);
        client.getInFaultInterceptors().add(this.loggingInInterceptor);
        client.getOutFaultInterceptors().add(this.loggingOutInterceptor);

        HTTPConduit http = (HTTPConduit) client.getConduit();

        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(this.connectionTimeout);
        httpClientPolicy.setAllowChunking(this.allowChunking);
        httpClientPolicy.setReceiveTimeout(this.receiveTimeout);

        AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
        authorizationPolicy.setUserName(this.username);
        authorizationPolicy.setPassword(this.password);

        http.setClient(httpClientPolicy);
        http.setAuthorization(authorizationPolicy);

        return apr;
    }
}
