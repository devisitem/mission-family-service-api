package me.missionfamily.web.mission_family_be.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginService {
    /**
     * 계정정보가 필요한 서비스에 적용
     */
}
