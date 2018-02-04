package hr.samara.web

import hr.samara.SampleWsApplication
import hr.samara.web.api.HelloService
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.http.*
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.nio.charset.Charset

/**
 * hello web service is not secured while health endpoint is
 */
@SpringBootTest(classes = SampleWsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWsIntegrationSpec extends Specification {

    private HelloService client

    @LocalServerPort
    private int port

    @Shared
    String healthPath
    @Autowired
    private Environment env

    def setup() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean()
        proxyFactory.setServiceClass(HelloService.class)
        proxyFactory.setAddress("http://localhost:" + this.port + "/services/hello")
        this.client = (HelloService) proxyFactory.create()

        this.healthPath = "http://localhost:" + this.port + env.getProperty('management.context-path') + '/health'
    }

    @Unroll
    def "call test operation with request [#request] and receive response [#expectedReponse]"() {
        when: "service is called"
        String response = client.hi(request)
        then: "evaluate response"
        response == expectedReponse
        where:
        request    || expectedReponse
        "John Doe" || "Hello John Doe"
        null       || "Hello Web Service"
        ""         || "Hello Web Service"
    }

    def "management-ui is secured - 401"() throws Exception {
        given:
        RestTemplate restTemplate = new RestTemplate()
        when:
        restTemplate.getForObject(healthPath, String.class)
        then:
        HttpClientErrorException ex = thrown()
        ex.message.contains('401')
        println "ex.message: ${ex.message}"
    }

    def "management-ui is secured - use basic auth - OK"() throws Exception {
        given:
        RestTemplate restTemplate = new RestTemplate()
        String auth = "admin:pass"
        byte[] encodedAuth = Base64.encoder.encode(auth.getBytes(Charset.forName("UTF-8")))
        String authHeader = "Basic " + new String(encodedAuth)
        HttpHeaders authHeaders = new HttpHeaders()
        authHeaders.set("Authorization", authHeader)
        when:
        ResponseEntity<String> responseEntity = restTemplate.exchange(healthPath, HttpMethod.GET, new HttpEntity(authHeaders), String.class)
        then:
        responseEntity
        println "responseEntity: ${responseEntity}"
        responseEntity.statusCode == HttpStatus.OK
    }

}
