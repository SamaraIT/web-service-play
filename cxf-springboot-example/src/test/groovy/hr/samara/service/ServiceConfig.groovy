package hr.samara.service

import groovy.util.logging.Slf4j
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor

@SpringBootApplication(scanBasePackages = ['hr.samara.dao', 'hr.samara.service'])
@Slf4j
class ServiceConfig {

    @Bean
    MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
