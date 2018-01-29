package hr.samara.config;

import hr.samara.web.HelloServiceImpl;
import hr.samara.web.api.ArticleWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Environment environment;
    @Autowired
    @Qualifier(Bus.DEFAULT_BUS_ID)
    private Bus springBus;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Bus bus() {
        if (this.environment.getProperty("logging.soap.enabled", Boolean.class)) {
            this.springBus.setFeatures(new ArrayList<>(Arrays.asList(loggingFeature())));
            logger.info("enabling logging of SOAP messages");
        }
        return this.springBus;
    }

    @Bean
    public Endpoint helloServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus(), new HelloServiceImpl());
        String addr = "/hello";
        endpoint.publish(addr);
        logger.info("publishing HelloService on " + addr);
        return endpoint;
    }

    @Bean
    public Endpoint articleServiceEndpoint(ArticleWebService articleWebService) {
        EndpointImpl endpoint = new EndpointImpl(bus(), articleWebService);
        String addr = "/article";
        endpoint.publish(addr);
        logger.info("publishing ArticleService on " + addr);
        return endpoint;
    }

    @Bean
    public LoggingFeature loggingFeature() {
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        return loggingFeature;
    }

    @PostConstruct
    public void printProperties() {
        logger.info("   *** ACTIVE PROFILES ***   " + Arrays.toString(this.environment.getActiveProfiles()));
        logger.info("logging.soap.enabled: " + this.environment.getProperty("logging.soap.enabled"));
    }

}
