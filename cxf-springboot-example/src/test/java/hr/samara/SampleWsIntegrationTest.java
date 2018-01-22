package hr.samara;

import hr.samara.web.HelloService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleWsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleWsIntegrationTest {

    private HelloService client;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setServiceClass(HelloService.class);
        proxyFactory.setAddress("http://localhost:" + this.port + "/services/hello");
        this.client = (HelloService) proxyFactory.create();
    }

    @Test
    public void helloWorld_withArguments() {
        String response = client.hi("John Doe");
        assertEquals("Hello John Doe", response);
    }

    @Test
    public void helloWorld_withoutArguments() {
        String response = client.hi(null);
        assertEquals("Hello Web Service", response);
    }
}
