package me.missionfamily.web.mission_family_be.common.service_enum;

public enum ServiceProperties {
    NOTICE_ALL("APP", 1, "공지사항 메세지"),
    INVITE_FAMILIES("APP", 2, "패밀리 멤버로 초대"),
    NOTICE_FAMILIES("APP", 3, "패밀리 공지"),

    ;






    String serviceType;
    int code;
    String rage;

    ServiceProperties(String serviceType, int code, String range){
        this.serviceType = serviceType;
        this.code = code;
        this.rage = range;
    }
}
