package me.missionfamily.web.mission_family_be.common;

import lombok.Data;
import lombok.Getter;

@Getter
public enum HttpResponseStatus {

    USER_ID_DUPLICATE (200,001,"이미 존재하는 아이디입니다."),
    ;


    int status;
    int code;
    String message;


    HttpResponseStatus(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }



}
