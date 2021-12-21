package me.missionfamily.web.mission_family_be.common.interceptor;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.logging.context.TrackerContext;
import me.missionfamily.web.mission_family_be.common.logging.context.TrackerContextHolder;
import me.missionfamily.web.mission_family_be.common.logging.tracker.StepLogTracker;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoggerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StepLogTracker tracker = new StepLogTracker();
        String txId = UUID.randomUUID().toString();
        tracker.init(txId);
        TrackerContextHolder.clearContext();
        TrackerContext context = TrackerContextHolder.createEmptyContext();
        context.setTracker(tracker);
        TrackerContextHolder.setContext(context);
        MDC.put("dsa","Dsa");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
