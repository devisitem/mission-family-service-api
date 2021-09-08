package me.missionfamily.web.mission_family_be.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ServiceDescriptions {


    /**
     *
     * 해당 API의 설명을 작성할 수 있습니다.
     */
    String value() default "";


}
