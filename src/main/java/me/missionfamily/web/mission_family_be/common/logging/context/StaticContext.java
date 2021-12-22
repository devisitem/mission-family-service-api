package me.missionfamily.web.mission_family_be.common.logging.context;

public class StaticContext {

    private static String STATIC_STRING = "STATIC_STRING";

    public static String getString() {
        return STATIC_STRING;
    }

    public static void setString() {
        STATIC_STRING = "TESTED_STATIC_STRING";
    }
}
