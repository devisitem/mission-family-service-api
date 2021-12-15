package me.missionfamily.web.mission_family_be.common.service_enum;

import me.missionfamily.web.mission_family_be.common.exception.ServiceException;

import java.util.Arrays;

public enum LogStep {
    DUPCHECK("DUPCHECK","아이디 중복확인"),
    REGISTER_USER("REGISTER_USER", "유저 회원가입"),
    SIGN_IN_USER("SIGN_IN_USER", "유저 로그인"),
    ;
    private String code;
    private String descriptions;

    LogStep(String code, String descriptions) {
        this.code = code;
        this.descriptions = descriptions;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescriptions() {
        return this.descriptions;
    }


    public static LogStep getLogStepByCode(String code) throws ServiceException {

        LogStep[] values = LogStep.values();

        return Arrays.stream(values).filter(step ->
                step.code.equals(code))
                .findFirst()
                .get();
    }
}
