package me.missionfamily.web.mission_family_be.common.aop;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAop {

    @Around("execution(* me.missionfamily.web.mission_family_be..*Controller.*(..))")
    public Object initRequestLog(ProceedingJoinPoint joinPoint) throws Throwable {





        return joinPoint.proceed();
    }
}
