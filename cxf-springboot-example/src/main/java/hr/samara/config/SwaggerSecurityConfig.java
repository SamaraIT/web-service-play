package hr.samara.config;

import hr.samara.swagger.SwaggerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PostConstruct;

/**
 * secure swagger and management endpoints
 */
@ConditionalOnExpression("${app.swagger.enabled:false} && ${app.swagger.securedGuiAccess:true}")
@EnableWebSecurity
@EnableConfigurationProperties(SwaggerProperties.class)
class SwaggerSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SWAGGER_UI_ROOT = "/swagger-ui.html";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @PostConstruct
    public void printProperties() {
        logger.info("   *** SWAGGER SECURITY IS ON ***   ");
        logger.info("app.swagger.enabled: " + this.environment.getProperty("app.swagger.enabled"));
        logger.info("app.swagger.securedGuiAccess: " + this.environment.getProperty("app.swagger.securedGuiAccess"));
        logger.info("management.context-path: " + this.environment.getProperty("management.context-path"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(SWAGGER_UI_ROOT, this.environment.getProperty("management.context-path") + "/**").authenticated();
    }

    /**
     * If application properties do not provide swagger username and password, default values are admin/password.
     */
    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth, SwaggerProperties swaggerProperties) throws Exception {
        auth.inMemoryAuthentication().withUser(swaggerProperties.getUsername()).password(swaggerProperties.getPassword()).roles("ADMIN");
    }

    @Bean
    SecurityAutoConfiguration autoConfiguration() {
        return new SecurityAutoConfiguration();
    }

    @Bean
    ManagementWebSecurityAutoConfiguration managementWebSecurityAutoConfiguration() {
        return new ManagementWebSecurityAutoConfiguration();
    }
}
