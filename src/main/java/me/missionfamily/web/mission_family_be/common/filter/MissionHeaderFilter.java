package me.missionfamily.web.mission_family_be.common.filter;


import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class MissionHeaderFilter implements Filter {

    private final StepLogger step;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String appToken = req.getHeader("MISSION_APP_TOKEN");

        //== 헤더 검증로직 구현 ==//

        String userId = req.getParameter("user_id");
        System.out.println("userId = " + userId);
        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println("parameterNames = " + parameterNames);

        filterChain.doFilter(req, res);
    }
}
