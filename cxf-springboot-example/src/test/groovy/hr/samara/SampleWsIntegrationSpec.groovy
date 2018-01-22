package hr.samara

import hr.samara.web.HelloService
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = SampleWsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleWsIntegrationSpec extends Specification {

    private HelloService client

    @LocalServerPort
    private int port

    def setup() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean()
        proxyFactory.setServiceClass(HelloService.class)
        proxyFactory.setAddress("http://localhost:" + this.port + "/services/hello")
        this.client = (HelloService) proxyFactory.create()
    }

    @Unroll
    def "call test operation with request [#request] and receive response [#expectedReponse]"() {
        when: "service is called"
        String response = client.hi(request)
        then: "evaluate response"
        response == expectedReponse
        where:
        request     || expectedReponse
        "John Doe"  || "Hello John Doe"
        null        || "Hello Web Service"
        ""          || "Hello Web Service"
    }

}
