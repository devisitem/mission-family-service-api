package me.missionfamily.web.mission_family_be.common.service_enum;

public enum ServiceProperties {
    INVITE_MEMBER("APP", "IVT01", "패밀리 멤버로 초대"),

    NOTICE_ALL("APP", "NTC01", "전체 공지사항"),
    NOTICE_FAMILIES("APP", "NTC02", "패밀리 공지사항"),


    ;






    String serviceType;
    String code;
    String description;

    ServiceProperties(String serviceType, String code, String description){
        this.serviceType = serviceType;
        this.code = code;
        this.description = description;
    }

    public String getDescriptions(){
        return this.description;
    }
}
