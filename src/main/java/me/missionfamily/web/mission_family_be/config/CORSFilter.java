package me.missionfamily.web.mission_family_be.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/api/**"}, description = "API 필터")
@Component("CorsFilter")
@Slf4j
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("CORSFilter --> {}",filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        req.setCharacterEncoding("utf-8");

        res.setHeader("Access-Control-Allow-Origin","*");
        res.setHeader("Access-Control-Allow-Methods","POST, GET, DELETE, PUT, PATCH, OPTION");

        res.setHeader("Accept-Charset","utf-8");
        res.setHeader("Cache-Control","no-cache");
        res.setHeader("Expires","-1");
        res.setHeader("Pragma","no-cache");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
