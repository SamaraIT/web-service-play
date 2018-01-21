package hr.samara.config;

import hr.samara.web.HelloServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Bean
    public Endpoint endpoint(Bus bus) {
        EndpointImpl endpoint = new EndpointImpl(bus, new HelloServiceImpl());
        endpoint.publish("/hello");
        return endpoint;
    }
}
