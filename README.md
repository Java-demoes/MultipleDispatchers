## Multiple Dispatcher servlets

@SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)
* The exclude does prevent Spring Boot from creating its own DispatcherServlet with / mapping. You can remove that line, 
if you want that mapping or define your own.
* You can add servletRegistrationBean.setLoadOnStartup(1) if you want to have your Servlets initialized on application start.
else it will wait for the first request for that servlet.
* It's important to set servletRegistrationBean.setName(...), else the servlets will override each other.


[http://localhost:8080/hello](http://localhost:8080/hello)
 * This is unprotected end point, with autoconfigured dispatcher servlet with context '/'

[http://localhost:8080/protected/secure/hello](http://localhost:8080/protected/secure/hello)
 * This request is handled by protected dispatcher which calls the hello method in  secure controller(ProtectedController.java) 
 * SSO filter is added to this context, so header **Authenticated=true** is required

[http://localhost:8080/open/insecure/hello](http://localhost:8080/open/insecure/hello)
 * This request is handled by Insecure dispatcher which calls the hello method in  insecure controller(UnProtectedController.java) 
 * Passes through OpenEndPointsFilter.java