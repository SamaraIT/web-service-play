package hr.samara.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
@ConditionalOnProperty("app.swagger.enabled")
public class SwaggerConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(
                        RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder().code(500).message("500 server error").responseModel(new ModelRef("Error")).build())
                );
    }

    @PostConstruct
    public void printProperties() {
        logger.info("   *** SWAGGER ON ***   ");
        logger.info("app.swagger.enabled: " + this.environment.getProperty("app.swagger.enabled"));
    }

}
