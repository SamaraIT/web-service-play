package hr.samara.web;

import org.springframework.util.StringUtils;

public class HelloServiceImpl implements hr.samara.web.HelloService {

    @Override
    public String hi(String request) {
        if (StringUtils.isEmpty(request))
            return "Hello Web Service";
        else return "Hello " + request;
    }
}
