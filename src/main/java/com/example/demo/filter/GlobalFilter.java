package com.example.demo.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 전처리
        String url = httpServletRequest.getRequestURI();

        BufferedReader br = httpServletRequest.getReader();

        br.lines().forEach(line -> {
            log.info("url : {}, line : {}",url, line);
        });


        chain.doFilter(httpServletRequest, httpServletResponse);
        log.info("sssssssssssssssss");
        log.debug("sssssssssssssssss");
        // 후처리
    }
}
