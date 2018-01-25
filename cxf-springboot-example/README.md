# Run at localhost
[CXF endpoint - services](http://localhost:8080/services)   
[WSDL](http://localhost:8080/services/hello?wsdl)    
[Actuator - health endpoint](http://localhost:8080/health) 


# Spring Boot CXF JAX-WS Starter
[Documentation](https://cxf.apache.org/docs/springboot.html)     
Registers CXFServlet with a  "/services/*" URL pattern for serving CXF JAX-WS endpoints.    
[Sample on github](https://github.com/apache/cxf/tree/master/distribution/src/main/release/samples/jaxws_spring_boot)  

Use "cxf.path" property to customize a CXFServlet URL pattern  
Use "cxf.servlet.init" map property to customize CXFServlet properties such as "services-list-path" (available by default at  "/services"), etc.

# Examples in the world
[Spring Boot & Apache CXF – How to SOAP in 2016](https://blog.codecentric.de/en/2016/02/spring-boot-apache-cxf/)    
[A Guide to Apache CXF with Spring](http://www.baeldung.com/apache-cxf-with-spring)  
[Apache CXF - Spring Boot SOAP Web Service Client Server Example](https://www.codenotfound.com/apache-cxf-spring-boot-soap-web-service-client-server-example.html)  

# Logging
[Apache CXF - Logging SOAP Request Response Fault Messages Example](https://www.codenotfound.com/apache-cxf-logging-soap-request-response-fault-messages-example.html)  
[Spring Boot features Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-logging.html)      
[Using Logback with Spring Boot](https://springframework.guru/using-logback-spring-boot/)  
[Configuring Logback with Spring Boot](https://lankydanblog.com/2017/08/31/configuring-logback-with-spring-boot/)  
[Solving Your Logging Problems With Logback](https://dzone.com/articles/solving-your-logging-problems-with-logback)  


# Actuator endpoints
[Spring documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)  
[Spring Boot Actuator: A Complete Guide](https://dzone.com/articles/spring-boot-actuator-a-complete-guide)  
[An introduction to Spring Boot Actuator](https://aboullaite.me/an-introduction-to-spring-boot-actuator/)  