package me.missionfamily.web.mission_family_be.common.aop;

import me.missionfamily.web.mission_family_be.common.service_enum.LogStep;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ServiceDescriptions {


    /**
     *
     * 해당 메서드의 로깅 단계를 작성할 수 있습니다.
     */
    LogStep value();


}
