package me.missionfamily.web.mission_family_be.common;

public enum HttpResponseStatus {

    USER_DUPLICATE (200,"001","already exist user id");







    int status;
    String code;
    String message;


    HttpResponseStatus(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
