package me.missionfamily.web.mission_family_be.common.aop;

import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.common.exception.HttpResponseStatus;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionRequest;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MissionAuthAop {

    private AccountRepository accountRepository;

    @Around("@annotation(LoginService)")
    public Object checkLoginService(ProceedingJoinPoint joinPoint) throws Throwable {


        for (Object arg : joinPoint.getArgs()){

            if(arg instanceof MissionRequest){
                AccountModel account = ((MissionRequest) arg).account();

                String loginId = account.getLoginId();
                String missionSignature = account.getMissionSignature();

                UserInfo loginUser = accountRepository.findUserInfoByUserId(loginId);

                if(MissionUtil.isEmptyOrNull(missionSignature)){

                    throw new ServiceException(HttpResponseStatus.AUTHKEY_MUST_BE_NON_NULL);
                }

                if(missionSignature.equals(loginUser)) {

                    return joinPoint.proceed();
                }
            }
        }

        return joinPoint.proceed();
    }
}
