package me.missionfamily.web.mission_family_be.common.validator;

public enum DeclaredClassification {

    LOGIN_ID("LOGIN_ID", "4자 이상 12자 이하, 영문또는 숫자포함"),
    ;

    private String code;
    private String description;

    DeclaredClassification(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
