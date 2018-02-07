package hr.samara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// AOP - use cglib
@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
public class SampleWsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SampleWsApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleWsApplication.class, args);
    }
}
