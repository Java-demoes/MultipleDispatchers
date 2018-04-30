package com.test.demo.Filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@WebFilter(urlPatterns = "/protected/*")
/**
 *
 * Implement logic here to check whether the incoming request has a valid sso id
 * session id can be used
 * when authenticated request hits this filter store the session in a list for multiple logins
 * on each request check if the session id is contained in the list
 * */
public class SSOFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SSOFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SSOFilter initialized!");
    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filter B called!");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        Stream.of(req.getHeaderNames()).forEach(System.out::println);
        if(Boolean.valueOf(req.getHeader("Authenticated"))){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            res.sendError(405,"User not authenticated!");
        }

    }


    public void destroy() {
        log.info("SSOFilter destroyed!");

    }
}
