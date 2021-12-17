package me.missionfamily.web.mission_family_be.common.interceptor;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.logging.context.LoggerContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class LoggerInterceptor implements HandlerInterceptor {

    private final StepLogger logger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoggerContext.resetAttributes();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}