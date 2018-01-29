package hr.samara.web;

import hr.samara.web.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class HelloServiceImpl implements HelloService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String hi(String request) {
        logger.info("START " + request);
        String response = "Hello Web Service";
        if (StringUtils.isEmpty(request) == false)
            response = "Hello " + request;
        logger.debug("END " + response);
        return response;
    }
}
