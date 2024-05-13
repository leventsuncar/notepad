package com.lsuncar.notepad.core.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class CORSFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse servletResponse = response;
        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        servletResponse.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST, DELETE");
        servletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, Content-Type, Accept, X-Auth-Token");
        //TODO Aşağısı çirkin
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
        }
        filterChain.doFilter(request, servletResponse);
    }
}
