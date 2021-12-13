package me.missionfamily.web.mission_family_be.common.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.common.exception.HttpResponseStatus;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionRequest;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MissionAuthAop {

    private final AccountRepository accountRepository;
    private final StepLogger step;
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

                if(missionSignature.equals(loginUser.getAuthKey())) {

                    return joinPoint.proceed();
                }else {

                    step.error("Failed Authorization. Please check your token. login");
                    throw new ServiceException(HttpResponseStatus.FAILED_AUTHENTICATE_PROCESS);
                }
            }
        }

        return joinPoint.proceed();
    }
}
