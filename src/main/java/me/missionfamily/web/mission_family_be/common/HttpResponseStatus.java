package me.missionfamily.web.mission_family_be.common;

import lombok.Data;
import lombok.Getter;

@Getter
public enum HttpResponseStatus {

    USER_ID_DUPLICATE (400,"001","이미 존재하는 아이디입니다."),
    NO_ACCOUNT_DATA_FOUNDS(400,"002", "등록된 계정이 없거나, 정보가 일치하지 않습니다."),
    AUTHKEY_MUST_BE_NON_NULL(403, "003", "인증키는 빈값 일 수 없습니다."),
    FAILED_AUTHENTICATE_PROCESS(403, " 004", "인증에 실패하였습니다."),
    ;


    int status;
    String code;
    String message;


    HttpResponseStatus(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }



}
