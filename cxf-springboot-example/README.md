# Example
http://localhost:8080/services/Hello?wsdl


# Spring Boot CXF JAX-WS Starter
https://cxf.apache.org/docs/springboot.html   
Registers CXFServlet with a  "/services/*" URL pattern for serving CXF JAX-WS endpoints.
https://github.com/apache/cxf/tree/master/distribution/src/main/release/samples/jaxws_spring_boot

Use "cxf.path" property to customize a CXFServlet URL pattern  
Use "cxf.servlet.init" map property to customize CXFServlet properties such as "services-list-path" (available by default at  "/services"), etc.

# Examples on net
https://blog.codecentric.de/en/2016/02/spring-boot-apache-cxf/  
http://www.baeldung.com/apache-cxf-with-spring

# Logging
https://www.codenotfound.com/apache-cxf-logging-soap-request-response-fault-messages-example.html
