package hr.samara.web.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloService {

    @WebMethod(operationName = "hi")
    String hi(@WebParam(name = "request") final String request);
}
