package me.missionfamily.web.mission_family_be.common.exception;

import lombok.Getter;

@Getter
public enum HttpResponseStatus {
    SUCCESS(200, 0, "성공 응답"),
    USER_ID_DUPLICATE (400,201,"이미 존재하는 아이디입니다."),
    NO_ACCOUNT_DATA_FOUNDS(400,202, "등록된 계정이 없거나, 정보가 일치하지 않습니다."),
    AUTHKEY_MUST_BE_NON_NULL(403, 203, "인증키는 빈값 일 수 없습니다."),
    FAILED_AUTHENTICATE_PROCESS(403, 204, "인증에 실패하였습니다."),
    NOT_FOUND_USER(500, 205, "존재하지 않는 유저 입니다."),
    NOT_FOUND_FAMILIES(500, 206, "패밀리를 찾을수 없습니다."),
    NON_EXIST_MESSAGE(500, 207 , "존재하지 않는 메세지입니다."),
    ;


    int status;
    int code;
    String message;


    HttpResponseStatus(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getCodeByStatus(HttpResponseStatus status) {
        return status.getCode();
    }
}
