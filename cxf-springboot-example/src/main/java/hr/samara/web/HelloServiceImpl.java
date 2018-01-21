package hr.samara.web;

public class HelloServiceImpl implements hr.samara.web.HelloService {
    @Override
    public String hi(String request) {
        return "Hello Web Service";
    }
}
