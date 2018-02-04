package hr.samara.web

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import hr.samara.config.SwaggerConfig
import hr.samara.config.SwaggerSecurityConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

/**
 * test management and swagger access and security using mock mvc
 */
@SpringBootTest(classes = [SwaggerSecurityConfig, SwaggerConfig], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ActiveProfiles("man")
@Slf4j
class WebAccessSpec extends Specification {

    @Autowired
    private Environment env
    @Autowired
    MockMvc mockMvc

    @Shared
    String healthPath
    String swaggerPath = SwaggerSecurityConfig.SWAGGER_UI_ROOT
    @Shared
    String userName
    @Shared
    String userPass

    def setup() {
        this.healthPath = env.getProperty('management.context-path') + '/health'
        this.userName = env.getProperty('app.swagger.username')
        this.userPass = env.getProperty('app.swagger.password')
    }

    def "management-ui is secured - 401"() throws Exception {
        when:
        MockHttpServletResponse response = mockMvc.perform(get(healthPath)).andReturn().response
        then:
        response.status == HttpStatus.UNAUTHORIZED.value()
    }

    def "management-ui is secured - use basic auth - OK"() throws Exception {
        when:
        MockHttpServletResponse response = mockMvc.perform(get(healthPath).with(httpBasic(userName, userPass))).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString) as Map
        log.info("response content: ${content}")
        then:
        response.status == HttpStatus.OK.value()
        content.status == "UP"
        content.diskSpace.status == "UP"
        content.db.status == "UP"
        content.db.database == "H2"
    }

    def "swagger-ui is secured - 401"() throws Exception {
        when:
        MockHttpServletResponse response = mockMvc.perform(get(swaggerPath)).andReturn().response
        then:
        response.status == HttpStatus.UNAUTHORIZED.value()
    }

    def "swagger-ui is secured - use basic auth - OK"() throws Exception {
        when:
        MockHttpServletResponse response = mockMvc.perform(get(swaggerPath).with(httpBasic(userName, userPass))).andReturn().response
        then:
        response.status == HttpStatus.OK.value()
        response.contentType == "text/html"
        response.contentAsString.contains("<title>Swagger UI")
    }
}
