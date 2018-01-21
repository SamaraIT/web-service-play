package hr.samara.config;

import hr.samara.web.HelloServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class WebServiceConfig {

    @Value("${cxf.log.soap.enabled}")
    private boolean loggingEnabled;

    @Autowired
    private Bus springBus;

    @Bean
    public Bus bus() {
        if (loggingEnabled)
            springBus.setFeatures(new ArrayList<>(Arrays.asList(loggingFeature())));
        return springBus;
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus(), new HelloServiceImpl());
        endpoint.publish("/hello");
        return endpoint;
    }

    @Bean
    public LoggingFeature loggingFeature() {
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);

        return loggingFeature;
    }

}
