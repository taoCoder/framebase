package com.tao.filter;

import com.tao.wrapper.ParameterRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangtao54
 * @description 参数封装filter
 * @date 2018/10/17
 */
@Component
@WebFilter(urlPatterns = "/*")
public class RequestWrapperFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(RequestWrapperFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String queryString = request.getQueryString();
        log.info("path=【{}】",queryString);

        Enumeration headerNames = request.getHeaderNames();
        Map<String, String> map = new HashMap<>(10);
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        log.info("header=【{}】",map.toString());

        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
