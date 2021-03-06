# Run at localhost
[CXF endpoint - services](http://localhost:8080/services)  
[Swagger](http://localhost:8080/swagger-ui.html)  
[WSDL](http://localhost:8080/services/hello?wsdl)    
[Actuator - health endpoint](http://localhost:8080/management-ui/health)  
[H2 Console](http://localhost:8080/h2-consolehttp://localhost:8080/h2-console)

# Spring Boot CXF JAX-WS Starter
[Documentation](https://cxf.apache.org/docs/springboot.html)     
Registers CXFServlet with a  "/services/*" URL pattern for serving CXF JAX-WS endpoints.    
[Sample on github](https://github.com/apache/cxf/tree/master/distribution/src/main/release/samples/jaxws_spring_boot)  

Use "cxf.path" property to customize a CXFServlet URL pattern  
Use "cxf.servlet.init" map property to customize CXFServlet properties such as "services-list-path" (available by default at  "/services"), etc.

## Examples
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
[Configure log level in runtime using Actuator endpoint](http://10.144.227.132:8080/services/hello?wsdl)  

# Lombok
[Being lazy with Lombok](https://lankydanblog.com/2017/04/29/being-lazy-with-lombok/)  
[Lombok Plugin for IntelliJ](https://plugins.jetbrains.com/plugin/6317-lombok-plugin)

# Testing
[Spring Boot features Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html)  
[Testing in Spring Boot](http://www.baeldung.com/spring-boot-testing)  

# Validation in Spring
[How-to: Method-level validation in Spring with @Validated annotation](http://blog.codeleak.pl/2012/03/how-to-method-level-validation-in.html)  
[Validating Spring MVC Request Mapping Method parameters](https://raymondhlee.wordpress.com/tag/methodvalidationpostprocessor/)    

# AOP
[Spring AOP AspectJ @AfterThrowing Annotation Advice Example](https://www.dineshonjava.com/spring-aop-aspectj-after-throwing-annotation-advice-example/)  

# Swagger
[Setting Up Swagger 2 with a Spring REST API](http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)    
[Spring Boot Restful API documentation with Swagger 2](https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/)

# Spring Boot Gradle plugin
[Spring Boot Gradle plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-gradle-plugin.html)

# AOP
[Spring JDK Proxies vs CGLIB vs AspectJ](https://www.credera.com/blog/technology-insights/open-source-technology-insights/aspect-oriented-programming-in-spring-boot-part-2-spring-jdk-proxies-vs-cglib-vs-aspectj/)

