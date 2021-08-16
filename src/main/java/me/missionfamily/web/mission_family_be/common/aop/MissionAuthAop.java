package me.missionfamily.web.mission_family_be.common.aop;

import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.service.AccountService;
import me.missionfamily.web.mission_family_be.business.family.dxo.FamilyDxo;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class MissionAuthAop {

    private AccountService accountService;

    @Around("@annotation(LoginService)")
    public Object checkLoginService(ProceedingJoinPoint joinPoint) throws Throwable {


        for (Object arg : joinPoint.getArgs()){

            if(arg instanceof MissionRequest){

            }
        }

        return joinPoint.proceed();
    }
}
