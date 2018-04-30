package com.test.demo.Filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/open/*")
@Primary
/**
 * Not a mandatory filter
 * */
public class OpenEndPointsFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SSOFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("OpenEndPointsFilter initialized!");
    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("OpenEndPointsFilter called!");
        filterChain.doFilter(servletRequest, servletResponse);
    }


    public void destroy() {
        log.info("OpenEndPointsFilter destroyed!");
    }

}
