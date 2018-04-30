package com.test.demo;


import com.test.demo.Dispatchers.InsercureDispatcherServletConfig;
import com.test.demo.Dispatchers.SecureDispatcherServletConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan //to allow scanning of webFilter annotated classes
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class,args);
    }

    /**
    @SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)
    * The exclude does prevent Spring Boot from creating its own DispatcherServlet with / mapping.
    * You can remove that line, if you want that mapping or define your own.
    * ==============================================================================================
    * You can add servletRegistrationBean.setLoadOnStartup(1)
    * if you want to have your Servlets initialized on application start else it will wait for the first request for that servlet.
    * ==============================================================================================
    * It's important to set servletRegistrationBean.setName(...), else the servlets will override each other.
     * */

    /*
    *  dispatcher servlet A registration
    *
    * **/
    @Bean
    public ServletRegistrationBean UnprotectedDispatcherServlet(){
        //create an instance of dispatcher servlet
        DispatcherServlet ds = new DispatcherServlet();
        // dispatcher servlet need a applicationContext
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        // build applicationContext from Config classes
        //this will load all components including controllerB into application Context
        //applicationContext.register(Application.class);
        //this will only load controllerA into application Context
        applicationContext.register(InsercureDispatcherServletConfig.class);
        applicationContext.register(InsercureDispatcherServletConfig.class);
        //Set applicationContext to DispatcherServlet
        ds.setApplicationContext(applicationContext);
        // create servlet and map dispatcher servlet with some url
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(ds,"/open/*");
        servletRegistrationBean.setName("UnprotectedDispatcherServlet");
        return servletRegistrationBean;

    }
    /*
     *  dispatcher servlet B registration
     *
     * **/
    @Bean
    public ServletRegistrationBean ProtectedDispatcherServlet(){
        DispatcherServlet ds = new DispatcherServlet();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //this will load all components including controllerA into application Context
        //applicationContext.register(Application.class);
        //this will only load controllerB into application Context
        applicationContext.register(SecureDispatcherServletConfig.class);
        //map ds with applicationcontext
        ds.setApplicationContext(applicationContext);
        //Servlet and map the ds with url
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(ds,"/protected/*");
        servletRegistrationBean.setName("ProtectedDispatcherServlet");
        return servletRegistrationBean;
    }


}
