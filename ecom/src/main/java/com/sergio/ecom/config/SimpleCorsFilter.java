package com.sergio.ecom.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // @Order y Ordered.HIGHEST_PRECEDENCE Cuando aplicas la anotación @Order con el valor Ordered.HIGHEST_PRECEDENCE, estás indicando que el bean anotado debe tener la más alta prioridad de ejecución. En términos prácticos, esto significa que se ejecutará antes que otros beans con una prioridad más baja.
public class SimpleCorsFilter implements Filter {


    // En esta clase vamos a llamar a nuestra API desde ANGULAR.

    @Value("${app.client.url}")
    private String clientAppUrl = "";

    public SimpleCorsFilter(){}


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest)  req;
        Map<String, String> map = new HashMap<>();
        String originHeader = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    // Método para crear la cuenta de administrador
    public void createAdminAccount(){

    }



}
